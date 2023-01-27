package com.github.mim1q.convenientdecor.network.c2s;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

public class SwitchHoodC2SPacket extends ModC2SPacket {
  public static final Identifier ID = ConvenientDecor.id("switch_hood");

  public SwitchHoodC2SPacket(Slot slot) {
    super(ID);
    writeInt(slot.getIndex());
  }
}
