package com.github.mim1q.seasonalspice.init;

import com.github.mim1q.seasonalspice.SeasonalSpice;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;

import java.util.List;

public class SeasonalSpiceItemGroups {
  public static final ItemGroup SPRING_SPICE = FabricItemGroupBuilder.create(SeasonalSpice.id("spring_spice"))
    .icon(() -> Blocks.OAK_SAPLING.asItem().getDefaultStack())
    .appendItems(stacks -> stacks.addAll(List.of(
      SeasonalSpiceBlocks.SHOVEL.asItem().getDefaultStack(),
      SeasonalSpiceItems.WATERING_CAN.getDefaultStack(),
      SeasonalSpiceItems.WATERING_CAN.getStack(32)
    )))
    .build();

  public static final ItemGroup SUMMER_SPICE = FabricItemGroupBuilder.create(SeasonalSpice.id("summer_spice"))
    .icon(() -> Blocks.SUNFLOWER.asItem().getDefaultStack())
    .appendItems(stacks -> stacks.addAll(List.of(
      SeasonalSpiceBlocks.UNBUNDLED_HAY_BLOCK.asItem().getDefaultStack(),
      SeasonalSpiceBlocks.HAYSTACK_BLOCK.asItem().getDefaultStack(),
      SeasonalSpiceBlocks.RED_PLASTIC_SHOVEL.asItem().getDefaultStack(),
      SeasonalSpiceBlocks.YELLOW_PLASTIC_SHOVEL.asItem().getDefaultStack(),
      SeasonalSpiceBlocks.BLUE_PLASTIC_SHOVEL.asItem().getDefaultStack(),
      SeasonalSpiceBlocks.PITCHFORK.asItem().getDefaultStack()
    )))
    .build();

  public static final ItemGroup AUTUMN_SPICE = FabricItemGroupBuilder.create(SeasonalSpice.id("autumn_spice"))
    .icon(() -> Blocks.PUMPKIN.asItem().getDefaultStack())
    .appendItems(stacks -> stacks.addAll(List.of(
      SeasonalSpiceBlocks.LEAF_PILE.asItem().getDefaultStack(),
      SeasonalSpiceBlocks.YELLOW_LEAF_PILE.asItem().getDefaultStack(),
      SeasonalSpiceBlocks.ORANGE_LEAF_PILE.asItem().getDefaultStack(),
      SeasonalSpiceBlocks.RED_LEAF_PILE.asItem().getDefaultStack(),
      SeasonalSpiceBlocks.BROWN_LEAF_PILE.asItem().getDefaultStack()
    )))
    .build();

  public static final ItemGroup WINTER_SPICE = FabricItemGroupBuilder.create(SeasonalSpice.id("winter_spice"))
    .icon(() -> Items.SNOWBALL.getDefaultStack())
    .build();

  public static void init() { }
}
