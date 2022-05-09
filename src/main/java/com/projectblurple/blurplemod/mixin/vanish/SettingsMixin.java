package com.projectblurple.blurplemod.mixin.vanish;

import eu.vanish.data.Settings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Settings.class)
public abstract class SettingsMixin {
    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite(remap = false)
    public boolean showFakeLeaveMessage() {
        return false;
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite(remap = false)
    public boolean showFakeJoinMessage() {
        return false;
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite(remap = false)
    public boolean showStatusInPlayerlist() {
        return false;
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite(remap = false)
    public boolean removeChatMessage() {
        return false;
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite(remap = false)
    public boolean removeWisperMessage() {
        return false;
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite(remap = false)
    public boolean removeCommandOPMessage() {
        return false;
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite(remap = false)
    public boolean overwriteMsgCommand() {
        return false;
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite(remap = false)
    public boolean overwriteListCommand() {
        return false;
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite(remap = false)
    public boolean fakePlayerCount() {
        return false;
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite(remap = false)
    public boolean isLogVanishToConsole() {
        return true;
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite(remap = false)
    public boolean isLogUnvanishToConsole() {
        return true;
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite(remap = false)
    public boolean isPersistent() {
        return false;
    }
}
