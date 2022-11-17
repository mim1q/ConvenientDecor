package com.github.mim1q.seasonalspice.client.colors;

import com.github.mim1q.seasonalspice.init.SeasonalSpiceBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

public class FallLeavesColors {
  public static final int YELLOW = 0xFFF52d;
  public static final int ORANGE = 0xED7B19;
  public static final int RED = 0xD12C1D;
  public static final int BROWN = 0x733420;

  public static int getColor(BlockState state, BlockRenderView world, BlockPos pos, int tintIndex) {
    if (state.isOf(SeasonalSpiceBlocks.YELLOW_LEAF_PILE)) {
      return YELLOW;
    }
    if (state.isOf(SeasonalSpiceBlocks.ORANGE_LEAF_PILE)) {
      return ORANGE;
    }
    if (state.isOf(SeasonalSpiceBlocks.RED_LEAF_PILE)) {
      return RED;
    }
    if (state.isOf(SeasonalSpiceBlocks.BROWN_LEAF_PILE)) {
      return BROWN;
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
