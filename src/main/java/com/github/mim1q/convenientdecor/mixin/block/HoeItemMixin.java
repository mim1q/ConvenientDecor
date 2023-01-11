package com.github.mim1q.convenientdecor.mixin.block;

import com.github.mim1q.convenientdecor.block.CustomProperties;
import com.github.mim1q.convenientdecor.init.ModItems;
import com.github.mim1q.convenientdecor.item.WateringCanItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Hand;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(HoeItem.class)
public abstract class HoeItemMixin extends MiningToolItem {
  protected HoeItemMixin(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
    super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
  }

  @Inject(method = "createTillAction", at = @At("RETURN"), cancellable = true)
  private static void createTillAction(BlockState result, CallbackInfoReturnable<Consumer<ItemUsageContext>> cir) {
    if (result.isOf(Blocks.FARMLAND)) {
      cir.setReturnValue((ctx) -> {
        boolean hydrated = false;
        PlayerEntity player = ctx.getPlayer();
        if (player != null) {
          ItemStack offhandStack = player.getStackInHand(Hand.OFF_HAND);
          if (offhandStack != null && offhandStack.isOf(ModItems.WATERING_CAN)) {
            int waterLevel = WateringCanItem.getWaterLevel(offhandStack);
            if (waterLevel > 0) {
              WateringCanItem.setWaterLevel(offhandStack, waterLevel - 1);
              hydrated = true;
            }
          }
        }
        ctx.getWorld().setBlockState(
          ctx.getBlockPos(),
          result.with(CustomProperties.HYDRATED, hydrated).with(FarmlandBlock.MOISTURE, hydrated ? 7 : 0),
          Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD
        );
        ctx.getWorld().emitGameEvent(GameEvent.BLOCK_CHANGE, ctx.getBlockPos(), GameEvent.Emitter.of(ctx.getPlayer(), result));
      });
    }
  }
}
