package com.github.mim1q.convenientdecor.client.render.umbrella;

import com.github.mim1q.convenientdecor.block.UmbrellaBlock;
import com.github.mim1q.convenientdecor.block.blockentity.UmbrellaBlockEntity;
import com.github.mim1q.convenientdecor.client.render.umbrella.UmbrellaRenderer.UmbrellaRenderHelper;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import org.joml.Quaternionf;

public class UmbrellaBlockEntityRenderer implements BlockEntityRenderer<UmbrellaBlockEntity> {
  private final UmbrellaRenderHelper umbrellaRenderers;

  public UmbrellaBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    umbrellaRenderers = new UmbrellaRenderHelper(ctx.getLayerRenderDispatcher());
  }

  @Override
  public void render(UmbrellaBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    BlockState state = entity.getCachedState();
    UmbrellaBlock block = (UmbrellaBlock) state.getBlock();
    boolean folded = entity.folded;
    int blockRotation = state.get(UmbrellaBlock.ROTATION);
    float rotation = blockRotation * 22.5F;
    boolean leaning = state.get(UmbrellaBlock.LEANING);
    if (leaning) {
      rotation = (float)(((blockRotation + 2) % 16) / 4) * 90.0F;
    }
    matrices.push();
    matrices.translate(0.5F, 0.05F, 0.5F);
    matrices.multiply(new Quaternionf().rotateY(MathHelper.RADIANS_PER_DEGREE * (-rotation + 90.0F)));
    if (leaning) {
      matrices.multiply(new Quaternionf().rotateZ(MathHelper.RADIANS_PER_DEGREE * (folded ? 30.0F : 10.0F)));
      matrices.translate(folded ? 0.3F : 0.65F, folded ? -0.3F : -0.2F, 0.0F);
      matrices.multiply(new Quaternionf().rotateY(MathHelper.RADIANS_PER_DEGREE * (90.0F)));
    } else {
      matrices.multiply(new Quaternionf().rotateZ(MathHelper.RADIANS_PER_DEGREE * (folded ? 100.0F : 55.0F)));
      matrices.translate(0.25F, folded ? -0.6F : -0.5F, 0.0F);
      matrices.multiply(new Quaternionf().rotateY(MathHelper.RADIANS_PER_DEGREE * (30.0F)));
    }
    umbrellaRenderers.getRenderer(state).render(matrices, vertexConsumers, light, overlay, block.color, folded);
    matrices.pop();
  }
}
