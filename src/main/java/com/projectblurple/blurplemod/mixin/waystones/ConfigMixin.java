package com.projectblurple.blurplemod.mixin.waystones;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import wraith.waystones.util.Config;

@Mixin(Config.class)
public abstract class ConfigMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean generateInVillages() {
        return false;
    }
}
