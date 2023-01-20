package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.init.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class UmbrellaItem extends BlockItem {
  public final Identifier texture;

  public UmbrellaItem(Settings settings, DyeColor color, boolean broken) {
    super(ModBlocks.UMBRELLA.get(color), settings);
    texture = ConvenientDecor.id("textures/blockentity/umbrella/" + (broken ? "broken" : color.getName()) + ".png");
  }

  public UmbrellaItem(Settings settings, DyeColor color) {
    this(settings, color, false);
  }
}
