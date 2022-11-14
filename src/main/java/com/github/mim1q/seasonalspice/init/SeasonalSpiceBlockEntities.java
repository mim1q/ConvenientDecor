package com.github.mim1q.seasonalspice.init;

import com.github.mim1q.seasonalspice.SeasonalSpice;
import com.github.mim1q.seasonalspice.block.blockentity.WateringCanBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class SeasonalSpiceBlockEntities {
  public static final BlockEntityType<WateringCanBlockEntity> WATERING_CAN = register(
    "watering_can",
    FabricBlockEntityTypeBuilder.create(WateringCanBlockEntity::new, SeasonalSpiceBlocks.WATERING_CAN).build()
  );

  public static void init() { }

  public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
    return Registry.register(Registry.BLOCK_ENTITY_TYPE, SeasonalSpice.id(name), type);
  }
}
