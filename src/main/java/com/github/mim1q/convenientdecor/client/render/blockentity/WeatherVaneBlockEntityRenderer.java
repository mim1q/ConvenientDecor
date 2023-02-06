package com.github.mim1q.convenientdecor.client.render.blockentity;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.block.blockentity.WeatherVaneBlockEntity;
import com.github.mim1q.convenientdecor.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3f;

public class WeatherVaneBlockEntityRenderer implements BlockEntityRenderer<WeatherVaneBlockEntity> {
  public static final Identifier TEXTURE_GOLD = ConvenientDecor.id("textures/block/weather_vane/gold.png");
  public static final Identifier TEXTURE_COPPER = ConvenientDecor.id("textures/block/weather_vane/copper.png");
  public static final Identifier TEXTURE_IRON = ConvenientDecor.id("textures/block/weather_vane/iron.png");
  public static final Identifier TEXTURE_NETHERITE = ConvenientDecor.id("textures/block/weather_vane/netherite.png");

  public WeatherVaneBlockEntityRenderer(BlockEntityRendererFactory.Context context) { }

  @Override
  public void render(WeatherVaneBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    matrices.push();
    {
      VertexConsumer vertices = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(getTexture(entity.getCachedState().getBlock())));
      matrices.scale(1.0F, -1.0F, -1.0F);
      matrices.translate(0.5F, -1.125F, -0.5F);
      matrices.push();
      {
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(entity.getYaw(MinecraftClient.getInstance().getTickDelta())));
        renderTop(matrices, vertices, light);
      }
      matrices.pop();
      matrices.translate(0.0F, 0.75F, 0.0F);
    }
    matrices.pop();
  }

  private static Identifier getTexture(Block block) {
    if (block == ModBlocks.GOLD_WEATHER_VANE) return TEXTURE_GOLD;
    if (block == ModBlocks.COPPER_WEATHER_VANE) return TEXTURE_COPPER;
    if (block == ModBlocks.IRON_WEATHER_VANE) return TEXTURE_IRON;
    return TEXTURE_NETHERITE;
  }

  protected void renderTop(MatrixStack matrices, VertexConsumer vertices, int light) {
    Matrix4f posM = matrices.peek().getPositionMatrix();
    Matrix3f normM = matrices.peek().getNormalMatrix();
    vertex(posM, normM, vertices, light, 0.0F, 0.0F, -8.0F, 0.0F, 0.0F, 255);
    vertex(posM, normM, vertices, light, 0.0F, 0.0F, 8.0F, 16.0F, 0.0F, 255);
    vertex(posM, normM, vertices, light, 0.0F, 12.0F,8.0F, 16.0F, 12.0F, 255);
    vertex(posM, normM, vertices, light, 0.0F, 12.0F,-8.0F, 0.0F, 12.0F, 255);

    vertex(posM, normM, vertices, light, 0.0F, 12.0F,-8.0F, 0.0F, 12.0F, 255);
    vertex(posM, normM, vertices, light, 0.0F, 12.0F,8.0F, 16.0F, 12.0F, 255);
    vertex(posM, normM, vertices, light, 0.0F, 0.0F, 8.0F, 16.0F, 0.0F, 255);
    vertex(posM, normM, vertices, light, 0.0F, 0.0F, -8.0F, 0.0F, 0.0F, 255);
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
