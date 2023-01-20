package com.github.mim1q.convenientdecor.client.render.umbrella;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.init.ModEntityModelLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class UmbrellaRenderer {
  public static final Map<DyeColor, Identifier> TEXTURES = new HashMap<>();
  public static final Identifier BROKEN_TEXTURE = ConvenientDecor.id("textures/blockentity/umbrella/broken.png");

  static {
    for (DyeColor color : DyeColor.values()) {
      TEXTURES.put(color, ConvenientDecor.id("textures/blockentity/umbrella/" + color.getName() + ".png"));
    }
  }

  private final UmbrellaModel foldedModel;
  private final UmbrellaModel unfoldedModel;
  public UmbrellaRenderer(EntityModelLoader loader) {
    this.foldedModel = new UmbrellaModel(loader.getModelPart(ModEntityModelLayers.UMBRELLA_FOLDED));
    this.unfoldedModel = new UmbrellaModel(loader.getModelPart(ModEntityModelLayers.UMBRELLA_UNFOLDED));
  }

  public static Identifier getTexture(DyeColor color, boolean broken) {
    return broken ? BROKEN_TEXTURE : TEXTURES.get(color);
  }

  public void render(
    MatrixStack matrices,
    VertexConsumerProvider vertexConsumers,
    int light,
    int overlay,
    DyeColor color,
    boolean broken,
    boolean folded
  ) {
    UmbrellaModel model = folded ? foldedModel : unfoldedModel;
    VertexConsumer consumer = vertexConsumers.getBuffer(model.getLayer(getTexture(color, broken)));
    model.render(matrices, consumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
  }
}
