package com.github.mim1q.convenientdecor.block;

import com.github.mim1q.convenientdecor.block.blockentity.WateringCanBlockEntity;
import com.github.mim1q.convenientdecor.init.ModItems;
import com.github.mim1q.convenientdecor.item.WateringCanItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WateringCanBlock extends Block implements BlockEntityProvider {
  public static final VoxelShape SHAPE = createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
  public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
  public static final BooleanProperty FILLED = BooleanProperty.of("filled");

  public WateringCanBlock(Settings settings) {
    super(settings);
    this.setDefaultState(super.getDefaultState().with(FILLED, false));
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new WateringCanBlockEntity(pos, state);
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    super.appendProperties(builder);
    builder.add(FACING, FILLED);
  }

  @Override
  @SuppressWarnings("deprecation")
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  @Nullable
  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    boolean filled = WateringCanItem.getWaterLevel(ctx.getStack()) > 0 || EnchantmentHelper.getLevel(Enchantments.INFINITY, ctx.getStack()) > 0;
    return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(FILLED, filled);
  }

  @Override
  public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
    return getStackFromBlockEntity(world.getBlockEntity(pos));
  }

  private static ItemStack getStackFromBlockEntity(BlockEntity entity) {
    if (entity instanceof WateringCanBlockEntity wateringCan) {
      ItemStack stack = new ItemStack(ModItems.WATERING_CAN);
      WateringCanItem.setWaterLevel(stack, wateringCan.getWaterLevel());
      if (wateringCan.isInfiniteWater()) {
        stack.addEnchantment(Enchantments.INFINITY, 1);
      }
      return stack;
    }
    return null;
  }

  @Override
  public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
    super.afterBreak(world, player, pos, state, blockEntity, tool);

    if (world.isClient) return;

    ItemStack stack = getStackFromBlockEntity(blockEntity);
    if (stack != null) {
      var entity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
      world.spawnEntity(entity);
    }
  }
}
