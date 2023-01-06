package com.github.mim1q.convenientdecor.mixin.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
  @SuppressWarnings("CancellableInjectionUsage")
  @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
  protected void convenientdecor$isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) { }
}
