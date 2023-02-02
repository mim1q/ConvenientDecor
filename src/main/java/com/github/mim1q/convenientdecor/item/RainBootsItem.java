package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.init.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class RainBootsItem extends ArmorItem {
  public RainBootsItem(Settings settings) {
    super(ModItems.CUSTOM_CLOTHES_ARMOR_MATERIAL, EquipmentSlot.FEET, settings);
  }
}
