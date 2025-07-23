package com.github.mim1q.convenientdecor.network;

import com.github.mim1q.convenientdecor.item.RaincoatItem;
import com.github.mim1q.convenientdecor.network.c2s.SwitchHoodC2SPacket;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class ModServerPacketHandler {
  public static void init() {
    PayloadTypeRegistry.playC2S().register(SwitchHoodC2SPacket.TYPE, SwitchHoodC2SPacket.CODEC);

    ServerPlayNetworking.registerGlobalReceiver(
      SwitchHoodC2SPacket.TYPE,
      ModServerPacketHandler::onSwitchHood
    );
  }

  public static void onSwitchHood(SwitchHoodC2SPacket packet, ServerPlayNetworking.Context context) {
    int slotId = packet.slot();

    //noinspection resource
    context.server().execute(() -> {
      Inventory inventory = context.player().getInventory();
      if (inventory == null) {
        return;
      }
      ItemStack stack = inventory.getStack(slotId);
      if (stack.getItem() instanceof RaincoatItem) {
        RaincoatItem.setHooded(stack, !RaincoatItem.isHooded(stack));
      }
    });
  }
}
