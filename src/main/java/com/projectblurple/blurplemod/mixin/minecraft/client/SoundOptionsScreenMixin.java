package com.projectblurple.blurplemod.mixin.minecraft.client;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(SoundOptionsScreen.class)
public abstract class SoundOptionsScreenMixin {
    @Redirect(method = "init",
            slice = @Slice(
                    from = @At(value = "FIELD",
                            target = "Lnet/minecraft/client/option/Option;SUBTITLES:Lnet/minecraft/client/option/CyclingOption;")),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/option/SoundOptionsScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;",
                    ordinal = 0))
    private Element addDrawableChild(SoundOptionsScreen instance, Element element) {
        return element;
    }
}
