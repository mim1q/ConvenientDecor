package com.github.mim1q.seasonalspice;

import com.github.mim1q.seasonalspice.init.SeasonalSpiceBlocks;
import com.github.mim1q.seasonalspice.init.SeasonalSpiceItemGroups;
import com.github.mim1q.seasonalspice.init.SeasonalSpiceItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class SeasonalSpice implements ModInitializer {
  public static final String MOD_ID = "seasonalspice";

  @Override
  public void onInitialize() {
    SeasonalSpiceBlocks.init();
    SeasonalSpiceItems.init();
    SeasonalSpiceItemGroups.init();
  }

  public static Identifier id(String path) {
    return new Identifier(MOD_ID, path);
  }
}
