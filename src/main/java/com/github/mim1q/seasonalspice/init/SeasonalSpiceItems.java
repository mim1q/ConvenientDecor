package com.github.mim1q.seasonalspice.init;

import com.github.mim1q.seasonalspice.SeasonalSpice;
import com.github.mim1q.seasonalspice.item.WateringCanItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class SeasonalSpiceItems {
  public static final WateringCanItem WATERING_CAN = register(
    new WateringCanItem(new FabricItemSettings().maxCount(1).equipmentSlot(stack -> EquipmentSlot.HEAD)),
    "watering_can"
  );

  public static void init() { }

  public static <T extends Item> T register(T item, String name) {
    Registry.register(Registry.ITEM, SeasonalSpice.id(name), item);
    return item;
  }
}
