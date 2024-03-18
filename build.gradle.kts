import com.matthewprenger.cursegradle.*
import net.fabricmc.loom.task.RemapJarTask
import java.io.FileNotFoundException

plugins {
  id("fabric-loom") version Versions.LOOM
  id("com.modrinth.minotaur") version Versions.MINOTAUR
  id("com.matthewprenger.cursegradle") version Versions.CURSEGRADLE
}

java {
  withSourcesJar()
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

base {
  archivesName.set(ModData.id)
}

group = ModData.group
version = ModData.version

repositories {
  mavenCentral()
  maven("https://maven.draylar.dev/releases") // Omega-config
}

dependencies {
  minecraft("com.mojang:minecraft:${Versions.MINECRAFT}")
  mappings("net.fabricmc:yarn:${Versions.YARN}:v2")
  modImplementation("net.fabricmc:fabric-loader:${Versions.FABRIC_LOADER}")
  modImplementation("net.fabricmc.fabric-api:fabric-api:${Versions.FABRIC_API}")

  modImplementation(include("dev.draylar.omega-config:omega-config-base:${Versions.OMEGA_CONFIG}")!!)
}

tasks {
  withType<ProcessResources> {
    inputs.property("version", ModData.version)
    filesMatching("fabric.mod.json") {
      expand("version" to ModData.version)
    }
  }
  withType<JavaCompile> {
    configureEach {
      options.release.set(17)
    }
  }
  register("runDatagenScript") {
    group = "fabric"
    dependsOn("datagen:run")
  }
}

// Data generation using Python script

tasks.register<Exec>("runPythonDatagen") {
  workingDir = projectDir
  val scriptFile = workingDir.resolve("pyDatagen/main.py")
  val outputPath = workingDir.resolve("src/main/pyGenerated")

  commandLine("python", scriptFile.absolutePath, outputPath.absolutePath)
}

sourceSets {
  main {
    resources {
      srcDirs("src/main/pyGenerated", "src/main/generated")
    }
  }
}

// Publishing
val secretsFile = rootProject.file("publishing.properties")
val secrets = Secrets(secretsFile)

val remapJar = tasks.getByName("remapJar") as RemapJarTask
val newVersionName = "${ModData.id}-${ModData.mcVersions[0]}-${ModData.version}"
val newChangelog = try {
  rootProject.file("changelogs/${ModData.version}.md").readText()
} catch (_: FileNotFoundException) {
  println("No changelog found")
  ""
}

if (secrets.isModrinthReady()) {
  println("Setting up Minotaur")
  modrinth {
    token.set(secrets.modrinthToken)
    projectId.set(secrets.modrinthId)
    uploadFile.set(remapJar)
    versionName.set(newVersionName)
    versionType.set(ModData.versionType)
    changelog.set(newChangelog)
    syncBodyFrom.set(rootProject.file("projectPage/PROJECT.md").readText())
    gameVersions.set(ModData.mcVersions)
    loaders.set(listOf("fabric"))
    dependencies {
      ModData.dependencies.forEach(required::project)
    }
  }
}

if (secrets.isCurseforgeReady()) {
  println("Setting up Cursegradle")
  curseforge {
    apiKey = secrets.curseforgeToken
    project(closureOf<CurseProject> {
      id = secrets.curseforgeId
      releaseType = ModData.versionType
      ModData.mcVersions.forEach(::addGameVersion)
      addGameVersion("Fabric")
      changelog = newChangelog
      changelogType = "markdown"
      relations(closureOf<CurseRelation> {
        ModData.dependencies.forEach(::requiredDependency)
      })
      mainArtifact(remapJar, closureOf<CurseArtifact> {
        displayName = newVersionName
      })
    })
    options(closureOf<Options> {
      forgeGradleIntegration = false
    })
  }
  project.afterEvaluate {
    tasks.getByName<CurseUploadTask>("curseforge${secrets.curseforgeId}") {
      dependsOn(remapJar)
    }
  }
}