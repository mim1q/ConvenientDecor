package com.github.mim1q.convenientdecor.block.blockentity;

import com.github.mim1q.convenientdecor.init.ModBlockEntities;
import com.github.mim1q.convenientdecor.item.WateringCanItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class WateringCanBlockEntity extends BlockEntity {
  private int waterLevel = 0;
  boolean infiniteWater = false;

  public WateringCanBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlockEntities.WATERING_CAN, pos, state);
  }

  public int getWaterLevel() {
    return waterLevel;
  }

  public void setWaterLevel(int waterLevel) {
    this.waterLevel = waterLevel;
  }

  public boolean isInfiniteWater() {
    return infiniteWater;
  }

  public void setInfiniteWater(boolean infiniteWater) {
    this.infiniteWater = infiniteWater;
  }

  @Override
  public void readNbt(NbtCompound nbt) {
    super.readNbt(nbt);
    waterLevel = nbt.getInt("WaterLevel");
    infiniteWater = nbt.getBoolean("InfiniteWater");
  }

  @Override
  protected void writeNbt(NbtCompound nbt) {
    super.writeNbt(nbt);
    nbt.putInt("WaterLevel", waterLevel);
    nbt.putBoolean("InfiniteWater", infiniteWater);
  }

  @Override
  public void setStackNbt(ItemStack stack) {
    super.setStackNbt(stack);
    this.setWaterLevel(WateringCanItem.getWaterLevel(stack));
    this.setInfiniteWater(EnchantmentHelper.get(stack).containsKey(Enchantments.INFINITY));
  }
}
