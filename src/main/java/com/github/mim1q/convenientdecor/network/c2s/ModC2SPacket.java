package com.github.mim1q.convenientdecor.network.c2s;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class ModC2SPacket extends PacketByteBuf {
  private final Identifier channelId;

  public ModC2SPacket(Identifier id) {
    super(Unpooled.buffer());
    channelId = id;
  }

  public Identifier getId() {
    return channelId;
  }

  public void send() {
    ClientPlayNetworking.send(getId(), this);
  }
}
