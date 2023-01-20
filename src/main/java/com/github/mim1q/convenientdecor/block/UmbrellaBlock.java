package com.github.mim1q.convenientdecor.block;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.block.blockentity.UmbrellaBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class UmbrellaBlock extends Block implements BlockEntityProvider {
  public final Identifier texture;

  public UmbrellaBlock(Settings settings, DyeColor color) {
    super(settings.nonOpaque().noCollision());
    texture = ConvenientDecor.id("textures/blockentity/umbrella/" + color.getName() + ".png");
  }

  @Override
  @SuppressWarnings("deprecation")
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.INVISIBLE;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new UmbrellaBlockEntity(pos, state);
  }
}
