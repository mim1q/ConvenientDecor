package com.github.mim1q.convenientdecor.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class LegacyUtil {
  public static NbtCompound getOrEmptyNbt(ItemStack stack) {
    var nbt = stack.get(DataComponentTypes.CUSTOM_DATA);
    if (nbt == null) return new NbtCompound();

    return nbt.copyNbt();
  }

  public static NbtCompound getOrCreateSubNbt(ItemStack stack, String key) {
    var nbt = getOrEmptyNbt(stack);
    if (nbt.contains(key)) return nbt.getCompound(key);
    var newNbt = new NbtCompound();
    nbt.put(key, newNbt);
    return newNbt;
  }

  public static void writeNbt(ItemStack stack, NbtCompound nbt) {
    stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
  }
}
