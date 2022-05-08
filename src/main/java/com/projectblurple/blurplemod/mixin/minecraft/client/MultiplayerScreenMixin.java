package com.projectblurple.blurplemod.mixin.minecraft.client;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MultiplayerScreen.class)
public abstract class MultiplayerScreenMixin extends Screen {
    @Shadow
    private ButtonWidget buttonJoin;

    private MultiplayerScreenMixin(Text title) {
        super(title);
    }

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/multiplayer/MultiplayerScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 1))
    protected Element addDrawableChild2(MultiplayerScreen instance, Element element) {
        this.buttonJoin.setWidth(70);
        this.buttonJoin.setMessage(new LiteralText("Join"));

        if (element instanceof ButtonWidget buttonWidget) {
            buttonWidget.setWidth(70);
            buttonWidget.x = this.width / 2 - 74;
            buttonWidget.setMessage(new LiteralText("Direct"));
            return this.addDrawableChild(buttonWidget);
        }

        throw new AssertionError("addDrawableChild2 mixin error");
    }

    @SuppressWarnings("ConstantConditions")
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/multiplayer/MultiplayerScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 2))
    protected Element addDrawableChild3(MultiplayerScreen instance, Element element) {
        if (element instanceof ButtonWidget buttonWidget) {
            this.addDrawableChild(new ButtonWidget(
                    this.width / 2 + 4 + 76,
                    buttonWidget.y,
                    75,
                    20,
                    new LiteralText("Singleplayer"),
                    button -> this.client.setScreen(new SelectWorldScreen(this))));

            buttonWidget.setWidth(70);
            buttonWidget.x = this.width / 2 + 4;
            buttonWidget.setMessage(new LiteralText("Add"));
            return this.addDrawableChild(buttonWidget);
        }

        throw new AssertionError("addDrawableChild2 mixin error");
    }
}
