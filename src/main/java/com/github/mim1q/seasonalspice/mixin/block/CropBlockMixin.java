package com.github.mim1q.seasonalspice.mixin.block;

import com.github.mim1q.seasonalspice.init.SeasonalSpiceBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CropBlock.class, priority = 2275)
public abstract class CropBlockMixin extends PlantBlock {
  public CropBlockMixin(Settings settings) {
    super(settings);
  }

  @Inject(
    method = "canPlantOnTop(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z",
    at = @At("RETURN"),
    cancellable = true
  )
  private void canPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
    if (floor.isOf(SeasonalSpiceBlocks.PERMANENT_FARMLAND)) {
      cir.setReturnValue(true);
    }
  }
}
