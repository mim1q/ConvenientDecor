package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.init.ModItems;
import com.github.mim1q.convenientdecor.network.c2s.SwitchHoodC2SPacket;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RaincoatItem extends ArmorItem {
  public static final String HOOD_ON_KEY = "tooltip.convenientedecor.raincoat.hood_on";
  public static final String HOOD_OFF_KEY = "tooltip.convenientedecor.raincoat.hood_off";

  public final DyeColor color;

  public RaincoatItem(DyeColor color) {
    super(ModItems.CUSTOM_CLOTHES_ARMOR_MATERIAL, EquipmentSlot.CHEST, new FabricItemSettings().maxCount(1));
    this.color = color;
  }

  @Override
  public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
    if (clickType == ClickType.RIGHT) {
      if (player.world.isClient) {
        player.world.playSound(player, player.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS, 1.0F, 1.0F);
        new SwitchHoodC2SPacket(slot).send();
      }
      return true;
    }
    return false;
  }

  @Override
  public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
    tooltip.add(Text.translatable(isHooded(stack) ? HOOD_OFF_KEY : HOOD_ON_KEY).formatted(Formatting.GRAY));
    super.appendTooltip(stack, world, tooltip, context);
  }

  public static void setHooded(ItemStack stack, boolean hooded) {
    stack.getOrCreateNbt().putBoolean("hooded", hooded);
  }

  public static boolean isHooded(ItemStack stack) {
    NbtCompound nbt = stack.getOrCreateNbt();
    if (nbt.contains("hooded")) {
      return nbt.getBoolean("hooded");
    }
    return false;
  }
}
