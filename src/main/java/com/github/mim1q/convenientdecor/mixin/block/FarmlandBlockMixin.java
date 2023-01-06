package com.github.mim1q.convenientdecor.mixin.block;

import com.github.mim1q.convenientdecor.block.CustomProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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

  @Inject(method = "appendProperties", at = @At("HEAD"))
  private void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
    builder.add(CustomProperties.WATERING_CAN_USED);
  }

  @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
  private void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
    if (state.get(CustomProperties.WATERING_CAN_USED)) {
      if (world.getBlockState(pos.up()).isAir()) {
        Vec3d offset = new Vec3d(random.nextDouble() - 0.5D, 0.0D, random.nextDouble() - 0.5D);
        Vec3d particlePos = Vec3d.ofBottomCenter(pos.up()).add(0.0D, 0.1D, 0.0D);
        world.spawnParticles(ParticleTypes.SPLASH, particlePos.x, particlePos.y, particlePos.z, 10, offset.x, 0.0D, offset.z, 0.1D);
      }
      ci.cancel();
    }
  }

  @Inject(method = "getPlacementState", at = @At("RETURN"), cancellable = true)
  private void getPlacementState(CallbackInfoReturnable<BlockState> cir) {
    BlockState state = cir.getReturnValue();
    cir.setReturnValue(state.isOf(Blocks.FARMLAND) ? state.with(CustomProperties.WATERING_CAN_USED, false) : state);
  }
}
