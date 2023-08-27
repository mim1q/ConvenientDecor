package datagen.custom.util

import com.google.gson.JsonArray
import tada.lib.resources.blockstate.BlockStateModel
import tada.lib.resources.blockstate.BlockStateModel.Rotation
import tada.lib.resources.blockstate.VariantBlockState
import tada.lib.resources.model.ParentedModel
import tada.lib.util.json
import tada.lib.util.jsonArray

val HORIZONTAL_DIRECTIONS = listOf(
  "north" to Rotation.NONE,
  "east" to Rotation.CW_90,
  "south" to Rotation.CW_180,
  "west" to Rotation.CW_270
)

fun VariantBlockState.horizontalFacing(key: String, firstModel: String, vararg otherModels: String) {
  HORIZONTAL_DIRECTIONS.forEach { (direction, rotation) ->
    val directionKey = "facing=$direction"
    variant(
      if (key.isEmpty()) directionKey else "$key,$directionKey",
      BlockStateModel(firstModel, yRot = rotation),
      *otherModels.map { BlockStateModel(it, yRot = rotation) }.toTypedArray()
    )
  }
}

fun ParentedModel.overrides(vararg overrides: Pair<String, String>): ParentedModel {
  this.postProcess {
    add("overrides", JsonArray().apply {
      overrides.forEach { (key, value) ->
        add(json {
          "model" to value
          "predicate" {
            key to 1
          }
        })
      }
    })
  }
  return this
}

fun ParentedModel.frontLight() : ParentedModel {
  this.postProcess {
    addProperty("gui_light", "front")
  }
  return this
}