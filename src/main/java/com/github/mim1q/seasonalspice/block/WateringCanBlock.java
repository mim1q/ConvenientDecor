package com.github.mim1q.seasonalspice.block;

import com.github.mim1q.seasonalspice.block.blockentity.WateringCanBlockEntity;
import com.github.mim1q.seasonalspice.init.SeasonalSpiceItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WateringCanBlock extends Block implements BlockEntityProvider {
  public static final VoxelShape SHAPE = createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
  public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
  public static final BooleanProperty FULL = BooleanProperty.of("full");

  public WateringCanBlock(Settings settings) {
    super(settings);
    this.setDefaultState(super.getDefaultState().with(FULL, false));
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new WateringCanBlockEntity(pos, state);
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    super.appendProperties(builder);
    builder.add(FACING, FULL);
  }

  @Override
  @SuppressWarnings("deprecation")
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  @Nullable
  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    boolean full = ctx.getStack().getDamage() < ctx.getStack().getMaxDamage();
    return this.getDefaultState().with(FACING, ctx.getPlayerFacing()).with(FULL, full);
  }

  @Override
  public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
    BlockEntity entity = world.getBlockEntity(pos);
    if (entity instanceof WateringCanBlockEntity) {
      ItemStack stack = new ItemStack(SeasonalSpiceItems.WATERING_CAN);
      stack.setDamage(((WateringCanBlockEntity) entity).getDamage());
      return stack;
    }
    return null;
  }

  @Override
  @SuppressWarnings("deprecation")
  public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
    ItemStack stack = getPickStack(builder.getWorld(), new BlockPos(builder.get(LootContextParameters.ORIGIN)), state);
    return stack == null ? List.of() : List.of(stack);
  }
}
