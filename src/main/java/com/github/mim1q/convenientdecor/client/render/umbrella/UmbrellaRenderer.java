package com.github.mim1q.convenientdecor.client.render.umbrella;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.init.ModBlocks;
import com.github.mim1q.convenientdecor.init.ModEntityModelLayers;
import com.github.mim1q.convenientdecor.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class UmbrellaRenderer {
  public static final Map<DyeColor, Identifier> TEXTURES = new HashMap<>();
  private final Identifier textureOverride;

  static {
    for (DyeColor color : DyeColor.values()) {
      TEXTURES.put(color, ConvenientDecor.id("textures/blockentity/umbrella/" + color.getName() + ".png"));
    }
  }

  private final UmbrellaModel foldedModel;
  private final UmbrellaModel unfoldedModel;

  private UmbrellaRenderer(EntityModelLoader loader) {
    this(loader, null);
  }

  private UmbrellaRenderer(EntityModelLoader loader, Identifier texture) {
    foldedModel = new UmbrellaModel(loader.getModelPart(ModEntityModelLayers.UMBRELLA_FOLDED));
    unfoldedModel = new UmbrellaModel(loader.getModelPart(ModEntityModelLayers.UMBRELLA_UNFOLDED));
    textureOverride = texture;
  }

  private UmbrellaRenderer(EntityModelLoader loader, EntityModelLayer foldedLayer, EntityModelLayer unfoldedLayer, Identifier texture) {
    this.foldedModel = new UmbrellaModel(loader.getModelPart(foldedLayer));
    this.unfoldedModel = new UmbrellaModel(loader.getModelPart(unfoldedLayer));
    this.textureOverride  = texture;
  }

  public Identifier getTexture(DyeColor color) {
    return textureOverride == null ? TEXTURES.get(color) : textureOverride;
  }

  public void render(
    MatrixStack matrices,
    VertexConsumerProvider vertexConsumers,
    int light,
    int overlay,
    DyeColor color,
    boolean folded
  ) {
    UmbrellaModel model = folded ? foldedModel : unfoldedModel;
    VertexConsumer consumer = vertexConsumers.getBuffer(model.getLayer(getTexture(color)));
    model.render(matrices, consumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
  }

  public static class UmbrellaRenderHelper {
    public static final Identifier BROKEN_TEXTURE = ConvenientDecor.id("textures/blockentity/umbrella/broken.png");
    public static final Identifier ALLERTS_UMBRELLA_TEXTURE = ConvenientDecor.id("textures/blockentity/umbrella/allert.png");

    private final UmbrellaRenderer defaultRenderer;
    private final UmbrellaRenderer brokenRenderer;
    private final UmbrellaRenderer allertsRenderer;

    public UmbrellaRenderHelper(EntityModelLoader loader) {
      defaultRenderer = new UmbrellaRenderer(loader);
      brokenRenderer = new UmbrellaRenderer(loader, BROKEN_TEXTURE);
      allertsRenderer = new UmbrellaRenderer(loader, ModEntityModelLayers.ALLERTS_UMBRELLA_FOLDED, ModEntityModelLayers.ALLERTS_UMBRELLA_UNFOLDED, ALLERTS_UMBRELLA_TEXTURE);
    }

    public UmbrellaRenderer getRenderer(ItemStack stack) {
      if (stack.isOf(ModItems.BROKEN_UMBRELLA)) {
        return brokenRenderer;
      }
      if (stack.isOf(ModItems.ALLERTS_UMBRELLA)) {
        return allertsRenderer;
      }
      return defaultRenderer;
    }

    public UmbrellaRenderer getRenderer(BlockState state) {
      if (state.isOf(ModBlocks.BROKEN_UMBRELLA)) {
        return brokenRenderer;
      }
      if (state.isOf(ModBlocks.ALLERTS_UMBRELLA)) {
        return allertsRenderer;
      }
      return defaultRenderer;
    }
  }
}
