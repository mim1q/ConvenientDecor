package com.github.mim1q.convenientdecor.block;

import com.github.mim1q.convenientdecor.block.blockentity.UmbrellaBlockEntity;
import com.github.mim1q.convenientdecor.item.UmbrellaItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UmbrellaBlock extends Block implements BlockEntityProvider {
  public static final IntProperty ROTATION = Properties.ROTATION;
  public static final BooleanProperty FOLDED = BooleanProperty.of("folded");

  public final DyeColor color;

  public UmbrellaBlock(Settings settings, DyeColor color) {
    super(settings.nonOpaque().noCollision());
    this.color = color;
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(ROTATION, FOLDED);
    super.appendProperties(builder);
  }

  @Nullable
  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    return this.getDefaultState()
      .with(ROTATION, MathHelper.floor((double)(ctx.getPlayerYaw() * 16.0F / 360.0F) + 0.5) & 15)
      .with(FOLDED, UmbrellaItem.isFolded(ctx.getStack()));
  }

  @Override
  @SuppressWarnings("deprecation")
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.INVISIBLE;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new UmbrellaBlockEntity(pos, state);
  }

  @Override
  @SuppressWarnings("deprecation")
  public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
    ItemStack stack = this.asItem().getDefaultStack();
    UmbrellaItem.setFolded(stack, state.get(FOLDED));
    return List.of(stack);
  }
}
