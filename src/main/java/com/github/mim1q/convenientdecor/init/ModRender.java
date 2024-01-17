package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.client.colors.FallLeavesColors;
import com.github.mim1q.convenientdecor.client.render.blockentity.PlushieBlockEntityRenderer;
import com.github.mim1q.convenientdecor.client.render.blockentity.WeatherVaneBlockEntityRenderer;
import com.github.mim1q.convenientdecor.client.render.clothes.RaincoatRenderer;
import com.github.mim1q.convenientdecor.client.render.umbrella.UmbrellaBlockEntityRenderer;
import com.github.mim1q.convenientdecor.client.render.umbrella.UmbrellaItemRenderer;
import com.github.mim1q.convenientdecor.client.render.umbrella.UmbrellaStandBlockEntityRenderer;
import com.github.mim1q.convenientdecor.item.RaincoatItem;
import com.github.mim1q.convenientdecor.item.WateringCanItem;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.item.Item;

public class ModRender {
  public static void init() { }

  public static void initBlocks() {
    BlockEntityRendererFactories.register(ModBlockEntities.UMBRELLA, UmbrellaBlockEntityRenderer::new);
    BlockEntityRendererFactories.register(ModBlockEntities.UMBRELLA_STAND, UmbrellaStandBlockEntityRenderer::new);
    BlockEntityRendererFactories.register(ModBlockEntities.WEATHER_VANE, WeatherVaneBlockEntityRenderer::new);
    BlockEntityRendererFactories.register(ModBlockEntities.PLUSHIE, PlushieBlockEntityRenderer::new);

    BlockRenderLayerMap.INSTANCE.putBlocks(
      RenderLayer.getCutout(),
      ModBlocks.HAYSTACK_BLOCK,
      ModBlocks.WATERING_CAN,
      ModBlocks.LEAF_PILE,
      ModBlocks.YELLOW_LEAF_PILE,
      ModBlocks.ORANGE_LEAF_PILE,
      ModBlocks.RED_LEAF_PILE,
      ModBlocks.BROWN_LEAF_PILE,
      ModBlocks.SPRUCE_LEAF_PILE,
      ModBlocks.BIRCH_LEAF_PILE,
      ModBlocks.JUNGLE_LEAF_PILE,
      ModBlocks.ACACIA_LEAF_PILE,
      ModBlocks.DARK_OAK_LEAF_PILE,
      ModBlocks.MANGROVE_LEAF_PILE,
      ModBlocks.AZALEA_LEAF_PILE,
      ModBlocks.FLOWERING_AZALEA_LEAF_PILE,
      ModBlocks.CHERRY_LEAF_PILE
    );
    for (Block block : ModBlocks.PLASTIC_SHOVEL.getList()) {
      BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), block);
    }

    ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x00AAFF, ModBlocks.WATERING_CAN);
    ColorProviderRegistry.BLOCK.register(
      FallLeavesColors::getColor,
      ModBlocks.LEAF_PILE,
      ModBlocks.YELLOW_LEAF_PILE,
      ModBlocks.ORANGE_LEAF_PILE,
      ModBlocks.RED_LEAF_PILE,
      ModBlocks.BROWN_LEAF_PILE,
      ModBlocks.JUNGLE_LEAF_PILE,
      ModBlocks.ACACIA_LEAF_PILE,
      ModBlocks.DARK_OAK_LEAF_PILE,
      ModBlocks.MANGROVE_LEAF_PILE
    );
    ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.getSpruceColor(), ModBlocks.SPRUCE_LEAF_PILE);
    ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.getBirchColor(), ModBlocks.BIRCH_LEAF_PILE);
  }

  public static void initItems() {
    ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x00AAFF, ModItems.WATERING_CAN);
    ColorProviderRegistry.ITEM.register(
      FallLeavesColors::getColor,
      ModBlocks.LEAF_PILE,
      ModBlocks.YELLOW_LEAF_PILE,
      ModBlocks.ORANGE_LEAF_PILE,
      ModBlocks.RED_LEAF_PILE,
      ModBlocks.BROWN_LEAF_PILE,
      ModBlocks.JUNGLE_LEAF_PILE,
      ModBlocks.ACACIA_LEAF_PILE,
      ModBlocks.DARK_OAK_LEAF_PILE,
      ModBlocks.MANGROVE_LEAF_PILE
    );
    ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getSpruceColor(), ModBlocks.SPRUCE_LEAF_PILE);
    ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getBirchColor(), ModBlocks.BIRCH_LEAF_PILE);

    ModelPredicateProviderRegistry.register(
      ModItems.WATERING_CAN,
      ConvenientDecor.id("filled"),
      (stack, world, entity, seed) -> WateringCanItem.canWater(stack) ? 1 : 0
    );

    for (Item item : ModItems.RAINCOAT.getList()) {
      ModelPredicateProviderRegistry.register(
        item,
        ConvenientDecor.id("hood"),
        (stack, world, entity, seed) -> RaincoatItem.isHooded(stack) ? 1 : 0
      );
    }

  }

  public static void initArmorRenderers(EntityRendererFactory.Context context) {
    RaincoatRenderer raincoatRenderer = new RaincoatRenderer(context.getModelLoader());
    for (Item item : ModItems.RAINCOAT.getList()) {
      ArmorRenderer.register(raincoatRenderer, item);
    }
    for (Item item : ModItems.RAIN_HAT.getList()) {
      ArmorRenderer.register(raincoatRenderer, item);
    }
    for (Item item : ModItems.RAIN_BOOTS.getList()) {
      ArmorRenderer.register(raincoatRenderer, item);
    }
  }

  public static void initDynamicItemRenderers(EntityRendererFactory.Context context) {
    UmbrellaItemRenderer umbrellaItemRenderer = new UmbrellaItemRenderer(context.getModelLoader());
    for (Item item : ModItems.UMBRELLA.getList()) {
      BuiltinItemRendererRegistry.INSTANCE.register(item, umbrellaItemRenderer);
    }
    BuiltinItemRendererRegistry.INSTANCE.register(ModItems.BROKEN_UMBRELLA, umbrellaItemRenderer);
    BuiltinItemRendererRegistry.INSTANCE.register(ModItems.ALLERTS_UMBRELLA, umbrellaItemRenderer);
  }
}
