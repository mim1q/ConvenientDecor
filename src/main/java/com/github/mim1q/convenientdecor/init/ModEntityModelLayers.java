package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.client.render.blockentity.PlushieBlockEntityRenderer;
import com.github.mim1q.convenientdecor.client.render.blockentity.WeatherVaneBlockEntityRenderer;
import com.github.mim1q.convenientdecor.client.render.clothes.RaincoatModel;
import com.github.mim1q.convenientdecor.client.render.umbrella.SupportersUmbrellasModelData;
import com.github.mim1q.convenientdecor.client.render.umbrella.UmbrellaModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.TexturedModelDataProvider;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class ModEntityModelLayers {
  public static final EntityModelLayer UMBRELLA_FOLDED = register("umbrella", "folded", UmbrellaModel::getFoldedTexturedModelData);
  public static final EntityModelLayer UMBRELLA_UNFOLDED = register("umbrella", "unfolded", UmbrellaModel::getUnfoldedTexturedModelData);
  public static final EntityModelLayer ALLERTS_UMBRELLA_FOLDED = register("allerts_umbrella", "folded", SupportersUmbrellasModelData::getAllertsFolded);
  public static final EntityModelLayer ALLERTS_UMBRELLA_UNFOLDED = register("allerts_umbrella", "unfolded", SupportersUmbrellasModelData::getAllertsUnfolded);
  public static final EntityModelLayer CLOTHES_RAINCOAT = register("clothes", "raincoat", RaincoatModel::getTexturedModelData);
  public static final EntityModelLayer WEATHER_VANE = register("weather_vane", "main", WeatherVaneBlockEntityRenderer.WeatherVaneModel::getTexturedModelData);

  // Plushies
  public static final EntityModelLayer SILLY_ALIEN_PLUSHIE = register("plushie", "silly_alien", PlushieBlockEntityRenderer::getSillyAlienTexturedModelData);
  public static final EntityModelLayer GNOME_PLUSHIE = register("plushie", "gnome", PlushieBlockEntityRenderer::getGnomeTexturedModelData);

  public static void init() {

  }

  private static EntityModelLayer register(String id, String name, TexturedModelDataProvider provider) {
    EntityModelLayer layer = new EntityModelLayer(ConvenientDecor.id(id), name);
    EntityModelLayerRegistry.registerModelLayer(layer, provider);
    return layer;
  }
}
