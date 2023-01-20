package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.init.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;

public class UmbrellaItem extends BlockItem {
  public final DyeColor color;

  public UmbrellaItem(Settings settings, DyeColor color) {
    super(ModBlocks.UMBRELLA.get(color), settings);
    this.color = color;
  }

  @Override
  public ActionResult useOnBlock(ItemUsageContext context) {
    if (context.getPlayer() != null && !context.getPlayer().isSneaking()) {
      return ActionResult.PASS;
    }
    return super.useOnBlock(context);
  }
}
