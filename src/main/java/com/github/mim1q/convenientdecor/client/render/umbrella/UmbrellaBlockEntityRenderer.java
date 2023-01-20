package com.github.mim1q.convenientdecor.client.render.umbrella;

import com.github.mim1q.convenientdecor.block.blockentity.UmbrellaBlockEntity;
import com.github.mim1q.convenientdecor.init.ModEntityModelLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;

public class UmbrellaBlockEntityRenderer implements BlockEntityRenderer<UmbrellaBlockEntity> {
  private final UmbrellaModel foldedModel;
  private final UmbrellaModel unfoldedModel;

  public UmbrellaBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    EntityModelLoader loader = ctx.getLayerRenderDispatcher();
    this.foldedModel = new UmbrellaModel(loader.getModelPart(ModEntityModelLayers.UMBRELLA_FOLDED));
    this.unfoldedModel = new UmbrellaModel(loader.getModelPart(ModEntityModelLayers.UMBRELLA_UNFOLDED));
  }

  @Override
  public void render(UmbrellaBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    matrices.push();
    matrices.translate(0.5F, 0.05F, 0.5F);
    matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(55.0F));
    matrices.translate(0.25F, -0.5F, 0.0F);
    matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(30.0F));
    VertexConsumer consumer = vertexConsumers.getBuffer(unfoldedModel.getLayer(entity.getTexture()));
    unfoldedModel.render(matrices, consumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
    matrices.pop();
  }
}
