package com.github.mim1q.convenientdecor.client.render.clothes;

import net.minecraft.client.model.*;

public class CustomClothesModelData {
  public static TexturedModelData raincoat() {
    ModelData modelData = new ModelData();
    ModelPartData modelPartData = modelData.getRoot();
    modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.5F, -24.5F, -2.5F, 9.0F, 16.0F, 5.0F, new Dilation(0.0F)).uv(28, 0).cuboid(-4.5F, -27.5F, -0.5F, 9.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
    modelPartData.addChild("head", ModelPartBuilder.create().uv(28, 11).cuboid(-4.5F, -32.5F, -4.5F, 9.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
    ModelPartData leftArm = modelPartData.addChild("leftArm", ModelPartBuilder.create(), ModelTransform.pivot(6.5F, -0.5F, -0.25F));
    leftArm.addChild("leftArm_r1", ModelPartBuilder.create().uv(0, 21).mirrored().cuboid(-3.5F, -0.25F, -2.25F, 5.0F, 11.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));
    ModelPartData rightArm = modelPartData.addChild("rightArm", ModelPartBuilder.create(), ModelTransform.pivot(6.5F, -0.5F, -0.25F));
    rightArm.addChild("rightArm_r1", ModelPartBuilder.create().uv(0, 21).cuboid(-1.5F, -0.25F, -2.25F, 5.0F, 11.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-13.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));
    return TexturedModelData.of(modelData, 64, 64);
  }
}