package com.github.mim1q.convenientdecor.client.render.umbrella;

import net.minecraft.client.model.*;

public class SupportersUmbrellasModelData {
  public static TexturedModelData getAllertsFolded() {
    ModelData modelData = new ModelData();
    ModelPartData modelPartData = modelData.getRoot();
    ModelPartData canopy = modelPartData.addChild("canopy", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 10.0F, 0.0F));
    canopy.addChild("cube_r1", ModelPartBuilder.create().uv(-17, 37).cuboid(-0.5F, -0.25F, -22.0F, 1.0F, 0.0F, 21.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2654F, -2.618F, 0.0F));
    canopy.addChild("cube_r2", ModelPartBuilder.create().uv(-15, 37).cuboid(-0.5F, -0.25F, -22.0F, 1.0F, 0.0F, 21.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2654F, 2.618F, 0.0F));
    canopy.addChild("cube_r3", ModelPartBuilder.create().uv(-15, 37).cuboid(-0.5F, -0.25F, -22.0F, 1.0F, 0.0F, 21.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2654F, 1.5708F, 0.0F));
    canopy.addChild("cube_r4", ModelPartBuilder.create().uv(-21, 37).cuboid(-0.5F, -0.55F, -22.0F, 1.0F, 0.0F, 21.0F, new Dilation(0.0F))
      .uv(33, 106).cuboid(-9.0F, 0.0F, -21.0F, 18.0F, 0.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.309F, 1.0472F, 0.0F));
    canopy.addChild("cube_r5", ModelPartBuilder.create().uv(-12, 37).cuboid(-0.5F, -0.55F, -22.0F, 1.0F, 0.0F, 21.0F, new Dilation(0.0F))
      .uv(33, 85).cuboid(-9.0F, 0.0F, -21.0F, 18.0F, 0.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.309F, 2.0944F, 0.0F));
    canopy.addChild("cube_r6", ModelPartBuilder.create().uv(-11, 37).cuboid(-0.5F, -0.55F, -22.0F, 1.0F, 0.0F, 21.0F, new Dilation(0.0F))
      .uv(33, 64).cuboid(-9.0F, 0.0F, -21.0F, 18.0F, 0.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.309F, 3.1416F, 0.0F));
    canopy.addChild("cube_r7", ModelPartBuilder.create().uv(-15, 37).cuboid(-0.5F, -0.55F, -22.0F, 1.0F, 0.0F, 21.0F, new Dilation(0.0F))
      .uv(33, 43).cuboid(-9.0F, 0.0F, -21.0F, 18.0F, 0.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.309F, -2.0944F, 0.0F));
    canopy.addChild("cube_r8", ModelPartBuilder.create().uv(-11, 37).cuboid(-0.5F, -0.55F, -22.0F, 1.0F, 0.0F, 21.0F, new Dilation(0.0F))
      .uv(33, 22).cuboid(-9.0F, 0.0F, -21.0F, 18.0F, 0.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.309F, -1.0472F, 0.0F));
    canopy.addChild("cube_r9", ModelPartBuilder.create().uv(-17, 37).cuboid(-0.5F, -0.55F, -22.0F, 1.0F, 0.0F, 21.0F, new Dilation(0.0F))
      .uv(33, 1).cuboid(-9.0F, 0.0F, -21.0F, 18.0F, 0.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.309F, 0.0F, 0.0F));
    canopy.addChild("cube_r10", ModelPartBuilder.create().uv(-11, 37).cuboid(-0.5F, -0.25F, -22.0F, 1.0F, 0.0F, 21.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2654F, 0.5236F, 0.0F));
    canopy.addChild("cube_r11", ModelPartBuilder.create().uv(-21, 37).cuboid(-0.5F, -0.25F, -22.0F, 1.0F, 0.0F, 21.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2654F, -0.5236F, 0.0F));
    canopy.addChild("cube_r12", ModelPartBuilder.create().uv(-20, 37).cuboid(-0.5F, -0.25F, -22.0F, 1.0F, 0.0F, 21.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2654F, -1.5708F, 0.0F));

    ModelPartData decor = canopy.addChild("decor", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -13.0F, 0.0F));
    decor.addChild("cube_r13", ModelPartBuilder.create().uv(111, 83).cuboid(1.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, 4.0F, 0.0F, -1.309F, 0.0F, 1.309F));
    decor.addChild("cube_r14", ModelPartBuilder.create().uv(94, 83).cuboid(-9.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, 4.0F, 0.0F, -1.309F, 0.0F, -1.309F));
    modelPartData.addChild("ribs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

    ModelPartData ribs2 = modelPartData.addChild("ribs2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 12.75F, 0.0F));
    ribs2.addChild("cube_r15", ModelPartBuilder.create().uv(-14, 57).cuboid(-0.5F, 0.0F, -18.0F, 1.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2392F, -2.618F, 0.0F));
    ribs2.addChild("cube_r16", ModelPartBuilder.create().uv(-16, 57).cuboid(-0.5F, 0.0F, -18.0F, 1.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2697F, 0.0F, 0.0F));
    ribs2.addChild("cube_r17", ModelPartBuilder.create().uv(-16, 57).cuboid(-0.5F, 0.0F, -18.0F, 1.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2697F, 1.0472F, 0.0F));
    ribs2.addChild("cube_r18", ModelPartBuilder.create().uv(-16, 57).cuboid(-0.5F, 0.0F, -18.0F, 1.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2697F, 3.1416F, 0.0F));
    ribs2.addChild("cube_r19", ModelPartBuilder.create().uv(-16, 57).cuboid(-0.5F, 0.0F, -18.0F, 1.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2697F, 2.0944F, 0.0F));
    ribs2.addChild("cube_r20", ModelPartBuilder.create().uv(-16, 57).cuboid(-0.5F, 0.0F, -18.0F, 1.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2697F, -2.0944F, 0.0F));
    ribs2.addChild("cube_r21", ModelPartBuilder.create().uv(-16, 57).cuboid(-0.5F, 0.0F, -18.0F, 1.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2697F, -1.0472F, 0.0F));
    ribs2.addChild("cube_r22", ModelPartBuilder.create().uv(-16, 57).cuboid(-0.5F, 0.0F, -18.0F, 1.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2392F, -1.5708F, 0.0F));
    ribs2.addChild("cube_r23", ModelPartBuilder.create().uv(-18, 57).cuboid(-0.5F, 0.0F, -18.0F, 1.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2392F, -0.5236F, 0.0F));
    ribs2.addChild("cube_r24", ModelPartBuilder.create().uv(-8, 57).cuboid(-0.5F, 0.0F, -18.0F, 1.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2392F, 0.5236F, 0.0F));
    ribs2.addChild("cube_r25", ModelPartBuilder.create().uv(-10, 57).cuboid(-0.5F, 0.0F, -18.0F, 1.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2392F, 1.5708F, 0.0F));
    ribs2.addChild("cube_r26", ModelPartBuilder.create().uv(-12, 57).cuboid(-0.5F, 0.0F, -18.0F, 1.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 1.2392F, 2.618F, 0.0F));

    modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(118, 0).cuboid(-0.5F, -28.75F, 0.0F, 5.0F, 28.0F, 0.0F, new Dilation(0.0F))
      .uv(107, 14).cuboid(-1.0F, -26.25F, -1.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
    return TexturedModelData.of(modelData, 128, 128);
  }

  public static TexturedModelData getAllertsUnfolded() {
    ModelData modelData = new ModelData();
    ModelPartData modelPartData = modelData.getRoot();
    ModelPartData canopy = modelPartData.addChild("canopy", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 10.0F, 0.0F));

    ModelPartData hanging = canopy.addChild("hanging", ModelPartBuilder.create().uv(91, 29).cuboid(-9.0F, 0.45F, -0.05F, 18.0F, 8.0F, 0.0F, new Dilation(0.0F))
      .uv(91, 56).mirrored().cuboid(-9.0F, 0.45F, 30.05F, 18.0F, 8.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, -8.0F, -15.0F));
    hanging.addChild("cube_r1", ModelPartBuilder.create().uv(91, 47).mirrored().cuboid(-0.5F, -0.75F, -0.3F, 18.0F, 8.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(9.0F, 1.2F, 30.05F, 0.0F, 1.0472F, 0.0F));
    hanging.addChild("cube_r2", ModelPartBuilder.create().uv(91, 65).mirrored().cuboid(-17.6F, -0.75F, -0.3F, 18.0F, 8.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-9.0F, 1.2F, 30.05F, 0.0F, -1.0472F, 0.0F));
    hanging.addChild("cube_r3", ModelPartBuilder.create().uv(91, 38).cuboid(-0.5F, -0.75F, 0.3F, 18.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(9.0F, 1.2F, -0.05F, 0.0F, -1.0472F, 0.0F));
    hanging.addChild("cube_r4", ModelPartBuilder.create().uv(91, 74).cuboid(-17.6F, -0.75F, 0.3F, 18.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-9.0F, 1.2F, -0.05F, 0.0F, 1.0472F, 0.0F));

    ModelPartData decor = canopy.addChild("decor", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.0F, 15.0F));
    decor.addChild("cube_r5", ModelPartBuilder.create().uv(111, 83).cuboid(-1.0F, -7.0F, 0.0F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -4.05F, -14.95F, 0.0F, 0.0F, 0.1745F));
    decor.addChild("cube_r6", ModelPartBuilder.create().uv(94, 83).cuboid(-7.0F, -7.0F, 0.0F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -4.05F, -14.95F, 0.0F, 0.0F, -0.1745F));

    ModelPartData body = canopy.addChild("body", ModelPartBuilder.create().uv(105, 11).cuboid(-1.5F, -0.35F, -1.5F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -13.0F, 0.0F));
    body.addChild("cube_r7", ModelPartBuilder.create().uv(1, 18).cuboid(-9.0F, 0.0F, -16.0F, 18.0F, 0.0F, 15.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, -1.0472F, 0.0F));
    body.addChild("cube_r8", ModelPartBuilder.create().uv(1, 1).cuboid(-9.0F, 0.0F, -16.0F, 18.0F, 0.0F, 15.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
    body.addChild("cube_r9", ModelPartBuilder.create().uv(1, 67).cuboid(-9.0F, 0.0F, -16.0F, 18.0F, 0.0F, 15.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, 2.0944F, 0.0F));
    body.addChild("cube_r10", ModelPartBuilder.create().uv(1, 84).cuboid(-9.0F, 0.0F, -16.0F, 18.0F, 0.0F, 15.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, 1.0472F, 0.0F));
    body.addChild("cube_r11", ModelPartBuilder.create().uv(1, 51).cuboid(-9.0F, 0.0F, -16.0F, 18.0F, 0.0F, 15.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, 3.1416F, 0.0F));
    body.addChild("cube_r12", ModelPartBuilder.create().uv(1, 34).cuboid(-9.0F, 0.0F, -16.0F, 18.0F, 0.0F, 15.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, -2.0944F, 0.0F));

    ModelPartData lines = canopy.addChild("lines", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -13.0F, 0.0F));
    lines.addChild("cube_r13", ModelPartBuilder.create().uv(-7, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4F, 0.0F, 0.3491F, 1.2217F, 0.0F));
    lines.addChild("cube_r14", ModelPartBuilder.create().uv(-11, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4F, 0.0F, 0.3491F, 0.8727F, 0.0F));
    lines.addChild("cube_r15", ModelPartBuilder.create().uv(-16, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4F, 0.0F, 0.3491F, 2.2689F, 0.0F));
    lines.addChild("cube_r16", ModelPartBuilder.create().uv(-9, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4F, 0.0F, 0.3491F, 1.9199F, 0.0F));
    lines.addChild("cube_r17", ModelPartBuilder.create().uv(-7, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4F, 0.0F, 0.3491F, -2.9671F, 0.0F));
    lines.addChild("cube_r18", ModelPartBuilder.create().uv(-7, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4F, 0.0F, 0.3491F, 2.9671F, 0.0F));
    lines.addChild("cube_r19", ModelPartBuilder.create().uv(-11, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4F, 0.0F, 0.3491F, -1.9199F, 0.0F));
    lines.addChild("cube_r20", ModelPartBuilder.create().uv(-7, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4F, 0.0F, 0.3491F, -2.2689F, 0.0F));
    lines.addChild("cube_r21", ModelPartBuilder.create().uv(-11, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4F, 0.0F, 0.3491F, -0.8727F, 0.0F));
    lines.addChild("cube_r22", ModelPartBuilder.create().uv(-7, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4F, 0.0F, 0.3491F, -1.2217F, 0.0F));
    lines.addChild("cube_r23", ModelPartBuilder.create().uv(-7, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, 0.1745F, 0.0F));
    lines.addChild("cube_r24", ModelPartBuilder.create().uv(-13, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, -0.1745F, 0.0F));
    lines.addChild("cube_r25", ModelPartBuilder.create().uv(-12, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, -2.618F, 0.0F));
    lines.addChild("cube_r26", ModelPartBuilder.create().uv(-15, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, 2.618F, 0.0F));
    lines.addChild("cube_r27", ModelPartBuilder.create().uv(-9, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, 1.5708F, 0.0F));
    lines.addChild("cube_r28", ModelPartBuilder.create().uv(-7, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, 0.5236F, 0.0F));
    lines.addChild("cube_r29", ModelPartBuilder.create().uv(-17, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, -0.5236F, 0.0F));
    lines.addChild("cube_r30", ModelPartBuilder.create().uv(-15, 1).cuboid(-0.5F, -0.25F, -18.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, -1.5708F, 0.0F));

    ModelPartData ribs = modelPartData.addChild("ribs", ModelPartBuilder.create().uv(90, 1).cuboid(-4.5F, -12.0F, -4.5F, 9.0F, 0.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 12.75F, 0.0F));
    ribs.addChild("cube_r31", ModelPartBuilder.create().uv(-13, 19).cuboid(-0.5F, 0.0F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 0.1745F, -2.618F, 0.0F));
    ribs.addChild("cube_r32", ModelPartBuilder.create().uv(-15, 19).cuboid(-0.5F, 0.0F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 0.1745F, -1.5708F, 0.0F));
    ribs.addChild("cube_r33", ModelPartBuilder.create().uv(-17, 19).cuboid(-0.5F, 0.0F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 0.1745F, -0.5236F, 0.0F));
    ribs.addChild("cube_r34", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.1F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 0.1745F, 0.8727F, 0.0F));
    ribs.addChild("cube_r35", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.1F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 0.1745F, 1.2217F, 0.0F));
    ribs.addChild("cube_r36", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.1F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, -2.9671F, 1.2217F, -3.1416F));
    ribs.addChild("cube_r37", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.1F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, -2.9671F, 0.8727F, 3.1416F));
    ribs.addChild("cube_r38", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.1F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, -2.9671F, 0.1745F, -3.1416F));
    ribs.addChild("cube_r39", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.1F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, -2.9671F, -0.1745F, 3.1416F));
    ribs.addChild("cube_r40", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.1F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, -2.9671F, -0.8727F, -3.1416F));
    ribs.addChild("cube_r41", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.1F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, -2.9671F, -1.2217F, 3.1416F));
    ribs.addChild("cube_r42", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.1F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 0.1745F, -1.2217F, 0.0F));
    ribs.addChild("cube_r43", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.1F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 0.1745F, -0.8727F, 0.0F));
    ribs.addChild("cube_r44", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.1F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 0.1745F, -0.1745F, 0.0F));
    ribs.addChild("cube_r45", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.1F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 0.1745F, 0.1745F, 0.0F));
    ribs.addChild("cube_r46", ModelPartBuilder.create().uv(-7, 19).cuboid(-0.5F, 0.0F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 0.1745F, 0.5236F, 0.0F));
    ribs.addChild("cube_r47", ModelPartBuilder.create().uv(-9, 19).cuboid(-0.5F, 0.0F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 0.1745F, 1.5708F, 0.0F));
    ribs.addChild("cube_r48", ModelPartBuilder.create().uv(-11, 19).cuboid(-0.5F, 0.0F, -17.0F, 1.0F, 0.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 0.0F, 0.1745F, 2.618F, 0.0F));
    modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(118, 0).cuboid(-0.5F, -28.75F, 0.0F, 5.0F, 28.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
    return TexturedModelData.of(modelData, 128, 128);
  }
}
