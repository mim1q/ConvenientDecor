package datagen

import datagen.custom.CustomPresets.leafPile
import datagen.custom.CustomPresets.raincoatSet
import datagen.custom.CustomPresets.shovel
import datagen.custom.CustomPresets.umbrella
import datagen.custom.CustomPresets.weatherVane
import datagen.custom.MinecraftColor
import datagen.custom.customRecipes
import tada.lib.generator.ResourceGenerator
import tada.lib.presets.common.CommonDropPresets
import tada.lib.presets.common.CommonModelPresets
import tada.lib.resources.blockstate.BlockState
import tada.lib.resources.recipe.CraftingRecipe
import java.nio.file.Path

fun main(args: Array<String>) {
  if (args.size != 3) {
    throw IllegalArgumentException("Must provide an output directory, lang directory and helper lang directory.")
  }
  val path = Path.of(args[0]).toAbsolutePath()
  val helperLangPath = Path.of(args[2]).toAbsolutePath()
  println(
    "Running datagen script." +
      "\n  Output directory: $path" +
      "\n  Generated language helper directory: $helperLangPath"
  )
  val generator = ResourceGenerator.create("convenientdecor", path).apply {
    MinecraftColor.forEach { color ->
      add(shovel("convenientdecor:${color}_plastic_shovel", color))
      add(umbrella("convenientdecor:${color}_umbrella", color))
      add(raincoatSet("convenientdecor", color))
    }
    add(umbrella("convenientdecor:broken_umbrella", functional = false))
    add(umbrella("convenientdecor:allerts_umbrella"))

    add("umbrella_stand", BlockState.createSingle("convenientdecor:block/umbrella_stand"))
    add(CommonModelPresets.itemBlockModel("convenientdecor:umbrella_stand"))
    add(CommonDropPresets.simpleDrop("convenientdecor:umbrella_stand"))

    listOf("gold", "copper", "iron", "netherite").forEach { material ->
      add(weatherVane("convenientdecor", material))
    }

    add(leafPile("convenientdecor:leaf_pile", "minecraft:block/oak_leaves", "convenientdecor:block/leaf_pile/oak", true, "minecraft:oak_leaves"))
    add(leafPile("convenientdecor:haystack_block", "convenientdecor:block/unbundled_hay_block_top", "convenientdecor:block/hay_stack", false, "convenientdecor:unbundled_hay_block"))
    listOf("yellow", "orange", "red", "brown").forEach {
      add(leafPile("convenientdecor:${it}_leaf_pile", "minecraft:block/oak_leaves", "convenientdecor:block/leaf_pile/oak", true))
      add("${it}_leaf_pile", CraftingRecipe.shapeless("convenientdecor:${it}_leaf_pile") {
        ingredient("convenientdecor:leaf_pile")
        ingredient("minecraft:${it}_dye")
      })
    }
    listOf("spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove").forEach {
      add(leafPile("convenientdecor:${it}_leaf_pile", "minecraft:block/${it}_leaves", "convenientdecor:block/leaf_pile/$it", true, "minecraft:${it}_leaves"))
    }
    listOf("azalea", "flowering_azalea").forEach {
      add(leafPile("convenientdecor:${it}_leaf_pile", "minecraft:block/${it}_leaves", "convenientdecor:block/leaf_pile/$it", false, "minecraft:${it}_leaves"))
    }

    add(customRecipes())
  }
  generator.generate()
}