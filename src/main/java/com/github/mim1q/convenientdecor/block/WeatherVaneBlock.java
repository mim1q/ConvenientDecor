package com.github.mim1q.convenientdecor.block;

import com.github.mim1q.convenientdecor.block.blockentity.WeatherVaneBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.level.ServerWorldProperties;
import org.jetbrains.annotations.Nullable;

public class WeatherVaneBlock extends Block implements BlockEntityProvider {
  public WeatherVaneBlock() {
    super(FabricBlockSettings.of(Material.METAL).breakInstantly().noCollision().nonOpaque());
  }

  public static float getWeatherChangePrediction(ServerWorld world) {
    ServerWorldProperties properties = (ServerWorldProperties) world.getLevelProperties();
    if (world.isThundering()) {
      return MathHelper.clamp((13000.0F - properties.getThunderTime()) / 12000.0F, 0.0F, 1.0F);
    }
    if (world.isRaining()) {
      return MathHelper.clamp((13000.0F - properties.getRainTime()) / 12000.0F, 0.0F, 1.0F);
    }
    return MathHelper.clamp((13000.0F - properties.getClearWeatherTime()) / 12000.0F, 0.0F, 1.0F);
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean emitsRedstonePower(BlockState state) {
    return true;
  }

  @Override
  @SuppressWarnings("deprecation")
  public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
    WeatherVaneBlockEntity entity = (WeatherVaneBlockEntity) world.getBlockEntity(pos);
    if (entity == null) {
      return 0;
    }
    return (int) (entity.getMultiplier() * 1.5F);
  }

  @Override
  @SuppressWarnings("deprecation")
  public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
    if (direction == Direction.UP) {
      return getWeakRedstonePower(state, world, pos, direction);
    }
    return 0;
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
    return (w, pos, s, entity) -> ((WeatherVaneBlockEntity) entity).tick(w, pos, s);
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new WeatherVaneBlockEntity(pos, state);
  }
}
