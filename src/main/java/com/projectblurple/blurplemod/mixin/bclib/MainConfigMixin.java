package com.projectblurple.blurplemod.mixin.bclib;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import ru.bclib.config.MainConfig;
import ru.bclib.config.ServerConfig;

@Mixin(MainConfig.class)
public abstract class MainConfigMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean applyPatches() {
        return false;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean repairBiomes() {
        return false;
    }
}
