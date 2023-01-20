package com.github.mim1q.convenientdecor;

import com.github.mim1q.convenientdecor.init.ModEntityModelLayers;
import com.github.mim1q.convenientdecor.init.ModRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;

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
          ModRender.initDynamicItemRenderers(context);
          dynamicItemRenderersRegistered = true;
        }
      })
    );
  }
}
