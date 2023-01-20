package com.github.mim1q.convenientdecor.client.render.umbrella;

import com.github.mim1q.convenientdecor.init.ModItems;
import com.github.mim1q.convenientdecor.item.UmbrellaItem;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.model.json.ModelTransformation.Mode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;

public class UmbrellaItemRenderer implements DynamicItemRenderer {

  private final UmbrellaRenderer umbrellaRenderer;

  public UmbrellaItemRenderer(EntityModelLoader loader) {
    this.umbrellaRenderer = new UmbrellaRenderer(loader);
  }

  @Override
  public void render(ItemStack stack, Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    UmbrellaItem item = (UmbrellaItem) stack.getItem();
    boolean folded = true;
    boolean broken = item == ModItems.BROKEN_UMBRELLA;
    switch (mode) {
      case GUI -> {
        float scale = folded ? 0.66F : 0.5F;
        matrices.scale(scale, scale, scale);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-30.0F));
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(30.0F));
        matrices.translate(folded ? 1.1F : 1.6F, folded ? 0.0F : 0.15F, 0.0F);
      }
    }
    umbrellaRenderer.render(matrices, vertexConsumers, light, overlay, item.color, broken, folded);
  }
}
