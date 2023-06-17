package com.github.mim1q.convenientdecor.client.render.umbrella;

import com.github.mim1q.convenientdecor.client.render.umbrella.UmbrellaRenderer.UmbrellaRenderHelper;
import com.github.mim1q.convenientdecor.item.UmbrellaItem;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.model.json.ModelTransformation.Mode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;

public class UmbrellaItemRenderer implements DynamicItemRenderer {
  private final UmbrellaRenderHelper umbrellaRenderers;


  public UmbrellaItemRenderer(EntityModelLoader loader) {
    umbrellaRenderers = new UmbrellaRenderHelper(loader);
  }

  @Override
  public void render(ItemStack stack, Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    UmbrellaItem item = (UmbrellaItem) stack.getItem();
    boolean folded = UmbrellaItem.isFolded(stack);
    if (mode == Mode.GUI) {
      float scale = folded ? 0.66F : 0.5F;
      matrices.scale(scale, scale, scale);
      matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-30.0F));
      matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(30.0F));
      matrices.translate(folded ? 1.1F : 1.6F, folded ? 0.0F : 0.15F, 0.0F);
    }
    if (mode == Mode.FIRST_PERSON_RIGHT_HAND || mode == Mode.FIRST_PERSON_LEFT_HAND) {
      float scale = folded ? 1.25F : 1.5F;
      matrices.scale(scale, scale, scale);
      matrices.translate(
        mode == Mode.FIRST_PERSON_RIGHT_HAND ? 0.8F : -0.13F,
        folded ? -0.5F : -0.1F,
        folded ? -0.1F : 0.1F
      );
      matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
    }
    if (mode == Mode.GROUND) {
      matrices.translate(0.5F, 0.25F, 0.5F);
      matrices.scale(0.25F, 0.25F, 0.25F);
    }
    if (mode == Mode.THIRD_PERSON_LEFT_HAND || mode == Mode.THIRD_PERSON_RIGHT_HAND) {
      if (folded) {
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(mode == Mode.THIRD_PERSON_LEFT_HAND ? 10.0F : -10.0F));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
        matrices.translate(-0.5F, 0.2F, -0.7F);
      } else {
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(70.0F));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(210.0F));
        matrices.translate(-0.6F, 0.55F, 0.0F);
      }
    }
    if (mode == Mode.FIXED) {
      matrices.translate(0.45F, 0.0F, 0.0F);
      matrices.scale(0.5F, 0.5F, 0.5F);
      matrices.translate(0.1F, 0.15F, 1.0F);
      matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
    }
    umbrellaRenderers.getRenderer(stack).render(matrices, vertexConsumers, light, overlay, item.color, folded);
  }
}
