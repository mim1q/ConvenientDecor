package com.github.mim1q.convenientdecor.init.group;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import com.github.mim1q.convenientdecor.init.ModItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
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
      Registry.register(Registry.BLOCK, id, entry);
      if (withBlockItem) {
        Registry.register(Registry.ITEM, id, new BlockItem(entry, new FabricItemSettings().group(ModItemGroups.CONVENIENT_DECOR)));
      }
    }
  }

  public static class ColoredItemGroup extends ColoredGroup<Item, ColoredItemGroup> {
    @Override
    protected void registerSingle(Identifier id, Item entry) {
      Registry.register(Registry.ITEM, id, entry);
    }
  }
}
