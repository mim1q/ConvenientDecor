package com.github.mim1q.convenientdecor.config;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;

public class ConvenientDecorConfig implements Config {

    @Comment("Turn individual functionalities on or off.")
    public final Features features = new Features();

    @Override
    public String getName() {
        return "convenientdecor";
    }

    @Override
    public String getExtension() {
        return "json5";
    }

    public static class Features {
        @Comment(" Right-clicking a farmland block with a watering can will permanently hydrate it.")
        public boolean wateringCanPermanentFarmland = true;
        @Comment(" Umbrella attracts lightning in the thunderstorm")
        public boolean umbrellaAttractsLightning = true;
        @Comment(" Umbrella slows down the player's falling speed and reduces fall damage.")
        public boolean umbrellaSlowsDownFalling = true;
        @Comment(" Rain clothes grant additional hearts in the rain and thunderstorm.")
        public boolean rainclothesIncreasedHp = true;


        @Comment(" The umbrella protects players from rain and sunlight when used with the Origins mod.")
        public boolean umbrellaOriginsIntegration = true;
        @Comment(" Rainhats and Raincoats (with the hood activated) protect players from rain when used with the Origins mod")
        public boolean rainclothesOriginsIntegration = true;
    }
}
