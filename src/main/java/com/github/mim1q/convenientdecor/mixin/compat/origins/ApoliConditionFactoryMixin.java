package com.github.mim1q.convenientdecor.mixin.compat.origins;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.item.ModItemTags;
import com.github.mim1q.convenientdecor.item.RaincoatItem;
import com.github.mim1q.convenientdecor.item.UmbrellaItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;
import java.util.function.BiFunction;

@SuppressWarnings("rawtypes")
@Pseudo
@Mixin(targets = "io.github.apace100.apoli.power.factory.condition.ConditionFactory")
public class ApoliConditionFactoryMixin<T> {
  @Mutable
  @Shadow
  @Final
  private BiFunction condition;

  @Unique
  private BiFunction oldCondition;

  @Unique
  private static Identifier apoliId(String name) {
    return new Identifier("apoli", name);
  }

  @Unique
  private static final Set<Identifier> CONDITIONS_NEGATED_BY_UMBRELLA = Set.of(
    apoliId("exposed_to_sky"),
    apoliId("exposed_to_sun")
  );

  @Unique
  private static final Set<Identifier> CONDITIONS_NEGATED_BY_RAIN_CLOTHES_OR_UMBRELLA = Set.of(
    apoliId("in_rain")
  );

  @SuppressWarnings({"unchecked", "UnresolvedMixinReference"})
  @Inject(
    method = "<init>(Lnet/minecraft/util/Identifier;Lio/github/apace100/calio/data/SerializableData;Ljava/util/function/BiFunction;)V",
    at = @At("TAIL")
  )
  private void init(Identifier identifier, @Coerce Object data, BiFunction condition, CallbackInfo ci) {
    this.oldCondition = condition;
    if (CONDITIONS_NEGATED_BY_UMBRELLA.contains(identifier)) {
      ConvenientDecor.LOGGER.info("Patching condition: " + identifier + " to negate with umbrellas");
      this.condition = (a, b) -> (boolean) this.oldCondition.apply(a, b) && !holdingUmbrella((LivingEntity) b);
    }
    if (CONDITIONS_NEGATED_BY_RAIN_CLOTHES_OR_UMBRELLA.contains(identifier)) {
      ConvenientDecor.LOGGER.info("Patching condition: " + identifier + " to negate with rain clothes");
      this.condition = (a, b) -> (boolean) this.oldCondition.apply(a, b) && !hasRainClothesOrUmbrella((LivingEntity) b);
    }
  }

  @Unique
  private static boolean holdingUmbrella(LivingEntity entity) {
    return holdingUmbrellaInHand(entity, Hand.MAIN_HAND) || holdingUmbrellaInHand(entity, Hand.OFF_HAND);
  }

  @Unique
  private static boolean holdingUmbrellaInHand(LivingEntity entity, Hand hand) {
    var stack = entity.getStackInHand(hand);
    return stack.isIn(ModItemTags.UMBRELLAS)
      && !UmbrellaItem.isFolded(stack);
  }

  @Unique
  private static boolean hasRainClothesOrUmbrella(LivingEntity entity) {
    var hasRainHat = entity.getEquippedStack(EquipmentSlot.HEAD).isIn(ModItemTags.RAIN_HATS);
    var rainCoat = entity.getEquippedStack(EquipmentSlot.CHEST);
    var hasRainCoatWithHood = rainCoat.isIn(ModItemTags.RAINCOATS) && RaincoatItem.isHooded(rainCoat);
    return holdingUmbrella(entity) || hasRainHat || hasRainCoatWithHood;
  }
}
