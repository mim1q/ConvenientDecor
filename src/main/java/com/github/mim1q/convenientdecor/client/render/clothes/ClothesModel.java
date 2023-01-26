package com.github.mim1q.convenientdecor.client.render.clothes;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public abstract class ClothesModel extends Model {
  protected final ModelPart root;
  protected final ModelPart body;
  protected final ModelPart head;
  protected final ModelPart rightArm;
  protected final ModelPart leftArm;
  protected final ModelPart rightLeg;
  protected final ModelPart leftLeg;

  public ClothesModel(ModelPart root) {
    super(RenderLayer::getArmorCutoutNoCull);
    this.root = root;
    this.body = root.hasChild("body") ? root.getChild("body") : null;
    this.head = root.hasChild("head") ? root.getChild("head") : null;
    this.rightArm = body != null && body.hasChild("rightArm") ? body.getChild("rightArm") : null;
    this.leftArm = body != null && body.hasChild("leftArm") ? body.getChild("leftArm") : null;
    this.rightLeg = root.hasChild("rightLeg") ? root.getChild("rightLeg") : null;
    this.leftLeg = root.hasChild("leftLeg") ? root.getChild("leftLeg") : null;
  }

  @Override
  public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
    root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
  }

  public abstract Identifier getTexture(ItemStack stack);

  public void applyTransform(BipedEntityModel<?> model, ItemStack stack) {
    if (head != null) {
      head.pitch = model.head.pitch;
      head.roll = model.head.roll;
      head.yaw = model.head.yaw;
    }
    if (body != null) {
      body.pitch = model.body.pitch;
      body.roll = model.body.roll;
      body.yaw = model.body.yaw;
    }
    if (rightArm != null) {
      rightArm.pitch = model.rightArm.pitch;
      rightArm.roll = model.rightArm.roll;
      rightArm.yaw = model.rightArm.yaw;
    }
    if (leftArm != null) {
      leftArm.pitch = model.leftArm.pitch;
      leftArm.roll = model.leftArm.roll;
      leftArm.yaw = model.leftArm.yaw;
    }
    if (rightLeg != null) {
      rightLeg.pitch = model.rightLeg.pitch;
      rightLeg.roll = model.rightLeg.pitch;
      rightLeg.yaw = model.rightLeg.yaw;
    }
    if (leftLeg != null) {
      leftLeg.pitch = model.leftLeg.pitch;
      leftLeg.roll = model.leftLeg.roll;
      leftLeg.yaw = model.leftLeg.yaw;
    }
  }
}
