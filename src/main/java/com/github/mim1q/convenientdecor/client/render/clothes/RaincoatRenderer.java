package com.github.mim1q.convenientdecor.client.render.clothes;

import com.github.mim1q.convenientdecor.init.ModEntityModelLayers;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class RaincoatRenderer implements ArmorRenderer {
  private final RaincoatModel model;

  public RaincoatRenderer(EntityModelLoader loader) {
    this.model = new RaincoatModel(loader.getModelPart(ModEntityModelLayers.CLOTHES_RAINCOAT));
  }

  @Override
  public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {
    VertexConsumer consumer = vertexConsumers.getBuffer(model.getLayer(model.getTexture(stack)));
    model.applyTransform(entity, contextModel, stack);
    model.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
  }
}
