package com.github.mim1q.convenientdecor.client.render.umbrella;

import com.github.mim1q.convenientdecor.block.UmbrellaBlock;
import com.github.mim1q.convenientdecor.block.blockentity.UmbrellaBlockEntity;
import com.github.mim1q.convenientdecor.init.ModBlocks;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;

public class UmbrellaBlockEntityRenderer implements BlockEntityRenderer<UmbrellaBlockEntity> {

  private final UmbrellaRenderer umbrellaRenderer;

  public UmbrellaBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    EntityModelLoader loader = ctx.getLayerRenderDispatcher();
    umbrellaRenderer = new UmbrellaRenderer(loader);
  }

  @Override
  public void render(UmbrellaBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    UmbrellaBlock block = (UmbrellaBlock) entity.getCachedState().getBlock();
    matrices.push();
    matrices.translate(0.5F, 0.05F, 0.5F);
    matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(55.0F));
    matrices.translate(0.25F, -0.5F, 0.0F);
    matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(30.0F));
    umbrellaRenderer.render(matrices, vertexConsumers, light, overlay, block.color, block == ModBlocks.BROKEN_UMBRELLA, false);
    matrices.pop();
  }
}
