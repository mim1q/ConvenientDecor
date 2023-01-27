package com.github.mim1q.convenientdecor.network;

import com.github.mim1q.convenientdecor.item.RaincoatItem;
import com.github.mim1q.convenientdecor.network.c2s.SwitchHoodC2SPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class ModServerPacketHandler {
  public static void init() {
    ServerPlayNetworking.registerGlobalReceiver(SwitchHoodC2SPacket.ID, ModServerPacketHandler::onSwitchHood);
  }

  public static void onSwitchHood(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
    int slotId = buf.readInt();

    server.execute(() -> {
      Inventory inventory = player.getInventory();
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
