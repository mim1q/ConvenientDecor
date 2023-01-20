package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.block.UmbrellaBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;

public class UmbrellaItem extends BlockItem {
  public final DyeColor color;

  public UmbrellaItem(UmbrellaBlock block, DyeColor color) {
    super(block, new FabricItemSettings().maxCount(1));
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
