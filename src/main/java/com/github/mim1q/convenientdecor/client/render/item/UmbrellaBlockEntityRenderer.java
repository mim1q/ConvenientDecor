package com.github.mim1q.convenientdecor.client.render.item;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.block.blockentity.UmbrellaBlockEntity;
import com.github.mim1q.convenientdecor.init.ModEntityModelLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class UmbrellaBlockEntityRenderer implements BlockEntityRenderer<UmbrellaBlockEntity> {
  public static final Identifier TEXTURE = ConvenientDecor.id("textures/blockentity/umbrella/black.png");

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
    matrices.scale(1.0F, -1.0F, -1.0F);
    VertexConsumer consumer = vertexConsumers.getBuffer(unfoldedModel.getLayer(TEXTURE));
    unfoldedModel.render(matrices, consumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
    matrices.pop();
  }
}
