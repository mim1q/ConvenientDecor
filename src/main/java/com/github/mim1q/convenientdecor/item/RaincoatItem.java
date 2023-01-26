package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.init.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.DyeColor;

public class RaincoatItem extends ArmorItem {
  public final DyeColor color;

  public RaincoatItem(DyeColor color) {
    super(ModItems.CUSTOM_CLOTHES_ARMOR_MATERIAL, EquipmentSlot.CHEST, new FabricItemSettings().maxCount(1));
    this.color = color;
  }
}
