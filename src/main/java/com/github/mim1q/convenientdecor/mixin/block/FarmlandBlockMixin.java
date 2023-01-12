package com.github.mim1q.convenientdecor.mixin.block;

import com.github.mim1q.convenientdecor.block.CustomProperties;
import com.github.mim1q.convenientdecor.init.ModItems;
import com.github.mim1q.convenientdecor.item.WateringCanItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = FarmlandBlock.class, priority = 2275)
public abstract class FarmlandBlockMixin extends Block {
  private FarmlandBlockMixin(Settings settings) {
    super(settings);
  }

  private boolean isUnhydratable() {
    return !this.getDefaultState().getProperties().contains(CustomProperties.HYDRATED);
  }

  @Inject(method = "<init>", at = @At("TAIL"))
  private void constructor(CallbackInfo ci) {
    if (isUnhydratable()) return;
    this.setDefaultState(getDefaultState().with(CustomProperties.HYDRATED, false));
  }

  @Inject(method = "appendProperties", at = @At("HEAD"))
  private void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
    builder.add(CustomProperties.HYDRATED);
  }

  @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
  private void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
    if (isUnhydratable()) return;
    if (state.get(CustomProperties.HYDRATED)) {
      ci.cancel();
    }
  }

  @Inject(method = "getPlacementState", at = @At("RETURN"), cancellable = true)
  private void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
    if (isUnhydratable()) return;
    BlockState state = cir.getReturnValue();
    boolean hydrated = false;
    PlayerEntity player = ctx.getPlayer();
    if (player != null) {
      ItemStack offhandStack = player.getStackInHand(Hand.OFF_HAND);
      if (offhandStack != null && offhandStack.isOf(ModItems.WATERING_CAN)) {
        int waterLevel = WateringCanItem.getWaterLevel(offhandStack);
        if (waterLevel > 0) {
          WateringCanItem.setWaterLevel(offhandStack, waterLevel - 1);
          hydrated = true;
        }
      }
    }
    cir.setReturnValue(state.with(CustomProperties.HYDRATED, hydrated).with(FarmlandBlock.MOISTURE, hydrated ? 7 : 0));
  }
}
