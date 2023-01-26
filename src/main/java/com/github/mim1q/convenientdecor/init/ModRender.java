package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.client.colors.FallLeavesColors;
import com.github.mim1q.convenientdecor.client.render.clothes.RaincoatRenderer;
import com.github.mim1q.convenientdecor.client.render.umbrella.UmbrellaBlockEntityRenderer;
import com.github.mim1q.convenientdecor.client.render.umbrella.UmbrellaItemRenderer;
import com.github.mim1q.convenientdecor.client.render.umbrella.UmbrellaStandBlockEntityRenderer;
import com.github.mim1q.convenientdecor.item.WateringCanItem;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;

public class ModRender {
  public static void init() { }

  public static void initBlocks() {
    BlockEntityRendererRegistry.register(ModBlockEntities.UMBRELLA, UmbrellaBlockEntityRenderer::new);
    BlockEntityRendererRegistry.register(ModBlockEntities.UMBRELLA_STAND, UmbrellaStandBlockEntityRenderer::new);

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

  public static void initArmorRenderers(EntityRendererFactory.Context context) {
    ArmorRenderer.register(new RaincoatRenderer(context.getModelLoader()), ModItems.RAINCOAT.get(DyeColor.YELLOW));
  }

  public static void initDynamicItemRenderers(EntityRendererFactory.Context context) {
    UmbrellaItemRenderer umbrellaItemRenderer = new UmbrellaItemRenderer(context.getModelLoader());
    for (Item item : ModItems.UMBRELLA.getList()) {
      BuiltinItemRendererRegistry.INSTANCE.register(item, umbrellaItemRenderer);
    }
    BuiltinItemRendererRegistry.INSTANCE.register(ModItems.BROKEN_UMBRELLA, umbrellaItemRenderer);
  }
}
