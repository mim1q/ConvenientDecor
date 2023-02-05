package com.github.mim1q.convenientdecor.block.blockentity;

import com.github.mim1q.convenientdecor.init.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class WeatherVaneBlockEntity extends BlockEntity {
  private static final float BASE_MAX_VELOCITY = 0.5F;
  private static final float BASE_ACCELERATION = 0.35F;

  private float multiplier = 1.0F;
  private float lastYaw = 0.0F;
  private float yaw = 0.0F;
  private float acceleration = 0.0F;
  private float maxVelocity = 0.0F;
  private float velocity = 0.0F;


  public WeatherVaneBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlockEntities.WEATHER_VANE, pos, state);
    yaw = Random.create().nextFloat() * 360.0F;
  }

  public void tick(World world, BlockPos pos, BlockState state) {
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

  public float getYaw(float tickDelta) {
    return lastYaw + tickDelta * (yaw - lastYaw);
  }
}
