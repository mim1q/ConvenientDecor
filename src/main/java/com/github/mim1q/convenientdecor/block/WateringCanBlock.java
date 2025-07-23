package com.github.mim1q.convenientdecor.block;

import com.github.mim1q.convenientdecor.block.blockentity.WateringCanBlockEntity;
import com.github.mim1q.convenientdecor.init.ModItems;
import com.github.mim1q.convenientdecor.item.WateringCanItem;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class WateringCanBlock extends BlockWithEntity {
  public static final MapCodec<WateringCanBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
    Settings.CODEC.fieldOf("settings").forGetter(it -> it.getSettings())
  ).apply(instance, WateringCanBlock::new));

  public static final VoxelShape SHAPE = createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
  public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
  public static final BooleanProperty FILLED = BooleanProperty.of("filled");

  public WateringCanBlock(Settings settings) {
    super(settings);
    this.setDefaultState(super.getDefaultState().with(FILLED, false));
  }

  @Override
  protected MapCodec<? extends BlockWithEntity> getCodec() {
    return CODEC;
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
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  @Nullable
  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    var infinity = ctx.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.INFINITY).orElse(null);
    boolean filled = WateringCanItem.getWaterLevel(ctx.getStack()) > 0 || EnchantmentHelper.getLevel(infinity, ctx.getStack()) > 0;
    return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(FILLED, filled);
  }

  @Override
  public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
    return getStackFromBlockEntity(world.getBlockEntity(pos));
  }


  private static ItemStack getStackFromBlockEntity(BlockEntity entity) {
    if (entity instanceof WateringCanBlockEntity wateringCan) {
      ItemStack stack = new ItemStack(ModItems.WATERING_CAN);
      WateringCanItem.setWaterLevel(entity.getWorld(), stack, wateringCan.getWaterLevel());
      if (wateringCan.isInfiniteWater()) {
        var inifnity = entity.getWorld().getRegistryManager().get(
          RegistryKeys.ENCHANTMENT
        ).getEntry(Enchantments.INFINITY).orElse(null);
        stack.addEnchantment(inifnity, 1);
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
