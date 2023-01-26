package com.github.mim1q.convenientdecor.client.render.clothes;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import net.minecraft.client.model.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class RaincoatModel extends ClothesModel {
  public static final Identifier TEXTURE = ConvenientDecor.id("textures/clothes/raincoat/yellow.png");

  public RaincoatModel(ModelPart root) {
    super(root);
  }

  public static TexturedModelData getTexturedModelData() {
    ModelData modelData = new ModelData();
    ModelPartData modelPartData = modelData.getRoot();
    ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.5F, -0.5F, -2.5F, 9.0F, 16.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
    body.addChild("hood", ModelPartBuilder.create().uv(28, 0).cuboid(-4.5F, -3.5F, -0.5F, 9.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
    ModelPartData leftArm = body.addChild("leftArm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 2.0F, 0.0F));
    leftArm.addChild("leftArm_r1", ModelPartBuilder.create().uv(0, 21).mirrored().cuboid(-3.5F, -0.25F, -2.25F, 5.0F, 11.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.5F, -2.5F, -0.25F, 0.0F, 0.0F, -0.1745F));
    ModelPartData rightArm = body.addChild("rightArm", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
    rightArm.addChild("rightArm_r1", ModelPartBuilder.create().uv(0, 21).cuboid(-1.5F, -0.25F, -2.25F, 5.0F, 11.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -2.5F, -0.25F, 0.0F, 0.0F, 0.1745F));
    modelPartData.addChild("head", ModelPartBuilder.create().uv(28, 11).cuboid(-4.5F, -8.5F, -4.5F, 9.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
    return TexturedModelData.of(modelData, 64, 64);
  }

  @Override
  public Identifier getTexture(ItemStack stack) {
    return TEXTURE;
  }
}
