package com.github.mim1q.convenientdecor.item.material;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class CustomClothesArmorMaterial implements ArmorMaterial {
  private static final int[] DURABILITY = { 500, 500, 500, 500 };
  private static final int[] PROTECTION = { 2, 3, 4, 2 };

  @Override
  public int getDurability(EquipmentSlot slot) {
    return DURABILITY[slot.getEntitySlotId()];
  }

  @Override
  public int getProtectionAmount(EquipmentSlot slot) {
    return PROTECTION[slot.getEntitySlotId()];
  }

  @Override
  public int getEnchantability() {
    return 15;
  }

  @Override
  public SoundEvent getEquipSound() {
    return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return Ingredient.ofItems(Items.STRING);
  }

  @Override
  public String getName() {
    return "convenientdecor_custom";
  }

  @Override
  public float getToughness() {
    return 0.0F;
  }

  @Override
  public float getKnockbackResistance() {
    return 0.0F;
  }
}
