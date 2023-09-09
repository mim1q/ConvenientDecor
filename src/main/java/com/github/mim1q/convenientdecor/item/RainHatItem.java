package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.item.material.ModArmorMaterials;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.DyeColor;

public class RainHatItem extends ArmorItem implements ColoredItem {
  private final DyeColor color;

  public RainHatItem(Settings settings, DyeColor color) {
    super(ModArmorMaterials.RAINCOAT, Type.HELMET, settings);
    this.color = color;
  }

  @Override
  public DyeColor getColor() {
    return color;
  }
}
