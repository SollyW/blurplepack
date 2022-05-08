package com.projectblurple.blurplemod.mixin.bclib;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import ru.bclib.config.ClientConfig;

@Mixin(ClientConfig.class)
public abstract class ClientConfigMixin {
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
    public boolean isAcceptingMods() {
        return false;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isAcceptingConfigs() {
        return false;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isAcceptingFiles() {
        return false;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isShowingModInfo() {
        return false;
    }
}
