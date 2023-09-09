package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.util.List;

public class ModItemGroups {
  private static ItemStack stack(ItemConvertible item) {
    return item.asItem().getDefaultStack();
  }

  public static final ItemGroup CONVENIENT_DECOR = register(FabricItemGroup.builder()
    .displayName(Text.of("Convenient Decor"))
    .icon(() -> ModItems.WATERING_CAN.getStack(32))
    .entries((ctx, stacks) -> {
      stacks.addAll(List.of(
        stack(ModBlocks.PITCHFORK),
        stack(ModBlocks.SHOVEL)
      ));
      stacks.addAll(ModBlocks.PLASTIC_SHOVEL.getItemStackList());
      stacks.addAll(List.of(
        ModItems.WATERING_CAN.getDefaultStack(),
        ModItems.WATERING_CAN.getStack(32),
        stack(ModBlocks.UNBUNDLED_HAY_BLOCK),
        stack(ModBlocks.HAYSTACK_BLOCK),
        stack(ModBlocks.LEAF_PILE),
        stack(ModBlocks.YELLOW_LEAF_PILE),
        stack(ModBlocks.ORANGE_LEAF_PILE),
        stack(ModBlocks.RED_LEAF_PILE),
        stack(ModBlocks.BROWN_LEAF_PILE),
        stack(ModBlocks.SPRUCE_LEAF_PILE),
        stack(ModBlocks.BIRCH_LEAF_PILE),
        stack(ModBlocks.JUNGLE_LEAF_PILE),
        stack(ModBlocks.ACACIA_LEAF_PILE),
        stack(ModBlocks.DARK_OAK_LEAF_PILE),
        stack(ModBlocks.MANGROVE_LEAF_PILE),
        stack(ModBlocks.AZALEA_LEAF_PILE),
        stack(ModBlocks.FLOWERING_AZALEA_LEAF_PILE)
      ));
      stacks.addAll(ModBlocks.UMBRELLA.getItemStackList());
      stacks.addAll(List.of(
        stack(ModBlocks.BROKEN_UMBRELLA),
        stack(ModBlocks.ALLERTS_UMBRELLA),
        stack(ModBlocks.UMBRELLA_STAND),
        stack(ModBlocks.GOLD_WEATHER_VANE),
        stack(ModBlocks.COPPER_WEATHER_VANE),
        stack(ModBlocks.IRON_WEATHER_VANE),
        stack(ModBlocks.NETHERITE_WEATHER_VANE)
      ));
      stacks.addAll(ModItems.RAIN_HAT.getItemStackList());
      stacks.addAll(ModItems.RAINCOAT.getItemStackList());
      stacks.addAll(ModItems.RAIN_BOOTS.getItemStackList());
    })
  );

  public static void init() { }

  private static ItemGroup register (ItemGroup.Builder builder) {
    var itemGroup = builder.build();
    Registry.register(Registries.ITEM_GROUP, ConvenientDecor.id("convenient_decor"), itemGroup);
    return itemGroup;
  }
}
