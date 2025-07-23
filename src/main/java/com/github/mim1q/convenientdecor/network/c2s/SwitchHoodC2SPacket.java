package com.github.mim1q.convenientdecor.network.c2s;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record SwitchHoodC2SPacket(
  int slot
) implements CustomPayload {
  public static final PacketCodec<PacketByteBuf, SwitchHoodC2SPacket> CODEC = PacketCodec.tuple(
    PacketCodecs.INTEGER,
    SwitchHoodC2SPacket::slot,
    SwitchHoodC2SPacket::new
  );

  public static CustomPayload.Id<SwitchHoodC2SPacket> TYPE = new CustomPayload.Id<>(ConvenientDecor.id("switch_hood"));

  @Override
  public Id<? extends CustomPayload> getId() {
    return TYPE;
  }
}
