package com.github.mim1q.seasonalspice.init;

import com.github.mim1q.seasonalspice.SeasonalSpice;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;

public class SeasonalSpiceItemGroups {
  public static final ItemGroup SPRING_SPICE = FabricItemGroupBuilder.create(SeasonalSpice.id("spring_spice"))
    .icon(() -> Blocks.OAK_SAPLING.asItem().getDefaultStack())
    .appendItems(stacks -> {
      stacks.add(SeasonalSpiceBlocks.SHOVEL.asItem().getDefaultStack());
    })
    .build();

  public static final ItemGroup SUMMER_SPICE = FabricItemGroupBuilder.create(SeasonalSpice.id("summer_spice"))
    .icon(() -> Blocks.SUNFLOWER.asItem().getDefaultStack())
    .appendItems(stacks -> {
      stacks.add(SeasonalSpiceBlocks.UNBUNDLED_HAY_BLOCK.asItem().getDefaultStack());
      stacks.add(SeasonalSpiceBlocks.HAYSTACK_BLOCK.asItem().getDefaultStack());
      stacks.add(SeasonalSpiceBlocks.RED_PLASTIC_SHOVEL.asItem().getDefaultStack());
      stacks.add(SeasonalSpiceBlocks.YELLOW_PLASTIC_SHOVEL.asItem().getDefaultStack());
      stacks.add(SeasonalSpiceBlocks.BLUE_PLASTIC_SHOVEL.asItem().getDefaultStack());
      stacks.add(SeasonalSpiceBlocks.PITCHFORK.asItem().getDefaultStack());
    })
    .build();

  public static final ItemGroup AUTUMN_SPICE = FabricItemGroupBuilder.create(SeasonalSpice.id("autumn_spice"))
    .icon(() -> Blocks.PUMPKIN.asItem().getDefaultStack())
    .build();

  public static final ItemGroup WINTER_SPICE = FabricItemGroupBuilder.create(SeasonalSpice.id("winter_spice"))
    .icon(() -> Items.SNOWBALL.getDefaultStack())
    .build();

  public static void init() { }
}
