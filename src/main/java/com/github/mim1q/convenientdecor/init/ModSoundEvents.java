package com.github.mim1q.convenientdecor.init;

import com.github.mim1q.convenientdecor.ConvenientDecor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

public class ModSoundEvents {
  public static final SoundEvent GNOME_PLUSHIE_SQUISH = register("gnome_plushie_squish");
  public static final SoundEvent SILLY_ALIEN_PLUSHIE_SQUISH = register("silly_alien_plushie_squish");

  public static void init() { }

  private static SoundEvent register(String name) {
    var id = ConvenientDecor.id(name);
    return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
  }
}
