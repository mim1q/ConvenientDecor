package com.github.mim1q.convenientdecor;

import com.github.mim1q.convenientdecor.config.ConvenientDecorConfig;
import com.github.mim1q.convenientdecor.init.*;
import com.github.mim1q.convenientdecor.network.ModServerPacketHandler;
import draylar.omegaconfig.OmegaConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConvenientDecor implements ModInitializer {
  public static final String MOD_ID = "convenientdecor";
  public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

  public static final ConvenientDecorConfig CONFIG = OmegaConfig.register(ConvenientDecorConfig.class);

  @Override
  public void onInitialize() {
    ModBlocks.init();
    ModBlockEntities.init();
    ModItems.init();
    ModItemGroups.init();
    ModServerPacketHandler.init();
    ModSoundEvents.init();
  }

  public static Identifier id(String path) {
    return new Identifier(MOD_ID, path);
  }
}
