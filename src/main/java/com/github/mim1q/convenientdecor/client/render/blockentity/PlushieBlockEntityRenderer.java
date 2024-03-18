package com.github.mim1q.convenientdecor.client.render.blockentity;

import com.github.mim1q.convenientdecor.block.blockentity.PlushieBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.joml.Quaternionf;

public class PlushieBlockEntityRenderer implements BlockEntityRenderer<PlushieBlockEntity> {
  private final BlockRenderManager blockRenderManager;
  private final BlockModelRenderer blockRenderer;

  public PlushieBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    this.blockRenderManager = context.getRenderManager();
    this.blockRenderer = blockRenderManager.getModelRenderer();
  }

  @Override
  public void render(PlushieBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

    var world = entity.getWorld();
    if (world == null) return;

    var block = entity.getCachedState().getBlock().getDefaultState();
    var time = world.getTime() + tickDelta;
    var model = blockRenderManager.getModel(block);

    matrices.push();
    {
      matrices.scale(1.0F, 1.0F - entity.getSquish(time) * 0.5F, 1.0F);
      matrices.translate(0.5, 0.5, 0.5);
      matrices.multiply(new Quaternionf().rotationY(MathHelper.RADIANS_PER_DEGREE * (-entity.getRotation())));
      matrices.translate(-0.5, -0.5, -0.5);

      blockRenderer.render(world, model, block, BlockPos.ORIGIN, matrices, vertexConsumers.getBuffer(RenderLayer.getCutout()), true, world.random, 0, light);
    }
    matrices.pop();
  }
}
