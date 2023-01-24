package com.github.mim1q.convenientdecor.client.render.clothes;

import com.github.mim1q.convenientdecor.init.ModEntityModelLayers;
import com.github.mim1q.convenientdecor.item.RaincoatItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class CustomClothesFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

  private final ClothesModel raincoatModel;

  public CustomClothesFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context, EntityModelLoader loader) {
    super(context);
    this.raincoatModel = new RaincoatModel(loader.getModelPart(ModEntityModelLayers.CLOTHES_RAINCOAT));
  }

  @Override
  public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
    if (entity.getInventory() == null) return;
    ItemStack chestStack = entity.getEquippedStack(EquipmentSlot.CHEST);
    if (chestStack.getItem() instanceof RaincoatItem) {
      renderClothes(raincoatModel, chestStack, matrices, vertexConsumers, light);
    }
  }

  private void renderClothes(ClothesModel model, ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
    VertexConsumer consumer = vertexConsumers.getBuffer(model.getLayer(model.getTexture(stack)));
    model.applyTransform(this.getContextModel(), stack);
    model.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
  }
}
