package com.github.mim1q.seasonalspice.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PitchforkBlock extends Block {
  public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
  public static final EnumProperty<Type> TYPE = EnumProperty.of("type", Type.class);
  public static final VoxelShape SHAPE = createCuboidShape(4, 0, 4, 12, 8, 12);

  public PitchforkBlock(Settings settings) {
    super(settings.nonOpaque().offsetType(
      (state) -> state.get(TYPE) == Type.TILTED ? OffsetType.NONE : OffsetType.XZ
    ));
    this.setDefaultState(this.getDefaultState().with(TYPE, Type.LYING));
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    super.appendProperties(builder);
    builder.add(FACING, TYPE);
  }

  @Nullable
  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    return getDefaultState().with(FACING, ctx.getPlayerFacing());
  }

  @Override
  @SuppressWarnings("deprecation")
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (player.isSneaking()) {
      world.setBlockState(pos, state.cycle(TYPE));
      return ActionResult.SUCCESS;
    }
    return ActionResult.PASS;
  }

  @Override
  @SuppressWarnings("deprecation")
  public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return VoxelShapes.empty();
  }

  @Override
  @SuppressWarnings("deprecation")
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  public enum Type implements StringIdentifiable {
    STRAIGHT,
    TILTED,
    LYING;

    @Override
    public String asString() {
      return name().toLowerCase();
    }
  }
}
