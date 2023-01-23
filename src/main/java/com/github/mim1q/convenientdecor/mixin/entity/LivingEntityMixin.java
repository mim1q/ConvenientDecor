package com.github.mim1q.convenientdecor.mixin.entity;

import com.github.mim1q.convenientdecor.init.ModItems;
import com.github.mim1q.convenientdecor.item.RaincoatItem;
import com.github.mim1q.convenientdecor.item.UmbrellaItem;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
  @Shadow public abstract ItemStack getStackInHand(Hand hand);
  @Shadow public abstract void setStackInHand(Hand hand, ItemStack stack);
  @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

  public LivingEntityMixin(EntityType<?> type, World world) {
    super(type, world);
  }

  private int umbrellaTimer = 0;

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

  private void tryBurnUmbrella(Hand hand) {
    if (hasUmbrellaInHand(hand, false, false)) {
      this.setStackInHand(hand, ModItems.BROKEN_UMBRELLA.getDefaultStack());
    }
  }

  private boolean onlyAirAbove() {
    for (int i = this.getBlockY() + 1; i <= world.getTopY(); i++) {
      if (!world.isAir(this.getBlockPos().withY(i))) {
        return false;
      }
    }
    return true;
  }

  @Inject(method = "tick", at = @At("HEAD"))
  private void tick(CallbackInfo ci) {
    if (!this.world.isClient) {
      if (holdsUmbrella(true, false)
        && world.isThundering()
        && world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, this.getBlockPos()).getY() <= this.getY()
        && onlyAirAbove()
      ) {
        umbrellaTimer++;
      } else {
        umbrellaTimer = Math.min(0, umbrellaTimer + 1);
      }
      if (umbrellaTimer > 20 * 60 && this.random.nextFloat() < 0.5F) {
        umbrellaTimer = -(20 * 60 * 4) - this.random.nextInt(20 * 120);
        LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(this.world);
        if (lightning != null) {
          lightning.setPosition(this.getPos());
          this.world.spawnEntity(lightning);
          tryBurnUmbrella(Hand.MAIN_HAND);
          tryBurnUmbrella(Hand.OFF_HAND);
        }
      }
    }
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

  @Inject(method = "getMaxHealth", at = @At("RETURN"), cancellable = true)
  @SuppressWarnings("DataFlowIssue")
  private void getMaxHealth(CallbackInfoReturnable<Float> cir) {
    if (!this.isPlayer() || ((PlayerEntity)(Object) this).getInventory() == null) {
      return;
    }
    float raincoatBonus = 0.0F;
    if (this.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof RaincoatItem) {
      if (this.world.isThundering()) {
        raincoatBonus = 8.0F;
      } else if (this.world.isRaining()) {
        raincoatBonus = 4.0F;
      }
    }
    cir.setReturnValue(
      cir.getReturnValue() + raincoatBonus
    );
  }

  @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
  private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
    umbrellaTimer = nbt.getInt("umbrellaTimer");
  }

  @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
  private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
    nbt.putInt("umbrellaTimer", umbrellaTimer);
  }
}
