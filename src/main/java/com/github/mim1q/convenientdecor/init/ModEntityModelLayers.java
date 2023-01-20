package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.client.render.umbrella.UmbrellaModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.TexturedModelDataProvider;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class ModEntityModelLayers {
  public static final EntityModelLayer UMBRELLA_FOLDED = register("umbrella", "folded", UmbrellaModel::getFoldedTexturedModelData);
  public static final EntityModelLayer UMBRELLA_UNFOLDED = register("umbrella", "unfolded", UmbrellaModel::getUnfoldedTexturedModelData);

  public static void init() {

  }

  private static EntityModelLayer register(String id, String name, TexturedModelDataProvider provider) {
    EntityModelLayer layer = new EntityModelLayer(ConvenientDecor.id(id), name);
    EntityModelLayerRegistry.registerModelLayer(layer, provider);
    return layer;
  }
}
