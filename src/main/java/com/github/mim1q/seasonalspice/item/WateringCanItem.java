package com.github.mim1q.seasonalspice.item;

import com.github.mim1q.seasonalspice.init.SeasonalSpiceBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class WateringCanItem extends Item {
  public WateringCanItem(Settings settings) {
    super(settings);
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    BlockHitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.WATER);
    ItemStack stack = user.getStackInHand(hand);
    TypedActionResult<ItemStack> result = this.tryUseOnBlock(world, user, stack, hitResult);
    if (result != null) {
      return result;
    }
    if (hitResult.getType() == HitResult.Type.MISS) {
      return TypedActionResult.pass(stack);
    }
    ItemPlacementContext ctx = new ItemPlacementContext(user, hand, stack, hitResult);
    return tryPlace(ctx);
  }

  protected TypedActionResult<ItemStack> tryUseOnBlock(World world, PlayerEntity user, ItemStack stack, BlockHitResult hitResult) {
    if (user.isSneaking()) {
      return null;
    }
    BlockState state = world.getBlockState(hitResult.getBlockPos());
    if (state.getFluidState().isOf(Fluids.WATER)) {
      stack.setDamage(0);
      return TypedActionResult.success(stack);
    }
    if (state.isOf(Blocks.FARMLAND)) {
      if (stack.getDamage() == this.getMaxDamage()) {
        return TypedActionResult.fail(stack);
      }
      stack.setDamage(stack.getDamage() + 1);
      world.setBlockState(hitResult.getBlockPos(), Blocks.WET_SPONGE.getDefaultState());
      return TypedActionResult.success(stack);
    }
    return null;
  }

  protected TypedActionResult<ItemStack> tryPlace(ItemPlacementContext ctx) {
    World world = ctx.getWorld();
    BlockPos pos = ctx.getBlockPos();
    BlockState state = world.getBlockState(pos);
    ItemStack stack = ctx.getStack();
    PlayerEntity user = ctx.getPlayer();
    if (!state.canReplace(ctx)) {
      return TypedActionResult.fail(stack);
    }
    System.out.println("tryPlace");

    BlockState newState = SeasonalSpiceBlocks.WATERING_CAN.getDefaultState();
    world.setBlockState(pos, newState);
    world.playSound(user, pos, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, (BlockSoundGroup.METAL.getVolume() + 1.0F) / 2.0F, BlockSoundGroup.METAL.getPitch() * 0.8F);
    world.emitGameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Emitter.of(user, newState));
    stack.decrement(1);
    return TypedActionResult.consume(stack);
  }

  @Override
  public boolean isItemBarVisible(ItemStack stack) {
    return true;
  }
}
