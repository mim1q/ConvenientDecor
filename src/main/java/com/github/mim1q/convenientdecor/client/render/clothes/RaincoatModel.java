package com.github.mim1q.convenientdecor.client.render.clothes;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.item.RaincoatItem;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
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

  public RaincoatModel(ModelPart root) {
    super(root);
    hood = head.getChild("hood");
    hood2 = head.getChild("hood2");
  }

  public static TexturedModelData getTexturedModelData() {
    ModelData modelData = new ModelData();
    ModelPartData modelPartData = modelData.getRoot();
    ModelPartData rightArm = modelPartData.addChild("rightArm", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
    rightArm.addChild("rightArm_r1", ModelPartBuilder.create().uv(0, 21).cuboid(-1.5F, -0.25F, -2.25F, 5.0F, 11.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -2.5F, -0.25F, 0.0F, 0.0F, 0.1745F));
    ModelPartData leftArm = modelPartData.addChild("leftArm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 2.0F, 0.0F));
    leftArm.addChild("leftArm_r1", ModelPartBuilder.create().uv(0, 21).mirrored().cuboid(-3.5F, -0.25F, -2.25F, 5.0F, 11.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.5F, -2.5F, -0.25F, 0.0F, 0.0F, -0.1745F));
    modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.5F, -0.5F, -2.5F, 9.0F, 16.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
    ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
    head.addChild("hood", ModelPartBuilder.create().uv(28, 11).cuboid(-4.5F, -32.5F, -4.5F, 9.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
    head.addChild("hood2", ModelPartBuilder.create().uv(28, 0).cuboid(-4.5F, -3.5F, -0.5F, 9.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
    return TexturedModelData.of(modelData, 64, 64);
  }

  @Override
  public void applyTransform(BipedEntityModel<?> model, ItemStack stack) {
    boolean hooded = RaincoatItem.isHooded(stack);
    hood.visible = hooded;
    hood2.visible = !hooded;
    super.applyTransform(model, stack);
  }

  @Override
  public Identifier getTexture(ItemStack stack) {
    return TEXTURES.get(((RaincoatItem) stack.getItem()).color);
  }
}
