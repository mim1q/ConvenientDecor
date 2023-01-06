package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.item.WateringCanItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ModItems {
  public static final WateringCanItem WATERING_CAN = register(
    new WateringCanItem(new FabricItemSettings().maxCount(1).equipmentSlot(stack -> EquipmentSlot.HEAD)),
    "watering_can"
  );

  public static void init() { }

  public static <T extends Item> T register(T item, String name) {
    Registry.register(Registry.ITEM, ConvenientDecor.id(name), item);
    return item;
  }
}
