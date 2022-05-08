package com.projectblurple.blurplemod.mixin.minecraft.client;

import com.mojang.blaze3d.systems.RenderSystem;
import me.axieum.mcmod.authme.api.gui.widget.AuthButtonWidget;
import me.axieum.mcmod.authme.api.util.SessionUtils;
import me.axieum.mcmod.authme.impl.gui.AuthMethodScreen;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    @Shadow
    @Final
    @Mutable
    public static Text COPYRIGHT;

    private static final Identifier BRUSH_TEXTURE = new Identifier("blurplemod", "textures/gui/title/brush.png");
    private static final ServerInfo PBMC_SERVER_INFO = new ServerInfo(
            "PBMC",
            "192.169.82.150:25569",
            false);

    static {
        PBMC_SERVER_INFO.setResourcePackPolicy(ServerInfo.ResourcePackPolicy.ENABLED);
    }

    private TitleScreenMixin(Text title) {
        super(title);
    }

    @Redirect(method = "init()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;"))
    private <T extends Element & Drawable & Selectable> T addDrawableChild2(TitleScreen instance, T drawableElement) {
        if (!(drawableElement instanceof TexturedButtonWidget)) {
            return this.addDrawableChild(drawableElement);
        }
        return null;
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIFFIIII)V"))
    private void drawTexture2(MatrixStack matrices, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        TitleScreen.drawTexture(matrices, x - width / 2, y - 2 * height / 5, u, v, width * 2, height * 2, textureWidth * 2, textureHeight * 2);
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;drawCenteredText(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V"))
    private void drawCenteredText2(MatrixStack matrices, TextRenderer textRenderer, String text, int centerX, int y, int color) {
        RenderSystem.setShaderTexture(0, BRUSH_TEXTURE);
        TitleScreen.drawTexture(matrices, centerX + 16, y, 0, 0, 32, 58, 32, 58);
    }

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 1, shift = At.Shift.AFTER), cancellable = true)
    private void initWidgetsNormal(CallbackInfo ci) {
        addDrawableChild(
                new AuthButtonWidget(
                        this,
                        this.width / 2 - 100,
                        this.height / 4 + 96,
                        btn -> client.setScreen(new AuthMethodScreen(this)),
                        null,
                        (btn, mtx, x, y) -> renderTooltip(mtx, new TranslatableText(
                                "gui.authme.button.auth.tooltip",
                                new LiteralText(SessionUtils.getSession().getUsername()).formatted(Formatting.YELLOW)
                        ), x, y),
                        new TranslatableText("gui.authme.button.auth")
                )
        );

        ci.cancel();
    }

    @Redirect(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 1))
    private Element addDrawableChild3(TitleScreen instance, Element element) {
        if (element instanceof ButtonWidget buttonWidget) {
            buttonWidget.x += 24;
            buttonWidget.y += 24;
            buttonWidget.setWidth(buttonWidget.getWidth() - 24);
            buttonWidget.setMessage(new LiteralText("Other Servers"));
            return this.addDrawableChild(buttonWidget);
        }

        throw new AssertionError("addDrawableChild3 mixin error");
    }

    @SuppressWarnings("ConstantConditions")
    @Redirect(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 0))
    private Element addDrawableChild4(TitleScreen instance, Element element) {
        if (element instanceof ButtonWidget buttonWidget) {
            ButtonWidget newButton = new ButtonWidget(
                    buttonWidget.x,
                    buttonWidget.y + 24,
                    buttonWidget.getWidth(),
                    buttonWidget.getHeight(),
                    new LiteralText("Play"),
                    button -> ConnectScreen.connect(this,
                            this.client,
                            ServerAddress.parse(PBMC_SERVER_INFO.address),
                            PBMC_SERVER_INFO));
            return this.addDrawableChild(newButton);
        }

        throw new AssertionError("addDrawableChild4 mixin error");
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void clinit(CallbackInfo ci) {
        COPYRIGHT = new LiteralText("Happy Birthday Discord!");
    }
}
