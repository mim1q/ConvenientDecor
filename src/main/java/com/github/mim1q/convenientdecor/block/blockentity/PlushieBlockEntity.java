package com.github.mim1q.convenientdecor.block.blockentity;

import com.github.mim1q.convenientdecor.init.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PlushieBlockEntity extends BlockEntity {
  private float rotation = 0.0F;
  private long squishTime = 0L;

  public PlushieBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlockEntities.PLUSHIE, pos, state);
  }

  public float getRotation() {
    return rotation;
  }

  public void setRotation(float rotation) {
    this.rotation = rotation;
    markDirty();
  }

  public void squish(World world, SoundEvent sound, long time) {
    if (time - squishTime > 5L) {
      world.playSound(null, pos, sound, SoundCategory.BLOCKS, 0.2F, 1.0F + world.random.nextFloat() * 0.1F);
      squishTime = time;
    }
  }

  public float getSquish(float time) {
    // ease out elastic
    float t = (time - squishTime) / 10.0F;
    if (t < 0.0F) return 1.0F;
    if (t > 1.0F) return 0.0F;
    return (float) (Math.pow(2.0, -10.0 * t) * Math.sin((t - 0.075) * (2.0 * Math.PI) / 0.3));
  }

  @Override
  public NbtCompound toInitialChunkDataNbt() {
    return createNbt();
  }

  @Nullable
  @Override
  public Packet<ClientPlayPacketListener> toUpdatePacket() {
    return BlockEntityUpdateS2CPacket.create(this);
  }

  @Override
  public void writeNbt(NbtCompound nbt) {
    super.writeNbt(nbt);
    nbt.putFloat("rotation", rotation);
  }

  @Override
  public void readNbt(NbtCompound nbt) {
    super.readNbt(nbt);
    rotation = nbt.getFloat("rotation");
  }
}
