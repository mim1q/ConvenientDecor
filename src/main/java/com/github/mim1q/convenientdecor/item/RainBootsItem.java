package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.item.material.ModArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

public class RainBootsItem extends ArmorItem implements ColoredItem {
  private final DyeColor color;

  public RainBootsItem(Settings settings, DyeColor color) {
    super(ModArmorMaterials.RAINCOAT, EquipmentSlot.FEET, settings);
    this.color = color;
  }

  @Override
  public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
    if (!world.isClient
      && slot == EquipmentSlot.FEET.getEntitySlotId()
      && entity instanceof LivingEntity living
      && world.isRaining()
    ) {
      living.addStatusEffect(new StatusEffectInstance(
        StatusEffects.SPEED,
        22,
        world.isThundering() ? 1 : 0,
        false, false, true
      ));
    }
    super.inventoryTick(stack, world, entity, slot, selected);
  }

  @Override
  public DyeColor getColor() {
    return color;
  }
}
