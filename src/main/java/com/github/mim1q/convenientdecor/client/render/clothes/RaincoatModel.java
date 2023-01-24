package com.github.mim1q.convenientdecor.client.render.clothes;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import net.minecraft.client.model.ModelPart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class RaincoatModel extends ClothesModel {
  public static final Identifier TEXTURE = ConvenientDecor.id("textures/clothes/raincoat/yellow.png");

  public RaincoatModel(ModelPart root) {
    super(root);
  }

  @Override
  public Identifier getTexture(ItemStack stack) {
    return TEXTURE;
  }
}
