# Mim1q's Fabric Mod Template

This is my template for Fabric Mods.

The template is licensed under the CC0 License. Credit isn't required, but is appreciated :blush:
Please include your own `README.md` and `LICENSE` files after generating a project.

## Using this template

To use the template, simply click the "Use this template" button on the top right of the page and create a new project.

For now, you will have to create your own `src/main/resources/fabric.mod.json` file. 
Please refer to the [documentation](https://fabricmc.net/wiki/documentation:fabric_mod_json) for more information.

Example minimal `fabric.mod.json`:
```json
{
  "schemaVersion": 1,
  "id": "example",
  "name": "Example mod",
  "version": "${version}",
  "environment": "*",
  "entrypoints": {
    "main": [
      "com.example.examplemod.ExampleMod"
    ]
  },
  "depends": {
    "fabricloader": ">=0.14.9",
    "fabric": ">=0.58.0",
    "minecraft": "1.19.2"
  }
}
```

## Modifying mod data

The mod data is stored in `buildSrc/src/main/kotlin/ModData.kt`. 
You can change the group, mod id, version and name there.
There's also a `Versions.kt` file in the same directory. It stores the versions of all the required dependencies.

You can freely add fields to ModData and Versions, but you should keep the already existing ones.

To use the fields in the `build.gradle.kts` file, you can use the `ModData` and `Versions` objects, e.g:

```kotlin
dependencies {
    modImplementation("net.example:examplemod:${Versions.examplemod}")
}
```

## Publishing your mod

Create a file in the root directory of your project named `publishing.properties`. Do not add this file to git
(this template's .gitignore file already ignores it).

The file should have the following structure.
You can find your tokens in the settings of your Modrinth and Curseforge accounts, respectively.

```properties
  MODRINTH_TOKEN=<your modrinth token>
  MODRINTH_ID=<your modrinth project id>
  CURSEFORGE_TOKEN=<your curseforge token>
  CURSEFORGE_ID=<your curseforge project id>
```

After reloading the gradle project, you can publish your mod by running the `publishing/modrinth` 
and `upload/curseforge` task.

If you only want to publish to one platform, you can leave the other fields empty.

## Adding changelogs to your published versions

Create a directory named `changelogs` in the root directory of your project.
The directory should contain a file for each version you want to add a changelog to.
Each file should be named `<version>.md` and should contain the changelog in Markdown format, e.g:

`changelogs/1.0.0.md`
```markdown
  # 1.0.0

  - added feature
  - fixed bug
```

The changelog will be automatically included when you publish your mod, so make sure to create it beforehand.

## Modrinth project page

The `PROJECT.md` file in the root directory is used as the project page on Modrinth. 
You can use Markdown to format it. To update the page with the file contents, run the `publishing/modrinthSyncBody` task.

Unfortunately, there is no way to update the project page on Curseforge this way, so you have to do it manually.
