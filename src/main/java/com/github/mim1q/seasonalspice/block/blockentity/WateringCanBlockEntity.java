package com.github.mim1q.seasonalspice.block.blockentity;

import com.github.mim1q.seasonalspice.init.SeasonalSpiceBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class WateringCanBlockEntity extends BlockEntity {
  private int damage = 0;

  public WateringCanBlockEntity(BlockPos pos, BlockState state) {
    super(SeasonalSpiceBlockEntities.WATERING_CAN, pos, state);
  }

  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  @Override
  public void readNbt(NbtCompound nbt) {
    super.readNbt(nbt);
    damage = nbt.getInt("damage");
  }

  @Override
  protected void writeNbt(NbtCompound nbt) {
    super.writeNbt(nbt);
    nbt.putInt("damage", damage);
  }

  @Override
  public void setStackNbt(ItemStack stack) {
    super.setStackNbt(stack);
    this.setDamage(stack.getDamage());
  }
}
