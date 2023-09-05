package com.github.mim1q.convenientdecor.client.render.blockentity;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.block.blockentity.WeatherVaneBlockEntity;
import com.github.mim1q.convenientdecor.init.ModBlocks;
import com.github.mim1q.convenientdecor.init.ModEntityModelLayers;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.joml.Quaternionf;

public class WeatherVaneBlockEntityRenderer implements BlockEntityRenderer<WeatherVaneBlockEntity> {
  public static final Identifier TEXTURE_GOLD = ConvenientDecor.id("textures/block/weather_vane/gold_top.png");
  public static final Identifier TEXTURE_COPPER = ConvenientDecor.id("textures/block/weather_vane/copper_top.png");
  public static final Identifier TEXTURE_IRON = ConvenientDecor.id("textures/block/weather_vane/iron_top.png");
  public static final Identifier TEXTURE_NETHERITE = ConvenientDecor.id("textures/block/weather_vane/netherite_top.png");

  private final WeatherVaneModel model;

  public WeatherVaneBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    model = new WeatherVaneModel(context.getLayerModelPart(ModEntityModelLayers.WEATHER_VANE));
  }

  @Override
  public void render(WeatherVaneBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    matrices.push();
    {
      VertexConsumer vertices = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(getTexture(entity.getCachedState().getBlock())));
      matrices.scale(1.0F, -1.0F, -1.0F);
      matrices.translate(0.5F, -1.125F, -0.5F);
      matrices.push();
      {
        matrices.multiply(new Quaternionf().rotationY(entity.getYaw(tickDelta) * MathHelper.RADIANS_PER_DEGREE));
        matrices.translate(0.0F, -0.8125F, 0.0F);
        model.render(matrices, vertices, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
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

  public static class WeatherVaneModel extends Model {
    private final ModelPart root;

    public WeatherVaneModel(ModelPart root) {
      super(RenderLayer::getEntityCutout);
      this.root = root;
    }

    public static TexturedModelData getTexturedModelData() {
      ModelData modelData = new ModelData();
      ModelPartData modelPartData = modelData.getRoot();
     modelPartData.addChild("main", ModelPartBuilder.create().uv(0, -16).mirrored().cuboid(0.0F, -12.0F, -8.0F, 0.0F, 12.0F, 16.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
      return TexturedModelData.of(modelData, 32, 16);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
      this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
  }
}
