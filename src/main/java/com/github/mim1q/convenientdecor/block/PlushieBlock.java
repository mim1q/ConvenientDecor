package com.github.mim1q.convenientdecor.block;

import com.github.mim1q.convenientdecor.block.blockentity.PlushieBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PlushieBlock extends BlockWithEntity {
  private final SoundEvent soundEvent;

  public PlushieBlock(Settings settings, SoundEvent soundEvent) {
    super(settings);
    this.soundEvent = soundEvent;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new PlushieBlockEntity(pos, state);
  }

  @Override
  public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    super.onPlaced(world, pos, state, placer, itemStack);
    if (placer == null) return;
    var blockEntity = world.getBlockEntity(pos);
    if (blockEntity instanceof PlushieBlockEntity plushie) {
      plushie.setRotation(placer.getYaw());
      plushie.squish(world, soundEvent, world.getTime());
    }
  }

  @Override
  public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
    return true;
  }

  @Override
  @SuppressWarnings("deprecation")
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    var blockEntity = world.getBlockEntity(pos);
    if (blockEntity instanceof PlushieBlockEntity plushie) {
      plushie.squish(world, soundEvent, world.getTime());
      return ActionResult.SUCCESS;
    }
    return super.onUse(state, world, pos, player, hand, hit);
  }
}
