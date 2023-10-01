package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.block.*;
import com.github.mim1q.convenientdecor.init.group.ColoredGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockSettingsAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HayBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;

public class ModBlocks {
  public static FabricBlockSettings metalSettings() {
    return FabricBlockSettings.create()
      .sounds(BlockSoundGroup.METAL)
      .strength(4.0F, 5.0F);
  }

  public static final PitchforkBlock PITCHFORK = registerWithSimpleItem(new PitchforkBlock(metalSettings().breakInstantly()), "pitchfork");
  public static final WateringCanBlock WATERING_CAN = register(new WateringCanBlock(metalSettings().breakInstantly()), "watering_can");
  public static final HayBlock UNBUNDLED_HAY_BLOCK = registerWithSimpleItem(new HayBlock(FabricBlockSettings.copy(Blocks.HAY_BLOCK)), "unbundled_hay_block");
  public static final HaystackBlock HAYSTACK_BLOCK = registerLeafPile("haystack_block");

  public static final HaystackBlock LEAF_PILE = registerLeafPile("leaf_pile");
  public static final HaystackBlock YELLOW_LEAF_PILE = registerLeafPile("yellow_leaf_pile");
  public static final HaystackBlock ORANGE_LEAF_PILE = registerLeafPile("orange_leaf_pile");
  public static final HaystackBlock RED_LEAF_PILE = registerLeafPile("red_leaf_pile");
  public static final HaystackBlock BROWN_LEAF_PILE = registerLeafPile("brown_leaf_pile");
  public static final HaystackBlock SPRUCE_LEAF_PILE = registerLeafPile("spruce_leaf_pile");
  public static final HaystackBlock BIRCH_LEAF_PILE = registerLeafPile("birch_leaf_pile");
  public static final HaystackBlock JUNGLE_LEAF_PILE = registerLeafPile("jungle_leaf_pile");
  public static final HaystackBlock ACACIA_LEAF_PILE = registerLeafPile("acacia_leaf_pile");
  public static final HaystackBlock DARK_OAK_LEAF_PILE = registerLeafPile("dark_oak_leaf_pile");
  public static final HaystackBlock MANGROVE_LEAF_PILE = registerLeafPile("mangrove_leaf_pile");
  public static final HaystackBlock AZALEA_LEAF_PILE = registerLeafPile("azalea_leaf_pile");
  public static final HaystackBlock FLOWERING_AZALEA_LEAF_PILE = registerLeafPile("flowering_azalea_leaf_pile");
  public static final HaystackBlock CHERRY_LEAF_PILE = registerLeafPile("cherry_leaf_pile");

  public static final PitchforkBlock SHOVEL = registerWithSimpleItem(new PitchforkBlock(metalSettings()), "shovel");
  public static final ColoredGroup.ColoredBlockGroup PLASTIC_SHOVEL = ColoredGroup.ofBlocks(true)
    .add16Colors((color) -> new PitchforkBlock(FabricBlockSettings.copy(SHOVEL)))
    .register("plastic_shovel");

  public static final ColoredGroup.ColoredBlockGroup UMBRELLA = ColoredGroup.ofBlocks(false)
    .add16Colors((color) -> new UmbrellaBlock(metalSettings().breakInstantly().mapColor(color), color))
    .register("umbrella");

  public static final UmbrellaBlock BROKEN_UMBRELLA = register(new UmbrellaBlock(FabricBlockSettings.copy(UMBRELLA.get(DyeColor.RED)), DyeColor.BLACK),"broken_umbrella");
  public static final UmbrellaBlock ALLERTS_UMBRELLA = register(new UmbrellaBlock(FabricBlockSettings.copy(UMBRELLA.get(DyeColor.RED)), DyeColor.BLACK),"allerts_umbrella");
  public static final UmbrellaStandBlock UMBRELLA_STAND = registerWithSimpleItem(new UmbrellaStandBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).breakInstantly()), "umbrella_stand");
  public static final WeatherVaneBlock GOLD_WEATHER_VANE = registerWithSimpleItem(new WeatherVaneBlock(40, FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).breakInstantly().noCollision().nonOpaque()), "gold_weather_vane");
  public static final WeatherVaneBlock COPPER_WEATHER_VANE = registerWithSimpleItem(new WeatherVaneBlock(100, FabricBlockSettings.copyOf(GOLD_WEATHER_VANE)), "copper_weather_vane");
  public static final WeatherVaneBlock IRON_WEATHER_VANE = registerWithSimpleItem(new WeatherVaneBlock(400, FabricBlockSettings.copyOf(GOLD_WEATHER_VANE)), "iron_weather_vane");
  public static final WeatherVaneBlock NETHERITE_WEATHER_VANE = registerWithSimpleItem(new WeatherVaneBlock(1200, FabricBlockSettings.copyOf(GOLD_WEATHER_VANE)), "netherite_weather_vane");

  public static void init() { }

  public static <T extends Block> T registerWithSimpleItem(T block, String name) {
    Registry.register(Registries.ITEM, ConvenientDecor.id(name), new BlockItem(block, new FabricItemSettings()));
    return register(block, name);
  }

  public static <T extends Block> T register(T block, String name) {
    Registry.register(Registries.BLOCK, ConvenientDecor.id(name), block);
    return block;
  }

  private static HaystackBlock registerLeafPile(String name) {
    return registerWithSimpleItem(new HaystackBlock(
      noZOffset(FabricBlockSettings
        .copyOf(Blocks.OAK_LEAVES)
        .strength(0.2F)
        .sounds(BlockSoundGroup.GRASS)
        .noCollision(),
        true
      )
    ), name);
  }

  @SuppressWarnings("UnstableApiUsage")
  private static FabricBlockSettings noZOffset(FabricBlockSettings settings, boolean additionalOffset) {
    if (additionalOffset) {
      ((AbstractBlockSettingsAccessor) settings).setOffsetter(Optional.of((state, world, pos) -> {
        var block = state.getBlock();
        @SuppressWarnings("deprecation") var l = MathHelper.hashCode(pos);
        var maxXzOffset = block.getMaxHorizontalModelOffset();
        var maxYOffset = maxXzOffset * block.getVerticalModelOffsetMultiplier();
        var x = MathHelper.clamp(((double)((float)(l & 15L) / 15.0F) - 0.5) * 0.5, -maxXzOffset, maxXzOffset);
        var y =  MathHelper.clamp(((double)((float)(l >> 4 & 15L) / 15.0F) - 0.5) * 0.5, -maxYOffset, maxYOffset);
        var z = MathHelper.clamp(((double)((float)(l >> 8 & 15L) / 15.0F) - 0.5) * 0.5, -maxXzOffset, maxXzOffset);
        var baseOffset = new Vec3d(x, y, z);
        return baseOffset.add(getZFightingOffset(pos));
      }));
    } else {
      ((AbstractBlockSettingsAccessor) settings).setOffsetter(Optional.of((state, world, pos) -> getZFightingOffset(pos)));
    }
    return settings;
  }

  private static Vec3d getZFightingOffset(BlockPos pos) {
    var x = pos.getX() % 3;
    var y = pos.getY() % 3;
    var z = pos.getZ() % 3;
    return new Vec3d(
      (z * 0.001) + (y * 0.0005) + 0.0005,
      (x * 0.001) + (z * 0.0005) + 0.0005,
      (y * 0.001) + (x * 0.0005) + 0.0005
    );
  }
}
