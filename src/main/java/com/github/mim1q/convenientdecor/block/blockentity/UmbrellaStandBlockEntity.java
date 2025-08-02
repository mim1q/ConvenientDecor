package com.github.mim1q.convenientdecor.block.blockentity;

import com.github.mim1q.convenientdecor.init.ModBlockEntities;
import com.github.mim1q.convenientdecor.item.UmbrellaItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class UmbrellaStandBlockEntity extends BlockEntity {
  private ItemStack stack = ItemStack.EMPTY;

  public UmbrellaStandBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlockEntities.UMBRELLA_STAND, pos, state);
  }

  public ItemStack getStack() {
    return stack;
  }

  public void setStack(ItemStack stack) {
    if (world != null && !world.isClient) {
      UmbrellaItem.setFolded(stack, true);
      this.stack = stack;
      world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_LISTENERS);
    }
  }

  public boolean hasStack() {
    return stack != null && !stack.isEmpty();
  }

  @Nullable
  @Override
  public Packet<ClientPlayPacketListener> toUpdatePacket() {
    return BlockEntityUpdateS2CPacket.create(this);
  }

  @Override
  public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup lookup) {
    return createNbt(lookup);
  }

  @Override
  public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
    super.readNbt(nbt, lookup);
    if (nbt.contains("stack") && !nbt.getCompound("stack").isEmpty()) {
      stack = ItemStack.fromNbt(lookup, nbt.getCompound("stack")).orElse(ItemStack.EMPTY);
    } else {
      stack = ItemStack.EMPTY;
    }
  }

  @Override
  protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
    super.writeNbt(nbt, lookup);
    var stackNbt = ItemStack.OPTIONAL_CODEC.encode(stack, NbtOps.INSTANCE, new NbtCompound()).getOrThrow();

    nbt.put("stack", stackNbt);
  }
}
