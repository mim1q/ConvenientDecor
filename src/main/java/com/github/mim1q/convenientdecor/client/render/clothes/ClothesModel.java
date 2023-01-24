package com.github.mim1q.convenientdecor.client.render.clothes;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public abstract class ClothesModel extends Model {
  private final ModelPart root;
  private final ModelPart body;
  private final ModelPart head;
  private final ModelPart rightArm;
  private final ModelPart leftArm;
  private final ModelPart rightLeg;
  private final ModelPart leftLeg;

  public ClothesModel(ModelPart root) {
    super(RenderLayer::getEntityCutout);
    this.root = root;
    this.body = root.hasChild("body") ? root.getChild("body") : null;
    this.head = root.hasChild("head") ? root.getChild("head") : null;
    this.rightArm = root.hasChild("rightArm") ? root.getChild("rightArm") : null;
    this.leftArm = root.hasChild("leftArm") ? root.getChild("leftArm") : null;
    this.rightLeg = root.hasChild("rightLeg") ? root.getChild("rightLeg") : null;
    this.leftLeg = root.hasChild("leftLeg") ? root.getChild("leftLeg") : null;
  }

  @Override
  public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
    root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
  }

  public abstract Identifier getTexture(ItemStack stack);

  public void applyTransform(PlayerEntityModel<AbstractClientPlayerEntity> model, ItemStack stack) {
//    if (body != null) body.copyTransform(model.body);
//    if (head != null) head.copyTransform(model.head);
//    if (rightArm != null) rightArm.copyTransform(model.rightArm);
//    if (leftArm != null) leftArm.copyTransform(model.leftArm);
//    if (rightLeg != null) rightLeg.copyTransform(model.rightLeg);
//    if (leftLeg != null) leftLeg.copyTransform(model.leftLeg);
  }
}
