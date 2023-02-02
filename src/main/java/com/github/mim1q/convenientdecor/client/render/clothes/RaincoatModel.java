package com.github.mim1q.convenientdecor.client.render.clothes;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.item.ColoredItem;
import com.github.mim1q.convenientdecor.item.RainBootsItem;
import com.github.mim1q.convenientdecor.item.RainHatItem;
import com.github.mim1q.convenientdecor.item.RaincoatItem;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class RaincoatModel extends ClothesModel {
  public static final Map<DyeColor, Identifier> TEXTURES = new HashMap<>();

  static {
    for (DyeColor color : DyeColor.values()) {
      TEXTURES.put(color, ConvenientDecor.id("textures/clothes/raincoat/" + color.getName() + ".png"));
    }
  }

  private final ModelPart hood;
  private final ModelPart hood2;
  private final ModelPart hat;

  public RaincoatModel(ModelPart root) {
    super(root);
    hood = head.getChild("hood");
    hood2 = head.getChild("hood2");
    hat = head.getChild("hat");
  }

  public static TexturedModelData getTexturedModelData() {
    ModelData modelData = new ModelData();
    ModelPartData modelPartData = modelData.getRoot();

    ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
    head.addChild("hood", ModelPartBuilder.create().uv(28, 11).cuboid(-4.5F, -32.5F, -4.5F, 9.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
    head.addChild("hood2", ModelPartBuilder.create().uv(28, 0).cuboid(-4.5F, -3.5F, -0.5F, 9.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
    head.addChild("hat", ModelPartBuilder.create().uv(9, 46).cuboid(-4.5F, 0.0F, -4.5F, 9.0F, 1.0F, 9.0F, new Dilation(0.0F))
      .uv(12, 36).cuboid(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, new Dilation(0.15F)), ModelTransform.of(0.0F, -6.75F, -0.025F, -0.07F, 0.0F, 0.0F));
    ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.5F, -0.5F, -2.5F, 9.0F, 12.0F, 5.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
    body.addChild("backFlap_r1", ModelPartBuilder.create().uv(40, 29).cuboid(-4.5F, -13.5F, 0.5F, 9.0F, 5.0F, 2.0F, new Dilation(0.09F)), ModelTransform.of(0.0F, 24.25F, 2.2F, 0.1745F, 0.0F, 0.0F));
    body.addChild("frontFlap_r1", ModelPartBuilder.create().uv(18, 29).cuboid(-4.5F, -1.0F, 0.0F, 9.0F, 5.0F, 2.0F, new Dilation(0.09F)), ModelTransform.of(0.0F, 11.5F, -2.5F, -0.1745F, 0.0F, 0.0F));
    ModelPartData leftArm = modelPartData.addChild("leftArm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 2.0F, 0.0F));
    leftArm.addChild("leftArm_r1", ModelPartBuilder.create().uv(0, 17).mirrored().cuboid(-3.5F, -0.25F, -2.25F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.5F, -2.5F, -0.25F, 0.0F, 0.0F, -0.1745F));
    ModelPartData rightArm = modelPartData.addChild("rightArm", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
    rightArm.addChild("rightArm_r1", ModelPartBuilder.create().uv(0, 17).cuboid(-1.5F, -0.25F, -2.25F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -2.5F, -0.25F, 0.0F, 0.0F, 0.1745F));
    modelPartData.addChild("rightLeg", ModelPartBuilder.create().uv(0, 31).cuboid(-2.0F, 4.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.1F))
      .uv(0, 55).cuboid(-2.0F, 9.0F, -3.2F, 4.0F, 3.0F, 1.0F, new Dilation(0.1F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));
    modelPartData.addChild("leftLeg", ModelPartBuilder.create().uv(0, 43).cuboid(-2.0F, 4.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.11F))
      .uv(0, 59).cuboid(-2.0F, 9.0F, -3.2F, 4.0F, 3.0F, 1.0F, new Dilation(0.11F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

    return TexturedModelData.of(modelData, 64, 64);
  }

  @Override
  public void applyTransform(LivingEntity entity, BipedEntityModel<?> model, ItemStack stack) {
    super.applyTransform(entity, model, stack);

    hat.visible = false;
    hood.visible = false;
    hood2.visible = false;
    body.visible = false;
    leftArm.visible = false;
    rightArm.visible = false;
    leftLeg.visible = false;
    rightLeg.visible = false;

    if (stack.getItem() instanceof RaincoatItem) {
      boolean hooded = RaincoatItem.isHooded(stack);
      body.visible = true;
      hood.visible = hooded;
      leftArm.visible = true;
      rightArm.visible = true;
      hood2.visible = !hooded;
      return;
    }
    if (stack.getItem() instanceof RainBootsItem) {
      leftLeg.visible = true;
      rightLeg.visible = true;
      return;
    }
    if (stack.getItem() instanceof RainHatItem) {
      hat.visible = true;
    }
  }

  @Override
  public Identifier getTexture(ItemStack stack) {
    return TEXTURES.get(((ColoredItem) stack.getItem()).getColor());
  }
}
