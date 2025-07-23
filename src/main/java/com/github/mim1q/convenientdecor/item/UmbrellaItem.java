package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.block.UmbrellaBlock;
import com.github.mim1q.convenientdecor.util.LegacyUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class UmbrellaItem extends BlockItem {
  public final DyeColor color;

  public UmbrellaItem(Item.Settings settings, UmbrellaBlock block, DyeColor color) {
    super(block, settings);
    this.color = color;
  }

  @Override
  public ActionResult useOnBlock(ItemUsageContext context) {
    if (context.getPlayer() != null && !context.getPlayer().isSneaking()) {
      return ActionResult.PASS;
    }
    return super.useOnBlock(context);
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    ItemStack stack = user.getStackInHand(hand);
    if (!world.isClient) {
      setFolded(stack, !isFolded(stack));
      world.playSound(null, user.getBlockPos().up(), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
    return TypedActionResult.success(stack, true);
  }

  public static void setFolded(ItemStack stack, boolean folded) {
    var nbt = LegacyUtil.getOrEmptyNbt(stack);
    nbt.putBoolean("folded", folded);
    LegacyUtil.writeNbt(stack, nbt);
  }

  public static boolean isFolded(ItemStack stack) {
    var nbt = LegacyUtil.getOrEmptyNbt(stack);
    if (nbt == null) return false;
    return nbt.getBoolean("folded");
  }
}
