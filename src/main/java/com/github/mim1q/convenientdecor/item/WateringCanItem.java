package com.github.mim1q.convenientdecor.item;

import com.github.mim1q.convenientdecor.block.CustomProperties;
import com.github.mim1q.convenientdecor.block.blockentity.WateringCanBlockEntity;
import com.github.mim1q.convenientdecor.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WateringCanItem extends Item {

  public static final int MAX_WATER_LEVEL = 32;
  public static final String WATER_LEVEL_KEY = "item.convenientdecor.watering_can.water_level";

  public WateringCanItem(Settings settings) {
    super(settings);
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    BlockHitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.WATER);
    ItemStack stack = user.getStackInHand(hand);
    if (hitResult.getType() == HitResult.Type.MISS) {
      return TypedActionResult.pass(stack);
    }
    TypedActionResult<ItemStack> result = this.tryUseOnBlock(world, user, stack, hitResult);
    if (result != null) {
      return result;
    }
    ItemPlacementContext ctx = new ItemPlacementContext(user, hand, stack, hitResult);
    TypedActionResult<ItemStack> result2 = this.tryPlace(ctx);
    if (result2.getResult().isAccepted()) {
      BlockEntity entity = world.getBlockEntity(ctx.getBlockPos());
      if (entity instanceof WateringCanBlockEntity wateringCan) {
        wateringCan.setStackNbt(stack);
      }
    }
    return result2;
  }

  protected TypedActionResult<ItemStack> tryUseOnBlock(World world, PlayerEntity user, ItemStack stack, BlockHitResult hitResult) {
    Vec3d pos = hitResult.getPos();
    BlockState state = world.getBlockState(hitResult.getBlockPos());
    if (state.getFluidState().isIn(FluidTags.WATER)) {
      if (getWaterLevel(stack) < MAX_WATER_LEVEL) {
        WateringCanItem.setWaterLevel(stack, MAX_WATER_LEVEL);
        world.playSound(pos.x, pos.y, pos.z, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1.0F, 1.0F, true);
        return TypedActionResult.success(stack);
      }
      return TypedActionResult.fail(stack);
    }
    if (state.isOf(Blocks.FARMLAND)) {
      if (user.isSneaking()) {
        return null;
      }
      if (!WateringCanItem.canWater(stack) || state.get(CustomProperties.HYDRATED)) {
        return TypedActionResult.fail(stack);
      }
      WateringCanItem.setWaterLevel(stack, WateringCanItem.getWaterLevel(stack) - 1);
      world.setBlockState(
        hitResult.getBlockPos(),
        Blocks.FARMLAND.getDefaultState()
          .with(CustomProperties.HYDRATED, true)
          .with(FarmlandBlock.MOISTURE, 7)
      );
      world.playSound(pos.x, pos.y, pos.z, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 1.0F, 1.0F, true);
      return TypedActionResult.success(stack);
    }
    return null;
  }

  protected TypedActionResult<ItemStack> tryPlace(ItemPlacementContext ctx) {
    World world = ctx.getWorld();
    BlockPos pos = ctx.getBlockPos();
    BlockState state = world.getBlockState(pos);
    ItemStack stack = ctx.getStack();
    PlayerEntity user = ctx.getPlayer();
    BlockState newState = ModBlocks.WATERING_CAN.getPlacementState(ctx);
    if (!world.canPlace(newState, pos, ShapeContext.absent())) {
      return TypedActionResult.fail(stack);
    }
    if (state.getMaterial().isReplaceable()) {
      world.setBlockState(pos, newState);
      world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, (BlockSoundGroup.METAL.getVolume() + 1.0F) / 2.0F, BlockSoundGroup.METAL.getPitch() * 0.8F, true);
      world.emitGameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Emitter.of(user, newState));
      stack.decrement(1);
    }
    return TypedActionResult.success(stack);
  }

  @Override
  public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
    super.appendTooltip(stack, world, tooltip, context);
    tooltip.add(Text.translatable(WATER_LEVEL_KEY, WateringCanItem.getWaterLevel(stack), MAX_WATER_LEVEL).formatted(Formatting.AQUA));
  }

  @Override
  public boolean isItemBarVisible(ItemStack stack) {
    return getWaterLevel(stack) < MAX_WATER_LEVEL;
  }

  @Override
  public ItemStack getDefaultStack() {
    ItemStack stack = super.getDefaultStack();
    WateringCanItem.setWaterLevel(stack, 0);
    return stack;
  }

  public ItemStack getStack(int waterLevel) {
    ItemStack stack = super.getDefaultStack();
    WateringCanItem.setWaterLevel(stack, waterLevel);
    return stack;
  }

  @Override
  public int getItemBarColor(ItemStack stack) {
    return 0x00AAFF;
  }

  @Override
  public int getItemBarStep(ItemStack stack) {
    return Math.round((WateringCanItem.getWaterLevel(stack) / 32.0f) * 13.0F);
  }

  public static int getWaterLevel(ItemStack stack) {
    return stack.getOrCreateNbt().getInt("WaterLevel");
  }

  public static void setWaterLevel(ItemStack stack, int waterLevel) {
    if (
      EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) == 0
      || waterLevel > getWaterLevel(stack)
    ) {
      stack.getOrCreateNbt().putInt("WaterLevel", waterLevel);
    }
  }

  public static boolean canWater(ItemStack stack) {
    return WateringCanItem.getWaterLevel(stack) > 0;
  }
}
