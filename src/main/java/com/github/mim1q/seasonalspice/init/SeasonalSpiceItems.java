package com.github.mim1q.seasonalspice.init;

import com.github.mim1q.seasonalspice.SeasonalSpice;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class SeasonalSpiceItems {
  public static void init() { }

  public static <T extends Item> T register(T item, String name) {
    Registry.register(Registry.ITEM, SeasonalSpice.id(name), item);
    return item;
  }
}
