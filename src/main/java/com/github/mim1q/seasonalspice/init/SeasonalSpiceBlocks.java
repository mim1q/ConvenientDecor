package com.github.mim1q.seasonalspice.init;

import com.github.mim1q.seasonalspice.SeasonalSpice;
import com.github.mim1q.seasonalspice.block.PitchforkBlock;
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
  public static final HayBlock UNBUNDLED_HAY_BLOCK = registerWithSimpleItem(new HayBlock(Settings.copy(Blocks.HAY_BLOCK)), "unbundled_hay_block");

  public static void init() { }

  public static <B extends Block> B registerWithSimpleItem(B block, String name) {
    SeasonalSpiceItems.register(new BlockItem(block, new FabricItemSettings()), name);
    return register(block, name);
  }

  public static <T extends Block> T register(T block, String name) {
    Registry.register(Registry.BLOCK, SeasonalSpice.id(name), block);
    return block;
  }
}
