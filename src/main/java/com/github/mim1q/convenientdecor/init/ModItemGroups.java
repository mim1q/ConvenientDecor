package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;

import java.util.List;

public class ModItemGroups {
  public static final ItemGroup SPRING_SPICE = FabricItemGroupBuilder.create(ConvenientDecor.id("spring_spice"))
    .icon(() -> Blocks.OAK_SAPLING.asItem().getDefaultStack())
    .appendItems(stacks -> stacks.addAll(List.of(
      ModBlocks.SHOVEL.asItem().getDefaultStack(),
      ModItems.WATERING_CAN.getDefaultStack(),
      ModItems.WATERING_CAN.getStack(32)
    )))
    .build();

  public static final ItemGroup SUMMER_SPICE = FabricItemGroupBuilder.create(ConvenientDecor.id("summer_spice"))
    .icon(() -> Blocks.SUNFLOWER.asItem().getDefaultStack())
    .appendItems(stacks -> stacks.addAll(List.of(
      ModBlocks.UNBUNDLED_HAY_BLOCK.asItem().getDefaultStack(),
      ModBlocks.HAYSTACK_BLOCK.asItem().getDefaultStack(),
      ModBlocks.RED_PLASTIC_SHOVEL.asItem().getDefaultStack(),
      ModBlocks.YELLOW_PLASTIC_SHOVEL.asItem().getDefaultStack(),
      ModBlocks.BLUE_PLASTIC_SHOVEL.asItem().getDefaultStack(),
      ModBlocks.PITCHFORK.asItem().getDefaultStack()
    )))
    .build();

  public static final ItemGroup AUTUMN_SPICE = FabricItemGroupBuilder.create(ConvenientDecor.id("autumn_spice"))
    .icon(() -> Blocks.PUMPKIN.asItem().getDefaultStack())
    .appendItems(stacks -> stacks.addAll(List.of(
      ModBlocks.LEAF_PILE.asItem().getDefaultStack(),
      ModBlocks.YELLOW_LEAF_PILE.asItem().getDefaultStack(),
      ModBlocks.ORANGE_LEAF_PILE.asItem().getDefaultStack(),
      ModBlocks.RED_LEAF_PILE.asItem().getDefaultStack(),
      ModBlocks.BROWN_LEAF_PILE.asItem().getDefaultStack()
    )))
    .build();

  public static final ItemGroup WINTER_SPICE = FabricItemGroupBuilder.create(ConvenientDecor.id("winter_spice"))
    .icon(() -> Items.SNOWBALL.getDefaultStack())
    .build();

  public static void init() { }
}
