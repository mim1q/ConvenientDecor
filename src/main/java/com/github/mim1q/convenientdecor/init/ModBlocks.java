package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.block.HaystackBlock;
import com.github.mim1q.convenientdecor.block.PitchforkBlock;
import com.github.mim1q.convenientdecor.block.UmbrellaBlock;
import com.github.mim1q.convenientdecor.block.WateringCanBlock;
import com.github.mim1q.convenientdecor.init.group.ColoredGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.HayBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
  public static final PitchforkBlock PITCHFORK = registerWithSimpleItem(new PitchforkBlock(FabricBlockSettings.of(Material.METAL)), "pitchfork");
  public static final PitchforkBlock SHOVEL = registerWithSimpleItem(new PitchforkBlock(FabricBlockSettings.of(Material.METAL)), "shovel");
  public static final PitchforkBlock RED_PLASTIC_SHOVEL = registerWithSimpleItem(new PitchforkBlock(FabricBlockSettings.of(Material.METAL)), "red_plastic_shovel");
  public static final PitchforkBlock BLUE_PLASTIC_SHOVEL = registerWithSimpleItem(new PitchforkBlock(FabricBlockSettings.of(Material.METAL)), "blue_plastic_shovel");
  public static final PitchforkBlock YELLOW_PLASTIC_SHOVEL = registerWithSimpleItem(new PitchforkBlock(FabricBlockSettings.of(Material.METAL)), "yellow_plastic_shovel");
  public static final WateringCanBlock WATERING_CAN = register(new WateringCanBlock(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.METAL)), "watering_can");
  public static final HayBlock UNBUNDLED_HAY_BLOCK = registerWithSimpleItem(new HayBlock(FabricBlockSettings.copy(net.minecraft.block.Blocks.HAY_BLOCK)), "unbundled_hay_block");
  public static final HaystackBlock HAYSTACK_BLOCK = registerWithSimpleItem(new HaystackBlock(FabricBlockSettings.copy(net.minecraft.block.Blocks.HAY_BLOCK)), "haystack_block");
  public static final HaystackBlock LEAF_PILE = registerWithSimpleItem(new HaystackBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).sounds(BlockSoundGroup.GRASS)), "leaf_pile");
  public static final HaystackBlock YELLOW_LEAF_PILE = registerWithSimpleItem(new HaystackBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).sounds(BlockSoundGroup.GRASS)), "yellow_leaf_pile");
  public static final HaystackBlock ORANGE_LEAF_PILE = registerWithSimpleItem(new HaystackBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).sounds(BlockSoundGroup.GRASS)), "orange_leaf_pile");
  public static final HaystackBlock RED_LEAF_PILE = registerWithSimpleItem(new HaystackBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).sounds(BlockSoundGroup.GRASS)), "red_leaf_pile");
  public static final HaystackBlock BROWN_LEAF_PILE = registerWithSimpleItem(new HaystackBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).sounds(BlockSoundGroup.GRASS)), "brown_leaf_pile");

  public static final ColoredGroup.ColoredBlockGroup UMBRELLA = ColoredGroup.ofBlocks(true)
    .add16Colors((color) -> new UmbrellaBlock(FabricBlockSettings.of(Material.WOOL).breakInstantly(), color))
    .register("umbrella");

  public static void init() { }

  public static <T extends Block> T registerWithSimpleItem(T block, String name) {
    Registry.register(Registry.ITEM, ConvenientDecor.id(name), new BlockItem(block, new FabricItemSettings()));
    return register(block, name);
  }

  public static <T extends Block> T register(T block, String name) {
    Registry.register(Registry.BLOCK, ConvenientDecor.id(name), block);
    return block;
  }
}
