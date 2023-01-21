package com.github.mim1q.convenientdecor.block.blockentity;

import com.github.mim1q.convenientdecor.block.UmbrellaBlock;
import com.github.mim1q.convenientdecor.init.ModBlockEntities;
import com.github.mim1q.convenientdecor.item.UmbrellaItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class UmbrellaBlockEntity extends BlockEntity {
  public final boolean folded;

  public UmbrellaBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlockEntities.UMBRELLA, pos, state);
    this.folded = state.get(UmbrellaBlock.FOLDED);
  }

  @Override
  public void setStackNbt(ItemStack stack) {
    super.setStackNbt(stack);
    UmbrellaItem.setFolded(stack, folded);
  }
}
