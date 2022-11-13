package com.github.mim1q.seasonalspice.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class HayStackBlock extends Block {
  public static final BooleanProperty NORTH = BooleanProperty.of("north");
  public static final BooleanProperty EAST = BooleanProperty.of("east");
  public static final BooleanProperty SOUTH = BooleanProperty.of("south");
  public static final BooleanProperty WEST = BooleanProperty.of("west");
  public static final BooleanProperty TOP = BooleanProperty.of("top");

  public HayStackBlock(Settings settings) {
    super(settings.offsetType(Block.OffsetType.XYZ).nonOpaque().dynamicBounds());
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    super.appendProperties(builder);
    builder.add(NORTH, EAST, SOUTH, WEST, TOP);
  }

  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    World world = ctx.getWorld();
    BlockPos pos = ctx.getBlockPos();
    boolean north = !world.getBlockState(pos.north()).isSideSolidFullSquare(world, pos.north(), Direction.SOUTH);
    boolean east = !world.getBlockState(pos.east()).isSideSolidFullSquare(world, pos.east(), Direction.WEST);
    boolean south = !world.getBlockState(pos.south()).isSideSolidFullSquare(world, pos.south(), Direction.NORTH);
    boolean west = !world.getBlockState(pos.west()).isSideSolidFullSquare(world, pos.west(), Direction.EAST);
    boolean top = !world.getBlockState(pos.up()).isSideSolidFullSquare(world, pos.up(), Direction.DOWN);
    return getDefaultState().with(NORTH, north).with(EAST, east).with(SOUTH, south).with(WEST, west).with(TOP, top);
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
  @SuppressWarnings("deprecation")
  public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
    boolean fullSide = world.getBlockState(neighborPos).isSideSolidFullSquare(world, neighborPos, direction.getOpposite());
    BooleanProperty property = getPropertyForDirection(direction);
    if (property != null) {
      return state.with(property, !fullSide);
    }
    return state;
  }

  private BooleanProperty getPropertyForDirection(Direction direction) {
    return switch (direction) {
      case NORTH -> NORTH;
      case EAST -> EAST;
      case SOUTH -> SOUTH;
      case WEST -> WEST;
      case UP -> TOP;
      case DOWN -> null;
    };
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
