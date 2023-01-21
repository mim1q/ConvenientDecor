package com.github.mim1q.convenientdecor.mixin.entity;

import com.github.mim1q.convenientdecor.init.ModItems;
import com.github.mim1q.convenientdecor.item.UmbrellaItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
  @Shadow public abstract ItemStack getStackInHand(Hand hand);

  public LivingEntityMixin(EntityType<?> type, World world) {
    super(type, world);
  }

  private boolean hasUmbrellaInHand(Hand hand, boolean canBeBroken, boolean canBeFolded) {
    ItemStack stack = this.getStackInHand(hand);
    Item item = stack.getItem();
    return item instanceof UmbrellaItem
      && (canBeBroken || item != ModItems.BROKEN_UMBRELLA)
      && (canBeFolded || !UmbrellaItem.isFolded(stack));
  }

  private boolean holdsUmbrella(boolean canBeBroken, boolean canBeFolded) {
    return hasUmbrellaInHand(Hand.MAIN_HAND, canBeBroken, canBeFolded)
      || hasUmbrellaInHand(Hand.OFF_HAND, canBeBroken, canBeFolded);
  }

  @ModifyVariable(method = "damage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
  private float modifyDamageAmount(float amount, DamageSource source) {
    if (source == DamageSource.FALL && holdsUmbrella(false, false)) {
      return Math.min(0.25F * amount, 8.0F);
    }
    return amount;
  }

  @ModifyVariable(method = "travel", at = @At("STORE"), ordinal = 0)
  private double modifyTravelGravity(double d) {
    if (holdsUmbrella(false, false) && this.getVelocity().getY() < 0.0D) {
      return Math.min(d, 0.025F);
    }
    return d;
  }
}
