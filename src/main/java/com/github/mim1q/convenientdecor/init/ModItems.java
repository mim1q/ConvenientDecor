package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.block.UmbrellaBlock;
import com.github.mim1q.convenientdecor.init.group.ColoredGroup;
import com.github.mim1q.convenientdecor.init.group.ColoredGroup.ColoredItemGroup;
import com.github.mim1q.convenientdecor.item.RaincoatItem;
import com.github.mim1q.convenientdecor.item.UmbrellaItem;
import com.github.mim1q.convenientdecor.item.WateringCanItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;

public class ModItems {
  public static final WateringCanItem WATERING_CAN = register(
    new WateringCanItem(new FabricItemSettings().maxCount(1).equipmentSlot(stack -> EquipmentSlot.HEAD)),
    "watering_can"
  );

  public static final ColoredItemGroup UMBRELLA = ColoredGroup.ofItems()
    .add16Colors(color -> new UmbrellaItem((UmbrellaBlock) ModBlocks.UMBRELLA.get(color), color))
    .register("umbrella");

  public static final UmbrellaItem BROKEN_UMBRELLA = register(new UmbrellaItem(ModBlocks.BROKEN_UMBRELLA, DyeColor.BLACK), "broken_umbrella");

  public static final ColoredItemGroup RAINCOAT = ColoredGroup.ofItems()
    .add16Colors(color -> new RaincoatItem(color))
    .register("raincoat");

  public static void init() { }

  public static <T extends Item> T register(T item, String name) {
    Registry.register(Registry.ITEM, ConvenientDecor.id(name), item);
    return item;
  }
}
