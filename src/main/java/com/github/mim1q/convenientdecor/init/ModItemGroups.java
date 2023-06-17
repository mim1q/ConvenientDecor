package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;

import java.util.List;

public class ModItemGroups {
  public static final ItemGroup CONVENIENT_DECOR = FabricItemGroupBuilder.create(ConvenientDecor.id("convenient_decor"))
    .icon(() -> ModItems.WATERING_CAN.getStack(32))
    .appendItems(stacks -> {
      stacks.addAll(List.of(
        ModBlocks.PITCHFORK.asItem().getDefaultStack(),
        ModBlocks.SHOVEL.asItem().getDefaultStack()
      ));
      stacks.addAll(ModBlocks.PLASTIC_SHOVEL.getItemStackList());
      stacks.addAll(List.of(
        ModItems.WATERING_CAN.getDefaultStack(),
        ModItems.WATERING_CAN.getStack(32),
        ModBlocks.UNBUNDLED_HAY_BLOCK.asItem().getDefaultStack(),
        ModBlocks.HAYSTACK_BLOCK.asItem().getDefaultStack(),
        ModBlocks.LEAF_PILE.asItem().getDefaultStack(),
        ModBlocks.YELLOW_LEAF_PILE.asItem().getDefaultStack(),
        ModBlocks.ORANGE_LEAF_PILE.asItem().getDefaultStack(),
        ModBlocks.RED_LEAF_PILE.asItem().getDefaultStack(),
        ModBlocks.BROWN_LEAF_PILE.asItem().getDefaultStack()
      ));
      stacks.addAll(ModBlocks.UMBRELLA.getItemStackList());
      stacks.addAll(List.of(
        ModBlocks.BROKEN_UMBRELLA.asItem().getDefaultStack(),
        ModBlocks.ALLERTS_UMBRELLA.asItem().getDefaultStack(),
        ModBlocks.UMBRELLA_STAND.asItem().getDefaultStack(),
        ModBlocks.GOLD_WEATHER_VANE.asItem().getDefaultStack(),
        ModBlocks.COPPER_WEATHER_VANE.asItem().getDefaultStack(),
        ModBlocks.IRON_WEATHER_VANE.asItem().getDefaultStack(),
        ModBlocks.NETHERITE_WEATHER_VANE.asItem().getDefaultStack()
      ));
      stacks.addAll(ModItems.RAIN_HAT.getItemStackList());
      stacks.addAll(ModItems.RAINCOAT.getItemStackList());
      stacks.addAll(ModItems.RAIN_BOOTS.getItemStackList());
    })
    .build();
  public static void init() { }
}
