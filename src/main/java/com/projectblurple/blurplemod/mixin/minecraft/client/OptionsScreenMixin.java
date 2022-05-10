package com.projectblurple.blurplemod.mixin.minecraft.client;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.gui.screen.option.MouseOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = OptionsScreen.class, priority = 999)
public abstract class OptionsScreenMixin extends Screen {
    @Shadow
    @Final
    private GameOptions settings;

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "method_19828", at = @At("HEAD"), cancellable = true, remap = false)
    private void method_19828(ButtonWidget widget, CallbackInfo ci) {
        this.client.setScreen(new VideoOptionsScreen(this, this.settings));
        ci.cancel();
    }

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "method_19825", at = @At("HEAD"), cancellable = true, remap = false)
    private void method_19825(ButtonWidget widget, CallbackInfo ci) {
        this.client.setScreen(new MouseOptionsScreen(this, this.settings));
        ci.cancel();
    }

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "method_19827", at = @At("HEAD"), cancellable = true, remap = false)
    private void method_19827(ButtonWidget widget, CallbackInfo ci) {
        this.client.setScreen(new KeybindsScreen(this, this.settings));
        ci.cancel();
    }

    @Redirect(method = "init",
            slice = @Slice(
                    from = @At(value = "CONSTANT",
                            args = "stringValue=options.language")),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/option/OptionsScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;",
                    ordinal = 0))
    private Element languageScreen(OptionsScreen instance, Element element) {
        return element;
    }

    @ModifyArg(method = "init",
            slice = @Slice(
                    from = @At(value = "CONSTANT",
                            args = "stringValue=options.resourcepack")),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/widget/ButtonWidget;<init>(IIIILnet/minecraft/text/Text;Lnet/minecraft/client/gui/widget/ButtonWidget$PressAction;)V",
                    ordinal = 0),
            index = 1)
    private int modifyY(int y) {
        return y - 24;
    }

    @Redirect(method = "init",
            slice = @Slice(
                    from = @At(value = "CONSTANT",
                            args = "stringValue=options.accessibility.title")),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/option/OptionsScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;",
                    ordinal = 0))
    private Element accessibilityScreen(OptionsScreen instance, Element element) {
        return element;
    }
}
