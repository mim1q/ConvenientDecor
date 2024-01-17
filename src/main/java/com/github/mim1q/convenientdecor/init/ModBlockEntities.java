package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.block.blockentity.*;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.List;

public class ModBlockEntities {
  public static final BlockEntityType<WateringCanBlockEntity> WATERING_CAN = register("watering_can",
    FabricBlockEntityTypeBuilder.create(WateringCanBlockEntity::new, ModBlocks.WATERING_CAN).build()
  );

  public static final BlockEntityType<UmbrellaBlockEntity> UMBRELLA = register("umbrella",
    createBuilder(UmbrellaBlockEntity::new, ModBlocks.UMBRELLA.getList(), ModBlocks.BROKEN_UMBRELLA, ModBlocks.ALLERTS_UMBRELLA).build()
  );

  public static final BlockEntityType<UmbrellaStandBlockEntity> UMBRELLA_STAND = register("umbrella_stand",
    FabricBlockEntityTypeBuilder.create(UmbrellaStandBlockEntity::new, ModBlocks.UMBRELLA_STAND).build()
  );

  public static final BlockEntityType<WeatherVaneBlockEntity> WEATHER_VANE = register("weather_vane",
    FabricBlockEntityTypeBuilder.create(WeatherVaneBlockEntity::new,
      ModBlocks.GOLD_WEATHER_VANE,
      ModBlocks.COPPER_WEATHER_VANE,
      ModBlocks.IRON_WEATHER_VANE,
      ModBlocks.NETHERITE_WEATHER_VANE
    ).build()
  );

  public static final BlockEntityType<PlushieBlockEntity> PLUSHIE = register("plushie",
    FabricBlockEntityTypeBuilder.create(PlushieBlockEntity::new, ModBlocks.SILLY_ALIEN_PLUSHIE, ModBlocks.GNOME_PLUSHIE).build()
  );

  public static void init() { }

  public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
    return Registry.register(Registries.BLOCK_ENTITY_TYPE, ConvenientDecor.id(name), type);
  }

  public static <T extends BlockEntity> FabricBlockEntityTypeBuilder<T> createBuilder(
    FabricBlockEntityTypeBuilder.Factory<? extends T> factory,
    List<Block> blocks,
    Block... additionalBlocks
  ) {
    FabricBlockEntityTypeBuilder<T> builder = FabricBlockEntityTypeBuilder.create(factory);
    for (Block block : blocks) {
      builder.addBlock(block);
    }
    builder.addBlocks(additionalBlocks);
    return builder;
  }
}
