package com.github.mim1q.convenientdecor.client.render.umbrella;

import com.github.mim1q.convenientdecor.block.UmbrellaBlock;
import com.github.mim1q.convenientdecor.block.blockentity.UmbrellaBlockEntity;
import com.github.mim1q.convenientdecor.init.ModBlocks;
import net.minecraft.block.BlockState;
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
    BlockState state = entity.getCachedState();
    UmbrellaBlock block = (UmbrellaBlock) state.getBlock();
    boolean folded = entity.folded;
    float rotation = state.get(UmbrellaBlock.ROTATION) * 22.5F;
    matrices.push();
    matrices.translate(0.5F, 0.05F, 0.5F);
    matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-rotation + 90.0F));
    matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(folded ? 100.0F : 55.0F));
    matrices.translate(0.25F, folded ? -0.6F : -0.5F, 0.0F);
    matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(30.0F));
    umbrellaRenderer.render(matrices, vertexConsumers, light, overlay, block.color, block == ModBlocks.BROKEN_UMBRELLA, folded);
    matrices.pop();
  }
}
