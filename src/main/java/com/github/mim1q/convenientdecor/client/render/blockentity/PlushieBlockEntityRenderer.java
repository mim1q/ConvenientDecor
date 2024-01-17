package com.github.mim1q.convenientdecor.client.render.blockentity;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.block.PlushieBlock;
import com.github.mim1q.convenientdecor.block.blockentity.PlushieBlockEntity;
import com.github.mim1q.convenientdecor.init.ModBlocks;
import com.github.mim1q.convenientdecor.init.ModEntityModelLayers;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.joml.Quaternionf;

import java.util.Map;

public class PlushieBlockEntityRenderer implements BlockEntityRenderer<PlushieBlockEntity> {
  private final Map<PlushieBlock, Identifier> textureMap;
  private final Map<PlushieBlock, ModelPart> modelMap;

  public PlushieBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    textureMap = Map.of(
      ModBlocks.SILLY_ALIEN_PLUSHIE, ConvenientDecor.id("textures/block/plushie/silly_alien.png"),
      ModBlocks.GNOME_PLUSHIE, ConvenientDecor.id("textures/block/plushie/gnome.png")
    );
    modelMap = Map.of(
      ModBlocks.SILLY_ALIEN_PLUSHIE, context.getLayerModelPart(ModEntityModelLayers.SILLY_ALIEN_PLUSHIE),
      ModBlocks.GNOME_PLUSHIE, context.getLayerModelPart(ModEntityModelLayers.GNOME_PLUSHIE)
    );
  }

  @Override
  public void render(PlushieBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    var block = (PlushieBlock) entity.getCachedState().getBlock();
    var model = modelMap.get(block);
    var texture = textureMap.get(block);
    if (model == null || texture == null || entity.getWorld() == null) return;

    var vertices = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(texture));

    matrices.push();
    {
      matrices.scale(1.0F, -1.0F, -1.0F);
      matrices.scale(1.0F, 1.0F - entity.getSquish(entity.getWorld().getTime() + tickDelta) * 0.5F, 1.0F);
      matrices.translate(0.5F, -1.5F, -0.5F);
      matrices.multiply(new Quaternionf().rotationY(MathHelper.RADIANS_PER_DEGREE * (entity.getRotation() - 180F)));
      model.render(matrices, vertices, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
    }
    matrices.pop();
  }

  public static TexturedModelData getSillyAlienTexturedModelData() {
    var modelData = new ModelData();
    var modelPartData = modelData.getRoot();
    var torso = modelPartData.addChild("torso", ModelPartBuilder.create().uv(0, 11).cuboid(-10.5F, 0.0F, 7.0F, 5.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 17.0F, -8.0F));
    var head = torso.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-11.5F, -6.0F, 6.0F, 7.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
    head.addChild("antenna_right", ModelPartBuilder.create().uv(0, 11).cuboid(-10.5F, -8.0F, 8.5F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
      .uv(0, 2).cuboid(-10.5F, -9.0F, 8.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
    head.addChild("antenna_left", ModelPartBuilder.create().uv(13, 11).cuboid(0.0F, -2.25F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
      .uv(0, 0).cuboid(0.0F, -3.25F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-6.5F, -5.75F, 8.5F, 0.0F, 0.0F, 0.3927F));
    var right_arm = torso.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-10.5F, 0.0F, 8.5F));
    right_arm.addChild("cube_r1", ModelPartBuilder.create().uv(20, 11).cuboid(-5.0F, 0.0F, -1.0F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));
    torso.addChild("left_arm", ModelPartBuilder.create().uv(19, 0).cuboid(0.0F, 0.0F, -1.0F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-5.5F, 0.0F, 8.5F, 0.0F, 0.0F, 0.7854F));
    torso.addChild("tail", ModelPartBuilder.create().uv(0, 27).cuboid(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F))
      .uv(0, 27).cuboid(-0.5F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 5.0F, 10.0F, 0.3927F, 0.0F, 0.0F));
    var left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(1.5F, 23.0F, 0.5F));
    left_leg.addChild("cube_r2", ModelPartBuilder.create().uv(0, 20).cuboid(-1.0F, -1.0F, -4.5F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.5F, 0.0F, -0.3927F, 0.0F));
    var right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-1.5F, 23.0F, 0.5F));
    right_leg.addChild("cube_r3", ModelPartBuilder.create().uv(11, 15).cuboid(-1.0F, -1.0F, -4.5F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.5F, 0.0F, 0.3927F, 0.0F));
    return TexturedModelData.of(modelData, 64, 64);
  }

  public static TexturedModelData getGnomeTexturedModelData() {
    var modelData = new ModelData();
    var modelPartData = modelData.getRoot();
    var torso = modelPartData.addChild("torso", ModelPartBuilder.create().uv(0, 10).cuboid(-3.0F, -8.0F, -3.0F, 6.0F, 7.0F, 6.0F, new Dilation(0.05F))
      .uv(0, 0).cuboid(-1.0F, -8.0F, -5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
    torso.addChild("cube_r1", ModelPartBuilder.create().uv(24, 0).cuboid(-4.0F, 0.0F, -1.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -8.0F, 0.0F, 0.0F, 0.0F, -0.7854F));
    var right_leg = torso.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, -1.0F));
    right_leg.addChild("cube_r2", ModelPartBuilder.create().uv(16, 28).cuboid(-1.0F, -1.0F, -1.5F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 0.0F, 1.0F, 0.0F, 0.7854F, 0.0F));
    var hat_rim = torso.addChild("hat_rim", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -10.0F, -3.25F, 8.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -0.75F));
    var hat_middle = hat_rim.addChild("hat_middle", ModelPartBuilder.create().uv(18, 17).cuboid(-3.0F, -4.5F, 0.0F, 6.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.25F, -2.25F, -0.3927F, 0.0F, 0.0F));
    hat_middle.addChild("hat_top", ModelPartBuilder.create().uv(0, 23).cuboid(-2.0F, -5.0F, 0.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.5F, 1.0F, -0.3927F, 0.0F, 0.0F));
    torso.addChild("left_leg", ModelPartBuilder.create().uv(27, 11).cuboid(-1.0F, -1.0F, -1.5F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -1.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
    torso.addChild("left_arm", ModelPartBuilder.create().uv(18, 10).cuboid(0.0F, 0.0F, -1.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
    return TexturedModelData.of(modelData, 64, 64);
  }
}
