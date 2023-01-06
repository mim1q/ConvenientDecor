package com.github.mim1q.convenientdecor.mixin.enchantment;

import com.github.mim1q.convenientdecor.init.ModItems;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InfinityEnchantment.class)
public class InfinityEnchantmentMixin extends EnchantmentMixin {
  @Override
  protected void convenientdecor$isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
    if (stack.isOf(ModItems.WATERING_CAN)) {
      cir.setReturnValue(true);
    }
  }
}
