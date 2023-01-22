package com.github.mim1q.convenientdecor.block;

import com.github.mim1q.convenientdecor.block.blockentity.UmbrellaStandBlockEntity;
import com.github.mim1q.convenientdecor.item.UmbrellaItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UmbrellaStandBlock extends Block implements BlockEntityProvider {
  public static final VoxelShape SHAPE = createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
  public static final IntProperty ROTATION = Properties.ROTATION;

  public UmbrellaStandBlock(Settings settings) {
    super(settings);
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(ROTATION);
  }

  @Override
  @SuppressWarnings("deprecation")
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    UmbrellaStandBlockEntity entity = (UmbrellaStandBlockEntity) world.getBlockEntity(pos);
    if (entity == null || hand == Hand.OFF_HAND) {
      return ActionResult.PASS;
    }
    ItemStack playerStack = player.getStackInHand(hand);
    if (entity.hasStack()) {
      if (playerStack.isEmpty()) {
        if (!world.isClient) {
          player.setStackInHand(hand, entity.getStack().copy());
          entity.setStack(ItemStack.EMPTY);
          world.playSound(null, pos, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return ActionResult.SUCCESS;
      }
      return ActionResult.PASS;
    }
    if (playerStack.getItem() instanceof UmbrellaItem) {
      if (!world.isClient) {
        entity.setStack(playerStack.copy());
        player.setStackInHand(hand, ItemStack.EMPTY);
        world.setBlockState(
          pos,
          state.with(ROTATION, MathHelper.floor((double)(player.getYaw() * 16.0F / 360.0F) + 0.5) & 15)
        );
        world.playSound(null, pos, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.BLOCKS, 1.0F, 1.0F);
      }
      return ActionResult.SUCCESS;
    }
    return ActionResult.PASS;
  }

  @Override
  @SuppressWarnings("deprecation")
  public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
    List<ItemStack> stacks = super.getDroppedStacks(state, builder);
    BlockEntity entity = builder.get(LootContextParameters.BLOCK_ENTITY);
    if (entity instanceof UmbrellaStandBlockEntity umbrella && umbrella.hasStack()) {
      stacks.add(umbrella.getStack());
    }
    return stacks;
  }

  @Override
  @SuppressWarnings("deprecation")
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new UmbrellaStandBlockEntity(pos, state);
  }
}
