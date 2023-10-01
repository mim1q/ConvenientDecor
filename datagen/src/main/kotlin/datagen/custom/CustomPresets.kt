package datagen.custom

import datagen.custom.util.frontLight
import datagen.custom.util.horizontalFacing
import datagen.custom.util.overrides
import tada.lib.presets.Preset
import tada.lib.presets.common.CommonDropPresets
import tada.lib.presets.common.CommonModelPresets
import tada.lib.resources.blockstate.BlockState
import tada.lib.resources.blockstate.BlockStateModel
import tada.lib.resources.blockstate.BlockStateModel.Rotation
import tada.lib.resources.model.ParentedModel
import tada.lib.resources.recipe.CraftingRecipe
import tada.lib.tags.TagManager
import tada.lib.util.Id

object CustomPresets {
  fun shovel(id: String, color: MinecraftColor? = null) = Preset {
    val (ns, name) = Id(id)

    val texture = "$ns:block/shovel/$name"
    val variants = listOf("lying", "tilted", "straight")
    variants.forEach { variant ->
      add("$name/$variant", ParentedModel.block("convenientdecor:block/shovel/$variant") {
        texture("0", texture)
        texture("particle", texture)
      })
    }
    add(name, BlockState.create {
      variants.forEach { variant ->
        horizontalFacing("type=$variant", "$ns:block/$name/$variant")
      }
    })
    add(name, ParentedModel.item("$ns:block/$name/straight"))
    if (color != null) {
      add(name, CraftingRecipe.shapeless(id) {
        ingredient("convenientdecor:shovel")
        ingredient(color.getDye())
      })
    }
    add(CommonDropPresets.simpleDrop(id))
  }

  fun umbrella(id: String, color: MinecraftColor? = null, functional: Boolean = true) = Preset {
    val (_, name) = Id(id)

    add(name, BlockState.createSingle("minecraft:block/${color ?: "gray"}_wool"))
    add(name, ParentedModel.item("minecraft:builtin/entity").frontLight())
    if (color != null) {
      val woolName = "minecraft:${color}_wool"
      add(name, CraftingRecipe.shaped(id) {
        pattern("WWW")
        pattern("BIB")
        pattern(" I ")
        key("W", woolName)
        key("B", "minecraft:iron_bars")
        key("I", "minecraft:iron_ingot")
      })
    }
    if (functional) {
      TagManager.add("convenientdecor:items/functional_umbrellas", id)
    }
  }

  fun raincoatSet(ns: String, color: MinecraftColor) = Preset {
    listOf("raincoat_hood", "rain_hat", "rain_boots").forEach {
      add("${color}_$it", ParentedModel.item("minecraft:item/generated") {
        texture("layer0", "$ns:item/$it/$color")
      })
    }

    add("${color}_raincoat", ParentedModel.item("minecraft:item/generated") {
      texture("layer0", "$ns:item/raincoat/$color")
      overrides("convenientdecor:hood" to "$ns:item/${color}_raincoat_hood")
    })

    listOf("raincoat" to "chestplate", "rain_hat" to "helmet", "rain_boots" to "boots").forEach {
      add("${color}_${it.first}", CraftingRecipe.shapeless("$ns:${color}_${it.first}") {
        ingredient("minecraft:leather_${it.second}")
        ingredient("minecraft:${color}_wool")
      })
    }

    TagManager.add("convenientdecor:items/raincoats", "$ns:${color}_raincoat")
    TagManager.add("convenientdecor:items/rain_hats", "$ns:${color}_rain_hat")
  }

  fun weatherVane(ns: String, material: String) = Preset {
    val name = "${material}_weather_vane"
    add(name, ParentedModel.block("convenientdecor:block/weather_vane") {
      texture("0", "$ns:block/weather_vane/$material")
      texture("particle", "minecraft:block/${material}_block")
    })
    add(name, ParentedModel.item("minecraft:item/generated").texture("layer0", "$ns:item/weather_vane/$material"))
    add(name, BlockState.createSingle("$ns:block/$name"))
    add(CommonDropPresets.simpleDrop("$ns:$name"))
    add(name, CraftingRecipe.shaped("$ns:$name") {
      pattern(" A ")
      pattern("III")
      pattern(" C ")
      key("A", "minecraft:arrow")
      key("I", "minecraft:${material}_ingot")
      key("C", "minecraft:compass")
    })
  }

  fun leafPile(id: String, baseTexture: String, fluffTexture: String, tinted: Boolean = false, baseBlock: String? = null) = Preset {
    val (ns, name) = Id(id)

    add(name, ParentedModel.block("convenientdecor:block/template/${if (tinted) "leaf_pile_tinted" else "leaf_pile"}") {
      texture("0", fluffTexture)
      texture("1", baseTexture)
      texture("particle", baseTexture)
    })
    add(CommonDropPresets.simpleDrop(id))
    add(name, BlockState.create {
      variant(
        "",
        BlockStateModel("$ns:block/$name", yRot = Rotation.NONE),
        BlockStateModel("$ns:block/$name", yRot = Rotation.CW_90),
        BlockStateModel("$ns:block/$name", yRot = Rotation.CW_180),
        BlockStateModel("$ns:block/$name", yRot = Rotation.CW_270)
      )
    })
    add(CommonModelPresets.itemBlockModel(id))
    TagManager.add("minecraft:blocks/mineable/hoe", id)

    if (baseBlock != null) {
      add(name, CraftingRecipe.shaped(id) {
        pattern("L")
        pattern("L")
        key("L", baseBlock)
      })
    }
  }
}

fun customRecipes() = Preset {
  add("allerts_umbrella", CraftingRecipe.shaped("convenientdecor:allerts_umbrella") {
    pattern("RWR")
    pattern("BIB")
    pattern(" I ")
    key("R", "minecraft:red_wool")
    key("W", "minecraft:white_wool")
    key("B", "minecraft:iron_bars")
    key("I", "minecraft:iron_ingot")
  })
}