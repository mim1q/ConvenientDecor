package com.github.mim1q.convenientdecor.init.group;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class ColoredGroup<T, U extends ColoredGroup<T, U>> {
  private final Map<DyeColor, T> entries = new HashMap<>();

  protected ColoredGroup() { }

  public U add(DyeColor color, ColoredConstructor<T> constructor) {
    this.entries.put(color, constructor.create(color));
    return (U) this;
  }

  public U add16Colors(ColoredConstructor<T> constructor) {
    for (DyeColor color : DyeColor.values()) {
      this.entries.put(color, constructor.create(color));
    }
    return (U) this;
  }

  public T get(DyeColor color) {
    return entries.get(color);
  }

  // Returns an arbitrarily (roughly by color) ordered list
  public List<T> getList() {
    return List.of(
      get(DyeColor.BLACK),
      get(DyeColor.GRAY),
      get(DyeColor.LIGHT_GRAY),
      get(DyeColor.WHITE),
      get(DyeColor.BROWN),
      get(DyeColor.BLUE),
      get(DyeColor.CYAN),
      get(DyeColor.LIGHT_BLUE),
      get(DyeColor.LIME),
      get(DyeColor.GREEN),
      get(DyeColor.YELLOW),
      get(DyeColor.ORANGE),
      get(DyeColor.RED),
      get(DyeColor.PINK),
      get(DyeColor.MAGENTA),
      get(DyeColor.PURPLE)
    );
  }

  protected abstract void registerSingle(Identifier id, T entry);

  public U register(String baseName) {
    for (Map.Entry<DyeColor, T> entry : entries.entrySet()) {
      registerSingle(ConvenientDecor.id(entry.getKey().getName() + "_" + baseName), entry.getValue());
    }
    return (U) this;
  }

  public static ColoredItemGroup ofItems() {
    return new ColoredItemGroup();
  }

  public static ColoredBlockGroup ofBlocks(boolean withBlockItem) {
    return new ColoredBlockGroup(withBlockItem);
  }

  @FunctionalInterface
  public interface ColoredConstructor<T> {
    T create(DyeColor color);
  }

  public static class ColoredBlockGroup extends ColoredGroup<Block, ColoredBlockGroup> {
    private final boolean withBlockItem;
    protected ColoredBlockGroup(boolean withBlockItem) {
      this.withBlockItem = withBlockItem;
    }

    @Override
    protected void registerSingle(Identifier id, Block entry) {
      Registry.register(Registries.BLOCK, id, entry);
      if (withBlockItem) {
        Registry.register(Registries.ITEM, id, new BlockItem(entry, new FabricItemSettings()));
      }
    }

    public List<ItemStack> getItemStackList() {
      return getList().stream().map(block -> block.asItem().getDefaultStack()).toList();
    }
  }

  public static class ColoredItemGroup extends ColoredGroup<Item, ColoredItemGroup> {
    @Override
    protected void registerSingle(Identifier id, Item entry) {
      Registry.register(Registries.ITEM, id, entry);
    }

    public List<ItemStack> getItemStackList() {
      return getList().stream().map(item -> item.getDefaultStack()).toList();
    }
  }
}
