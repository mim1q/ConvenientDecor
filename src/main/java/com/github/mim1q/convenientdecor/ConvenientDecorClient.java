package com.github.mim1q.convenientdecor;

import com.github.mim1q.convenientdecor.init.ModEntityModelLayers;
import com.github.mim1q.convenientdecor.init.ModRender;
import net.fabricmc.api.ClientModInitializer;

public class ConvenientDecorClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    ModRender.initBlocks();
    ModRender.initItems();
    ModRender.init();
    ModEntityModelLayers.init();
  }
}
