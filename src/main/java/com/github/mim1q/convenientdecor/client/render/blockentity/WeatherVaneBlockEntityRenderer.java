package com.github.mim1q.convenientdecor.client.render.blockentity;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.block.blockentity.WeatherVaneBlockEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;

public class WeatherVaneBlockEntityRenderer implements BlockEntityRenderer<WeatherVaneBlockEntity> {
  public static final Identifier TEXTURE = ConvenientDecor.id("textures/block/weather_vane/copper.png");

  @Override
  public void render(WeatherVaneBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    matrices.push();
    VertexConsumer vertices = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(TEXTURE));
    matrices.scale(1.0F, -1.0F, -1.0F);
    matrices.translate(0.5F, -1.125F, -0.5F);
    renderTop(matrices, vertices, light);
    matrices.pop();
  }

  protected void renderTop(MatrixStack matrices, VertexConsumer vertices, int light) {
    Matrix4f posM = matrices.peek().getPositionMatrix();
    Matrix3f normM = matrices.peek().getNormalMatrix();
    vertex(posM, normM, vertices, light, 0.0F, 0.0F, -7.5F, 1.0F, 0.0F, 255);
    vertex(posM, normM, vertices, light, 0.0F, 0.0F, 7.5F, 16.0F, 0.0F, 255);
    vertex(posM, normM, vertices, light, 0.0F, 12.0F,7.5F, 16.0F, 12.0F, 255);
    vertex(posM, normM, vertices, light, 0.0F, 12.0F,-7.5F, 1.0F, 12.0F, 255);

    vertex(posM, normM, vertices, light, 0.0F, 12.0F,-7.5F, 1.0F, 12.0F, 255);
    vertex(posM, normM, vertices, light, 0.0F, 12.0F,7.5F, 16.0F, 12.0F, 255);
    vertex(posM, normM, vertices, light, 0.0F, 0.0F, 7.5F, 16.0F, 0.0F, 255);
    vertex(posM, normM, vertices, light, 0.0F, 0.0F, -7.5F, 1.0F, 0.0F, 255);
  }

  protected void vertex(Matrix4f positionMatrix, Matrix3f normalMatrix, VertexConsumer vertices, int light, float x, float y, float z, float u, float v, int alpha) {
    vertices.vertex(positionMatrix, x / 16.0F, y / 16.0F, z / 16.0F)
      .color(255, 255, 255, alpha)
      .texture(u / 16.0F, v / 16.0F)
      .overlay(OverlayTexture.DEFAULT_UV)
      .light(light)
      .normal(normalMatrix, 0.0F, 1.0F, 0.0F)
      .next();
  }
}
