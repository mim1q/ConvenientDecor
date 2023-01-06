package com.github.mim1q.seasonalspice.init;

import com.github.mim1q.seasonalspice.SeasonalSpice;
import com.github.mim1q.seasonalspice.block.HaystackBlock;
import com.github.mim1q.seasonalspice.block.PitchforkBlock;
import com.github.mim1q.seasonalspice.block.WateringCanBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HayBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;

public class SeasonalSpiceBlocks {

  public static final PitchforkBlock PITCHFORK = registerWithSimpleItem(new PitchforkBlock(Settings.copy(Blocks.IRON_BLOCK)), "pitchfork");
  public static final PitchforkBlock SHOVEL = registerWithSimpleItem(new PitchforkBlock(Settings.copy(Blocks.IRON_BLOCK)), "shovel");
  public static final PitchforkBlock RED_PLASTIC_SHOVEL = registerWithSimpleItem(new PitchforkBlock(Settings.copy(Blocks.IRON_BLOCK)), "red_plastic_shovel");
  public static final PitchforkBlock BLUE_PLASTIC_SHOVEL = registerWithSimpleItem(new PitchforkBlock(Settings.copy(Blocks.IRON_BLOCK)), "blue_plastic_shovel");
  public static final PitchforkBlock YELLOW_PLASTIC_SHOVEL = registerWithSimpleItem(new PitchforkBlock(Settings.copy(Blocks.IRON_BLOCK)), "yellow_plastic_shovel");
  public static final WateringCanBlock WATERING_CAN = register(new WateringCanBlock(Settings.copy(Blocks.IRON_BLOCK)), "watering_can");
  public static final HayBlock UNBUNDLED_HAY_BLOCK = registerWithSimpleItem(new HayBlock(Settings.copy(Blocks.HAY_BLOCK)), "unbundled_hay_block");
  public static final HaystackBlock HAYSTACK_BLOCK = registerWithSimpleItem(new HaystackBlock(Settings.copy(Blocks.HAY_BLOCK)), "haystack_block");
  public static final HaystackBlock LEAF_PILE = registerWithSimpleItem(new HaystackBlock(Settings.copy(Blocks.OAK_LEAVES)), "leaf_pile");
  public static final HaystackBlock YELLOW_LEAF_PILE = registerWithSimpleItem(new HaystackBlock(Settings.copy(Blocks.OAK_LEAVES)), "yellow_leaf_pile");
  public static final HaystackBlock ORANGE_LEAF_PILE = registerWithSimpleItem(new HaystackBlock(Settings.copy(Blocks.OAK_LEAVES)), "orange_leaf_pile");
  public static final HaystackBlock RED_LEAF_PILE = registerWithSimpleItem(new HaystackBlock(Settings.copy(Blocks.OAK_LEAVES)), "red_leaf_pile");
  public static final HaystackBlock BROWN_LEAF_PILE = registerWithSimpleItem(new HaystackBlock(Settings.copy(Blocks.OAK_LEAVES)), "brown_leaf_pile");

  public static void init() { }

  public static <T extends Block> T registerWithSimpleItem(T block, String name) {
    Registry.register(Registry.ITEM, SeasonalSpice.id(name), new BlockItem(block, new FabricItemSettings()));
    return register(block, name);
  }

  public static <T extends Block> T register(T block, String name) {
    Registry.register(Registry.BLOCK, SeasonalSpice.id(name), block);
    return block;
  }
}
