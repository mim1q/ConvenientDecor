package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.item.material.ModArmorMaterials;
import com.github.mim1q.convenientdecor.network.c2s.SwitchHoodC2SPacket;
import com.github.mim1q.convenientdecor.util.LegacyUtil;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class RaincoatItem extends ArmorItem implements ColoredItem {
  public static final String HOOD_ON_KEY = "tooltip.convenientedecor.raincoat.hood_on";
  public static final String HOOD_OFF_KEY = "tooltip.convenientedecor.raincoat.hood_off";

  public final DyeColor color;

  public RaincoatItem(DyeColor color) {
    super(ModArmorMaterials.RAINCOAT, Type.CHESTPLATE, new Item.Settings().maxCount(1));
    this.color = color;
  }

  @Override
  public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
    if (clickType == ClickType.RIGHT) {
      if (player.getWorld().isClient) {
        player.getWorld().playSound(player, player.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);
        ClientPlayNetworking.send(new SwitchHoodC2SPacket(slot.getIndex()));
      }
      return true;
    }
    return false;
  }

  @Override
  public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
    if (!world.isClient
      && entity instanceof LivingEntity living
      && living.getEquippedStack(EquipmentSlot.CHEST).isOf(this)
      && world.isRaining()
      && ConvenientDecor.CONFIG.features.rainclothesIncreasedHp
    ) {
      living.addStatusEffect(new StatusEffectInstance(
        StatusEffects.RESISTANCE,
        22,
        0,
        false, false, true
      ));
    }
    super.inventoryTick(stack, world, entity, slot, selected);
  }

  @Override
  public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
    tooltip.add(Text.translatable(isHooded(stack) ? HOOD_OFF_KEY : HOOD_ON_KEY).formatted(Formatting.GRAY));
    super.appendTooltip(stack, context, tooltip, type);
  }

  public static void setHooded(ItemStack stack, boolean hooded) {
    var nbt = LegacyUtil.getOrEmptyNbt(stack);
    nbt.putBoolean("hooded", hooded);
    LegacyUtil.writeNbt(stack, nbt);
  }

  public static boolean isHooded(ItemStack stack) {
    NbtCompound nbt = LegacyUtil.getOrEmptyNbt(stack);
    if (nbt.contains("hooded")) {
      return nbt.getBoolean("hooded");
    }
    return false;
  }

  @Override
  public DyeColor getColor() {
    return color;
  }
}
