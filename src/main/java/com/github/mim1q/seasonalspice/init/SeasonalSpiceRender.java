package com.github.mim1q.seasonalspice.init;

import com.github.mim1q.seasonalspice.SeasonalSpice;
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
      SeasonalSpiceBlocks.WATERING_CAN
    );

    ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x00AAFF, SeasonalSpiceBlocks.WATERING_CAN);
  }

  public static void initItems() {
    ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x00AAFF, SeasonalSpiceItems.WATERING_CAN);

    ModelPredicateProviderRegistry.register(
      SeasonalSpiceItems.WATERING_CAN,
      SeasonalSpice.id("filled"),
      (stack, world, entity, seed) -> WateringCanItem.canWater(stack) ? 1 : 0
    );
  }
}
