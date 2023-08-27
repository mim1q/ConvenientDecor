package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class ModItemTags {
  public static final TagKey<Item> UMBRELLAS = of("functional_umbrellas");
  public static final TagKey<Item> RAINCOATS = of("raincoats");
  public static final TagKey<Item> RAIN_HATS = of("rain_hats");

  public static TagKey<Item> of(String id) {
    return TagKey.of(Registry.ITEM_KEY, ConvenientDecor.id(id));
  }
}
