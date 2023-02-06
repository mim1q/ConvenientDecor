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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.level.ServerWorldProperties;
import org.jetbrains.annotations.Nullable;

public class WeatherVaneBlock extends Block implements BlockEntityProvider {
  public static final String FORECAST_MODE_MESSAGE = "block.convenientdecor.weather_vane.forecast_mode";
  public static final String DETECTION_MODE_MESSAGE = "block.convenientdecor.weather_vane.detection_mode";

  public final int timeUnit;
  public static final BooleanProperty FORECAST_MODE = BooleanProperty.of("forecast_mode");

  public WeatherVaneBlock(int timeUnit) {
    super(FabricBlockSettings.of(Material.METAL).breakInstantly().noCollision().nonOpaque());
    this.timeUnit = timeUnit;
    this.setDefaultState(getDefaultState().with(FORECAST_MODE, false));
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FORECAST_MODE);
  }

  @Override
  @SuppressWarnings("deprecation")
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (!world.isClient) {
      boolean currentlyForecast = state.get(FORECAST_MODE);
      world.setBlockState(pos, state.with(FORECAST_MODE, !currentlyForecast));
      player.sendMessage(Text.translatable(currentlyForecast ? DETECTION_MODE_MESSAGE : FORECAST_MODE_MESSAGE), true);
    }
    return ActionResult.SUCCESS;
  }

  public static int getWeatherPredictionStrength(ServerWorld world, int timeUnit) {
    ServerWorldProperties properties = (ServerWorldProperties) world.getLevelProperties();
    if (world.isRaining() || properties.getClearWeatherTime() == 0) {
      return getStrengthFromRemainingTime(properties.getRainTime(), timeUnit);
    }
    return getStrengthFromRemainingTime(properties.getClearWeatherTime(), timeUnit);
  }

  public static int getStrengthFromRemainingTime(float remainingTime, int timeUnit) {
    int timeUnits = (int) (remainingTime / timeUnit) + 1;
    return MathHelper.clamp(16 - timeUnits, 0, 15);
  }

  public static int getStrengthFromWeather(ServerWorld world) {
    if (world.isThundering()) {
      return 15;
    }
    if (world.isRaining()) {
      return 7;
    }
    return 0;
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
    return (int) (entity.getMultiplier() - 1);
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
