package com.github.mim1q.convenientdecor.item.material;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ModArmorMaterials {
//  public static RegistryEntry<ArmorMaterial> RAINCOAT = register(
//    "convenientdecor_raincoat",
//    10,
//    new int[]{1, 3, 2, 1},
//    15,
//    SoundEvents.ITEM_ARMOR_EQUIP_LEATHER.value(),
//    0.0F,
//    0.0F,
//    () -> Ingredient.ofItems(Items.WHITE_WOOL)
//  );
//
//  private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};

  public static final RegistryEntry<ArmorMaterial> RAINCOAT = ArmorMaterials.register(
    "convenientdecor_raincoat",
    new EnumMap<>(Map.of(
      ArmorItem.Type.HELMET, 13,
      ArmorItem.Type.CHESTPLATE, 15,
      ArmorItem.Type.LEGGINGS, 16,
      ArmorItem.Type.BOOTS, 11
    )),
    1,
    SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
    0f,
    0.0F,
    () -> Ingredient.ofItems(Items.WHITE_WOOL),
    List.of()
  );
}
