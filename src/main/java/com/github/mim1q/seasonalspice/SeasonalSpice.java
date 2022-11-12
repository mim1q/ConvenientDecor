package com.github.mim1q.seasonalspice;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class SeasonalSpice implements ModInitializer {
  public static final String MOD_ID = "seasonalspice";

  @Override
  public void onInitialize() {

  }

  public static Identifier id(String path) {
    return new Identifier(MOD_ID, path);
  }
}
