package com.github.mim1q.convenientdecor.block.blockentity;

import com.github.mim1q.convenientdecor.init.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class WeatherVaneBlockEntity extends BlockEntity {
  public WeatherVaneBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlockEntities.WEATHER_VANE, pos, state);
  }
}
