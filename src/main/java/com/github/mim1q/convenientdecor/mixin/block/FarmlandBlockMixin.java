package com.github.mim1q.convenientdecor.mixin.block;

import com.github.mim1q.convenientdecor.block.CustomProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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

@Mixin(value = FarmlandBlock.class, priority = 2275)
public abstract class FarmlandBlockMixin extends Block {
  private FarmlandBlockMixin(Settings settings) {
    super(settings);
  }

  @Inject(method = "<init>", at = @At("TAIL"))
  private void constructor(CallbackInfo ci) {
    this.setDefaultState(getDefaultState().with(CustomProperties.HYDRATED, false));
  }

  @Inject(method = "appendProperties", at = @At("HEAD"))
  private void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
    builder.add(CustomProperties.HYDRATED);
  }

  @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
  private void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
    if (state.get(CustomProperties.HYDRATED)) {
      if (world.getBlockState(pos.up()).isAir()) {
        Vec3d offset = new Vec3d(random.nextDouble() - 0.5D, 0.0D, random.nextDouble() - 0.5D);
        Vec3d particlePos = Vec3d.ofBottomCenter(pos.up()).add(0.0D, 0.1D, 0.0D);
        world.spawnParticles(ParticleTypes.SPLASH, particlePos.x, particlePos.y, particlePos.z, 10, offset.x, 0.0D, offset.z, 0.1D);
      }
      ci.cancel();
    }
  }
}
