package com.github.mim1q.convenientdecor.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.util.DyeColor;

public class RaincoatItem extends ArmorItem {
  public final DyeColor color;

  public RaincoatItem(DyeColor color) {
    super(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new FabricItemSettings().maxCount(1));
    this.color = color;
  }
}
