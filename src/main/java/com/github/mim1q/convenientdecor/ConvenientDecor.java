package com.github.mim1q.convenientdecor;

import com.github.mim1q.convenientdecor.init.ModBlockEntities;
import com.github.mim1q.convenientdecor.init.ModBlocks;
import com.github.mim1q.convenientdecor.init.ModItemGroups;
import com.github.mim1q.convenientdecor.init.ModItems;
import com.github.mim1q.convenientdecor.network.ModServerPacketHandler;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class ConvenientDecor implements ModInitializer {
  public static final String MOD_ID = "convenientdecor";

  @Override
  public void onInitialize() {
    ModBlocks.init();
    ModBlockEntities.init();
    ModItems.init();
    ModItemGroups.init();
    ModServerPacketHandler.init();
  }

  public static Identifier id(String path) {
    return new Identifier(MOD_ID, path);
  }
}
