package com.github.mim1q.seasonalspice.init;

import com.github.mim1q.seasonalspice.SeasonalSpice;
import com.github.mim1q.seasonalspice.client.colors.FallLeavesColors;
import com.github.mim1q.seasonalspice.item.WateringCanItem;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;

public class SeasonalSpiceRender {
  public static void init() { }

  public static void initBlocks() {
    BlockRenderLayerMap.INSTANCE.putBlocks(
      RenderLayer.getCutout(),
      SeasonalSpiceBlocks.RED_PLASTIC_SHOVEL,
      SeasonalSpiceBlocks.BLUE_PLASTIC_SHOVEL,
      SeasonalSpiceBlocks.YELLOW_PLASTIC_SHOVEL,
      SeasonalSpiceBlocks.HAYSTACK_BLOCK,
      SeasonalSpiceBlocks.WATERING_CAN,
      SeasonalSpiceBlocks.LEAF_PILE,
      SeasonalSpiceBlocks.YELLOW_LEAF_PILE,
      SeasonalSpiceBlocks.ORANGE_LEAF_PILE,
      SeasonalSpiceBlocks.RED_LEAF_PILE,
      SeasonalSpiceBlocks.BROWN_LEAF_PILE
    );

    ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x00AAFF, SeasonalSpiceBlocks.WATERING_CAN);
    ColorProviderRegistry.BLOCK.register(
      FallLeavesColors::getColor,
      SeasonalSpiceBlocks.LEAF_PILE,
      SeasonalSpiceBlocks.YELLOW_LEAF_PILE,
      SeasonalSpiceBlocks.ORANGE_LEAF_PILE,
      SeasonalSpiceBlocks.RED_LEAF_PILE,
      SeasonalSpiceBlocks.BROWN_LEAF_PILE
    );
  }

  public static void initItems() {
    ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x00AAFF, SeasonalSpiceItems.WATERING_CAN);
    ColorProviderRegistry.ITEM.register(
      FallLeavesColors::getColor,
      SeasonalSpiceBlocks.LEAF_PILE,
      SeasonalSpiceBlocks.YELLOW_LEAF_PILE,
      SeasonalSpiceBlocks.ORANGE_LEAF_PILE,
      SeasonalSpiceBlocks.RED_LEAF_PILE,
      SeasonalSpiceBlocks.BROWN_LEAF_PILE
    );

    ModelPredicateProviderRegistry.register(
      SeasonalSpiceItems.WATERING_CAN,
      SeasonalSpice.id("filled"),
      (stack, world, entity, seed) -> WateringCanItem.canWater(stack) ? 1 : 0
    );
  }
}
