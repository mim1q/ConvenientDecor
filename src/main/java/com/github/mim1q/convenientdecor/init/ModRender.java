package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.client.colors.FallLeavesColors;
import com.github.mim1q.convenientdecor.client.render.umbrella.UmbrellaBlockEntityRenderer;
import com.github.mim1q.convenientdecor.item.WateringCanItem;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;

public class ModRender {
  public static void init() { }

  public static void initBlocks() {
    BlockEntityRendererRegistry.register(ModBlockEntities.UMBRELLA, UmbrellaBlockEntityRenderer::new);

    BlockRenderLayerMap.INSTANCE.putBlocks(
      RenderLayer.getCutout(),
      ModBlocks.RED_PLASTIC_SHOVEL,
      ModBlocks.BLUE_PLASTIC_SHOVEL,
      ModBlocks.YELLOW_PLASTIC_SHOVEL,
      ModBlocks.HAYSTACK_BLOCK,
      ModBlocks.WATERING_CAN,
      ModBlocks.LEAF_PILE,
      ModBlocks.YELLOW_LEAF_PILE,
      ModBlocks.ORANGE_LEAF_PILE,
      ModBlocks.RED_LEAF_PILE,
      ModBlocks.BROWN_LEAF_PILE
    );

    ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x00AAFF, ModBlocks.WATERING_CAN);
    ColorProviderRegistry.BLOCK.register(
      FallLeavesColors::getColor,
      ModBlocks.LEAF_PILE,
      ModBlocks.YELLOW_LEAF_PILE,
      ModBlocks.ORANGE_LEAF_PILE,
      ModBlocks.RED_LEAF_PILE,
      ModBlocks.BROWN_LEAF_PILE
    );
  }

  public static void initItems() {
    ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x00AAFF, ModItems.WATERING_CAN);
    ColorProviderRegistry.ITEM.register(
      FallLeavesColors::getColor,
      ModBlocks.LEAF_PILE,
      ModBlocks.YELLOW_LEAF_PILE,
      ModBlocks.ORANGE_LEAF_PILE,
      ModBlocks.RED_LEAF_PILE,
      ModBlocks.BROWN_LEAF_PILE
    );

    ModelPredicateProviderRegistry.register(
      ModItems.WATERING_CAN,
      ConvenientDecor.id("filled"),
      (stack, world, entity, seed) -> WateringCanItem.canWater(stack) ? 1 : 0
    );
  }
}
