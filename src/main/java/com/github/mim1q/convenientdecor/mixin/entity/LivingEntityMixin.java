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
import org.spongepowered.asm.mixin.Unique;
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

  @Unique
  private int convenientdecor$umbrellaTimer = 0;

  @Unique
  private boolean convenientdecor$hasUmbrellaInHand(Hand hand, boolean canBeBroken, boolean canBeFolded) {
    ItemStack stack = this.getStackInHand(hand);
    Item item = stack.getItem();
    return item instanceof UmbrellaItem
      && (canBeBroken || item != ModItems.BROKEN_UMBRELLA)
      && (canBeFolded || !UmbrellaItem.isFolded(stack));
  }

  @Unique
  private boolean convenientdecor$holdsUmbrella(boolean canBeBroken, boolean canBeFolded) {
    return convenientdecor$hasUmbrellaInHand(Hand.MAIN_HAND, canBeBroken, canBeFolded)
      || convenientdecor$hasUmbrellaInHand(Hand.OFF_HAND, canBeBroken, canBeFolded);
  }

  @Unique
  private void convenientdecor$tryBurnUmbrella(Hand hand) {
    if (convenientdecor$hasUmbrellaInHand(hand, false, false)) {
      this.setStackInHand(hand, ModItems.BROKEN_UMBRELLA.getDefaultStack());
    }
  }

  @Unique
  private boolean convenientdecor$onlyAirAbove() {
    for (int i = this.getBlockY() + 1; i <= getWorld().getTopY(); i++) {
      if (!getWorld().isAir(this.getBlockPos().withY(i))) {
        return false;
      }
    }
    return true;
  }

  @Inject(method = "tick", at = @At("HEAD"))
  private void tick(CallbackInfo ci) {
    if (!getWorld().isClient) {
      if (convenientdecor$holdsUmbrella(true, false)
        && getWorld().isThundering()
        && getWorld().getTopPosition(Heightmap.Type.MOTION_BLOCKING, this.getBlockPos()).getY() <= this.getY()
        && convenientdecor$onlyAirAbove()
      ) {
        convenientdecor$umbrellaTimer++;
      } else {
        convenientdecor$umbrellaTimer = Math.min(0, convenientdecor$umbrellaTimer + 1);
      }
      if (convenientdecor$umbrellaTimer > 20 * 60 && this.random.nextFloat() < 0.05F) {
        convenientdecor$umbrellaTimer = -(20 * 60 * 5) - this.random.nextInt(20 * 120);
        LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(getWorld());
        if (lightning != null) {
          lightning.setPosition(this.getPos());
          getWorld().spawnEntity(lightning);
          convenientdecor$tryBurnUmbrella(Hand.MAIN_HAND);
          convenientdecor$tryBurnUmbrella(Hand.OFF_HAND);
        }
      }
    }
  }

  @ModifyVariable(method = "damage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
  private float modifyDamageAmount(float amount, DamageSource source) {
    if (source == getWorld().getDamageSources().fall() && convenientdecor$holdsUmbrella(false, false)) {
      return Math.min(0.25F * amount, 8.0F);
    }
    return amount;
  }

  @ModifyVariable(method = "travel", at = @At("STORE"), ordinal = 0)
  private double modifyTravelGravity(double d) {
    if (convenientdecor$holdsUmbrella(false, false) && this.getVelocity().getY() < 0.0D) {
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
      if (getWorld().isThundering()) {
        raincoatBonus = 8.0F;
      } else if (getWorld().isRaining()) {
        raincoatBonus = 4.0F;
      }
    }
    cir.setReturnValue(
      cir.getReturnValue() + raincoatBonus
    );
  }

  @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
  private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
    convenientdecor$umbrellaTimer = nbt.getInt("umbrellaTimer");
  }

  @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
  private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
    nbt.putInt("umbrellaTimer", convenientdecor$umbrellaTimer);
  }
}
