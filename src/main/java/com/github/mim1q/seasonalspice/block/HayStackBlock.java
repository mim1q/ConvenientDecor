package com.github.mim1q.seasonalspice.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class HayStackBlock extends Block {
  public HayStackBlock(Settings settings) {
    super(settings.offsetType(Block.OffsetType.XYZ).nonOpaque().dynamicBounds());
  }

  @Override
  public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
    return true;
  }

  @Override
  @SuppressWarnings("deprecation")
  public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
    super.onEntityCollision(state, world, pos, entity);
    entity.slowMovement(state, new Vec3d(0.85D, 1.0D, 0.85D));
  }

  @Override
  public float getMaxHorizontalModelOffset() {
    return 0.125f;
  }

  @Override
  public float getVerticalModelOffsetMultiplier() {
    return 0.125f;
  }

  @Override
  @SuppressWarnings("deprecation")
  public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return VoxelShapes.empty();
  }

  @Override
  @SuppressWarnings("deprecation")
  public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
    return VoxelShapes.empty();
  }

  @Override
  @SuppressWarnings("deprecation")
  public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return VoxelShapes.empty();
  }
}
