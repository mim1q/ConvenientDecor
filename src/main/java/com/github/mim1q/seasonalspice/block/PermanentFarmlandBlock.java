package com.github.mim1q.seasonalspice.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class PermanentFarmlandBlock extends FarmlandBlock {
  public PermanentFarmlandBlock(Settings settings) {
    super(settings);
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    super.appendProperties(builder);
  }

  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    return this.getState();
  }

  public BlockState getState() {
    return this.getDefaultState().with(MOISTURE, 7);
  }

  @Override
  public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) { }
}
