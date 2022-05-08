package com.projectblurple.blurplemod;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.CyclingOption;

@Environment(EnvType.CLIENT)
public class ClientConfig {
    public static boolean reacharound = true;

    public static final CyclingOption<Boolean> REACHAROUND = CyclingOption.create(
            "options.blurple.reacharound",
            gameOptions -> ClientConfig.reacharound,
            (gameOptions, option, showIlluminations) -> ClientConfig.reacharound = showIlluminations);
}
