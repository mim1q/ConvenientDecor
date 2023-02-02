package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.init.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.DyeColor;

public class RainHatItem extends ArmorItem implements ColoredItem {
  private final DyeColor color;

  public RainHatItem(Settings settings, DyeColor color) {
    super(ModItems.CUSTOM_CLOTHES_ARMOR_MATERIAL, EquipmentSlot.HEAD, settings);
    this.color = color;
  }

  @Override
  public DyeColor getColor() {
    return color;
  }
}
