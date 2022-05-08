package com.projectblurple.blurplemod.mixin.bclib;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import ru.bclib.config.ServerConfig;

@Mixin(ServerConfig.class)
public abstract class ServerConfigMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isAllowingAutoSync() {
        return false;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isOfferingConfigs() {
        return false;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isOfferingFiles() {
        return false;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isOfferingMods() {
        return false;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isOfferingAllMods() {
        return false;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isOfferingInfosForMods() {
        return false;
    }
}
