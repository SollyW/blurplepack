package com.projectblurple.blurplemod.mixin.bedrockify;

import me.juancarloscp52.bedrockify.Bedrockify;
import me.juancarloscp52.bedrockify.BedrockifySettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bedrockify.class)
public abstract class BedrockifyMixin {
    @Shadow(remap = false)
    public BedrockifySettings settings;

    @Inject(method = "loadSettings", at = @At("TAIL"), remap = false)
    private void loadSettings(CallbackInfo ci) {
        this.settings.loadingScreen = true;
        this.settings.bedrockIfyButton = false;
        this.settings.showPositionHUD = false;
        this.settings.FPSHUD = 0;
        this.settings.heldItemTooltip = 1;
        this.settings.showPaperDoll = false;
        this.settings.showChunkMap = false;
        this.settings.reacharound = true;
        this.settings.reacharoundSneaking = true;
        this.settings.reacharoundIndicator = false;
        this.settings.reacharoundMultiplayer = true;
        this.settings.positionHUDHeight = 50;
        this.settings.screenSafeArea = 0;
        this.settings.overlayIgnoresSafeArea = false;
        this.settings.cubeMapBackground = true;
        this.settings.bedrockChat = false;
        this.settings.slotHighlight = false;
        this.settings.highLightColor1 = -1;
        this.settings.highLightColor2 = -9590208;
        this.settings.idleAnimation = 1f;
        this.settings.reacharoundBlockDistance = 0.5;
        this.settings.reacharoundPitchAngle = 25;
        this.settings.bedrockRecipes = false;
        this.settings.savingOverlay = false;
        this.settings.eatingAnimations = true;
        this.settings.pickupAnimations = false;
        this.settings.expTextStyle = false;
        this.settings.quickArmorSwap = true;
        this.settings.transparentHotBar = true;
        this.settings.biggerIcons = false;
        this.settings.dyingTrees = true;
        this.settings.sneakingShield = true;
        this.settings.bedrockShading = true;
        this.settings.fireAspectLight = false;
        this.settings.disableFlyingMomentum = false;
    }
}
