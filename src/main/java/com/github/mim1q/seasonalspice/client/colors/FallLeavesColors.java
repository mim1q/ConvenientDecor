package com.github.mim1q.seasonalspice.client.colors;

import com.github.mim1q.seasonalspice.init.SeasonalSpiceBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

public class FallLeavesColors {
  public static final int YELLOW = 0x00AAFF;
  public static final int ORANGE = 0x00AAFF;
  public static final int RED = 0x00AAFF;
  public static final int BROWN = 0x00AAFF;

  public static int getColor(BlockState state, BlockRenderView world, BlockPos pos, int tintIndex) {
    if (state.isOf(SeasonalSpiceBlocks.YELLOW_LEAF_PILE)) {
      return 0xfff52d;
    }
    if (state.isOf(SeasonalSpiceBlocks.ORANGE_LEAF_PILE)) {
      return 0xed7b19;
    }
    if (state.isOf(SeasonalSpiceBlocks.RED_LEAF_PILE)) {
      return 0xd12c1d;
    }
    if (state.isOf(SeasonalSpiceBlocks.BROWN_LEAF_PILE)) {
      return 0x733420;
    }
    return world.getColor(pos, BiomeColors.FOLIAGE_COLOR);
  }

  public static int getColor(ItemStack stack, int tintIndex) {
    if (stack.isOf(SeasonalSpiceBlocks.YELLOW_LEAF_PILE.asItem())) {
      return YELLOW;
    }
    if (stack.isOf(SeasonalSpiceBlocks.ORANGE_LEAF_PILE.asItem())) {
      return ORANGE;
    }
    if (stack.isOf(SeasonalSpiceBlocks.RED_LEAF_PILE.asItem())) {
      return RED;
    }
    if (stack.isOf(SeasonalSpiceBlocks.BROWN_LEAF_PILE.asItem())) {
      return BROWN;
    }
    return FoliageColors.getDefaultColor();
  }
}
