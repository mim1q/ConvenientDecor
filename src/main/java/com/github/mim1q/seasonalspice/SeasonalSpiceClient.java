package com.github.mim1q.seasonalspice;

import com.github.mim1q.seasonalspice.init.SeasonalSpiceRender;
import net.fabricmc.api.ClientModInitializer;

public class SeasonalSpiceClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    SeasonalSpiceRender.initBlocks();
  }
}
