package com.github.mim1q.convenientdecor;

import com.github.mim1q.convenientdecor.init.ModEntityModelLayers;
import com.github.mim1q.convenientdecor.init.ModRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;

import static com.github.mim1q.convenientdecor.ConvenientDecor.CONFIG;

public class ConvenientDecorClient implements ClientModInitializer {
  private static boolean dynamicItemRenderersRegistered = false;

  @Override
  public void onInitializeClient() {
    ModRender.initBlocks();
    ModRender.initItems();
    ModRender.init();
    ModEntityModelLayers.init();

    LivingEntityFeatureRendererRegistrationCallback.EVENT.register(
      ((entityType, entityRenderer, registrationHelper, context) -> {
        if (!dynamicItemRenderersRegistered) {
          ModRender.initArmorRenderers(context);
          ModRender.initDynamicItemRenderers(context);
          dynamicItemRenderersRegistered = true;
        }
      })
    );

    if (CONFIG.features.wateringCanPermanentFarmland) {
      ResourceManagerHelper.registerBuiltinResourcePack(
          ConvenientDecor.id("hydrated_farmland"),
          FabricLoader.getInstance().getModContainer("convenientdecor").orElseThrow(),
          ResourcePackActivationType.ALWAYS_ENABLED
      );
    }
  }
}
