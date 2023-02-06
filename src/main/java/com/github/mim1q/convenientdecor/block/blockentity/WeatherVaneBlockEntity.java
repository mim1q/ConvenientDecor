package com.github.mim1q.convenientdecor.block.blockentity;

import com.github.mim1q.convenientdecor.block.WeatherVaneBlock;
import com.github.mim1q.convenientdecor.init.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.level.ServerWorldProperties;

public class WeatherVaneBlockEntity extends BlockEntity {
  private static final float BASE_MAX_VELOCITY = 0.5F;
  private static final float BASE_ACCELERATION = 0.35F;

  private float lastYaw = 0.0F;
  private float yaw = Random.create().nextFloat() * 360.0F;
  private float acceleration = 0.0F;
  private float maxVelocity = 0.0F;
  private float velocity = 0.0F;

  public WeatherVaneBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlockEntities.WEATHER_VANE, pos, state);
  }

  public void tick(World world, BlockPos pos, BlockState state) {
    int multiplier = state.get(WeatherVaneBlock.POWER) + 1;
    if (!world.isClient && world.getTime() % 20 == 0) {
      updatePower((ServerWorld) world, state);
      markDirty();
      return;
    }
    Random rng = world.getRandom();
    if (world.getTime() % 20 == 0 && rng.nextFloat() < 0.25F * multiplier) {
      maxVelocity = rng.nextFloat() * BASE_MAX_VELOCITY * multiplier;
      acceleration = (rng.nextBoolean() ? -1.0F : 1.0F) * (BASE_ACCELERATION * rng.nextFloat());
    }
    float nextVelocity = velocity + (acceleration * multiplier);
    if (Math.abs(nextVelocity) > maxVelocity * multiplier) {
      velocity = maxVelocity * multiplier * Math.signum(nextVelocity);
    } else {
      velocity = nextVelocity;
    }
    lastYaw = yaw;
    yaw += velocity;
    acceleration *= 0.9F;
    velocity *= 0.95F;
  }

  public void updatePower(ServerWorld world, BlockState state) {
    int power = (state.get(WeatherVaneBlock.FORECAST_MODE)
      ? getWeatherPredictionStrength(world, ((WeatherVaneBlock) state.getBlock()).timeUnit)
      : getStrengthFromWeather(world));
    int lastPower = state.get(WeatherVaneBlock.POWER);
    world.setBlockState(this.getPos(), state.with(WeatherVaneBlock.POWER, power));
    if (power != lastPower) {
      world.updateNeighbors(this.getPos(), this.getCachedState().getBlock());
      world.updateNeighbors(this.getPos().down(), this.getCachedState().getBlock());
    }
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

  public float getYaw(float tickDelta) {
    return lastYaw + tickDelta * (yaw - lastYaw);
  }
}
