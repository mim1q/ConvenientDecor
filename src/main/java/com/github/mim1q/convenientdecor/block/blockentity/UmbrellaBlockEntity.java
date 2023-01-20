package com.github.mim1q.convenientdecor.block.blockentity;

import com.github.mim1q.convenientdecor.block.UmbrellaBlock;
import com.github.mim1q.convenientdecor.init.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class UmbrellaBlockEntity extends BlockEntity {
  public UmbrellaBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlockEntities.UMBRELLA, pos, state);
  }

  public Identifier getTexture() {
    return ((UmbrellaBlock) this.getCachedState().getBlock()).texture;
  }
}
