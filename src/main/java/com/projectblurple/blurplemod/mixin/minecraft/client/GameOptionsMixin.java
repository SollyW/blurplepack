package com.projectblurple.blurplemod.mixin.minecraft.client;

import com.google.common.collect.Lists;
import com.projectblurple.blurplemod.ClientConfig;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ChatVisibility;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.NarratorMode;
import net.minecraft.client.tutorial.TutorialStep;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.Difficulty;
import org.lwjgl.glfw.GLFW;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.Arrays;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
    @Shadow
    @Final
    @Mutable
    public KeyBinding socialInteractionsKey;

    @Shadow
    @Final
    @Mutable
    public KeyBinding advancementsKey;

    @Shadow
    @Final
    @Mutable
    public KeyBinding saveToolbarActivatorKey;

    @Shadow
    @Final
    @Mutable
    public KeyBinding loadToolbarActivatorKey;

    @Shadow
    @Final
    @Mutable
    public KeyBinding[] hotbarKeys;

    @Shadow
    @Final
    private Object2FloatMap<SoundCategory> soundVolumeLevels;

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/GameOptions;load()V"))
    private void init(MinecraftClient client, File optionsFile, CallbackInfo ci) {
        GameOptions options = ((GameOptions) (Object) this);

        options.difficulty = Difficulty.HARD;
        options.resourcePacks = Lists.newArrayList("vanilla", "Fabric Mods", "file/blurple");
        options.maxFps = 60;
        options.fov = 90;
        this.soundVolumeLevels.put(SoundCategory.MUSIC, 0.5f);
        this.soundVolumeLevels.put(SoundCategory.WEATHER, 0.2f);
        options.autoJump = false;

        this.blurple_tweak();
    }

    @SuppressWarnings({"ReferenceToMixin"})
    @Inject(method = "accept", at = @At("TAIL"))
    private void accept(@Coerce GameOptions_VisitorAccessor visitor, CallbackInfo ci) {
        ClientConfig.reacharound = visitor.callVisitBoolean("blurple.reacharound", ClientConfig.reacharound);

        this.blurple_tweak();
    }

    @SuppressWarnings("ConstantConditions")
    private void blurple_tweak() {
        GameOptions options = ((GameOptions) (Object) this);

        options.autoSuggestions = true;
        options.chatColors = true;
        options.chatLinks = true;
        options.chatLinksPrompt = true;
        options.forceUnicodeFont = false;
        options.realmsNotifications = false;
        options.showSubtitles = false;
        options.touchscreen = false;
        options.monochromeLogo = true;
        options.hideLightningFlashes = false;
        options.language = "en_gb";
        options.chatVisibility = ChatVisibility.FULL;
        options.chatOpacity = 1.0;
        options.chatLineSpacing = 0;
        options.textBackgroundOpacity = 0.5;
        options.backgroundForChatOnly = true;
        options.advancedItemTooltips = false;
        options.heldItemTooltips = true;
        options.chatHeightFocused = 1.0;
        options.chatDelay = 0;
        options.chatHeightUnfocused = 0.44366195797920227;
        options.chatScale = 1.0;
        options.chatWidth = 1.0;
        options.narrator = NarratorMode.OFF;
        options.tutorialStep = TutorialStep.NONE;
        options.skipMultiplayerWarning = true;
        options.joinedFirstServer = true;
        options.hideBundleTutorial = true;

        this.socialInteractionsKey.setBoundKey(InputUtil.UNKNOWN_KEY);
        this.saveToolbarActivatorKey.setBoundKey(InputUtil.UNKNOWN_KEY);
        this.loadToolbarActivatorKey.setBoundKey(InputUtil.UNKNOWN_KEY);
    }

    @Inject(method = "<init>", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, shift = At.Shift.AFTER, target = "Lnet/minecraft/client/option/GameOptions;loadToolbarActivatorKey:Lnet/minecraft/client/option/KeyBinding;"))
    private void init(CallbackInfo ci) {
        this.socialInteractionsKey = new KeyBinding("key.socialInteractions", InputUtil.UNKNOWN_KEY.getCode(), KeyBinding.MULTIPLAYER_CATEGORY);
        this.advancementsKey = new KeyBinding("key.advancements", InputUtil.UNKNOWN_KEY.getCode(), KeyBinding.MISC_CATEGORY);
        this.saveToolbarActivatorKey = new KeyBinding("key.saveToolbarActivator", InputUtil.UNKNOWN_KEY.getCode(), KeyBinding.CREATIVE_CATEGORY);
        this.loadToolbarActivatorKey = new KeyBinding("key.loadToolbarActivator", InputUtil.UNKNOWN_KEY.getCode(), KeyBinding.CREATIVE_CATEGORY);

        this.hotbarKeys = Arrays.copyOf(this.hotbarKeys, 18);
        this.hotbarKeys[9] = new KeyBinding("key.hotbar.10", GLFW.GLFW_KEY_0, KeyBinding.INVENTORY_CATEGORY);
        this.hotbarKeys[10] = new KeyBinding("key.hotbar.11", GLFW.GLFW_KEY_MINUS, KeyBinding.INVENTORY_CATEGORY);
        this.hotbarKeys[11] = new KeyBinding("key.hotbar.12", GLFW.GLFW_KEY_EQUAL, KeyBinding.INVENTORY_CATEGORY);
        this.hotbarKeys[12] = new KeyBinding("key.hotbar.13", InputUtil.UNKNOWN_KEY.getCode(), KeyBinding.INVENTORY_CATEGORY);
        this.hotbarKeys[13] = new KeyBinding("key.hotbar.14", InputUtil.UNKNOWN_KEY.getCode(), KeyBinding.INVENTORY_CATEGORY);
        this.hotbarKeys[14] = new KeyBinding("key.hotbar.15", InputUtil.UNKNOWN_KEY.getCode(), KeyBinding.INVENTORY_CATEGORY);
        this.hotbarKeys[15] = new KeyBinding("key.hotbar.16", InputUtil.UNKNOWN_KEY.getCode(), KeyBinding.INVENTORY_CATEGORY);
        this.hotbarKeys[16] = new KeyBinding("key.hotbar.17", InputUtil.UNKNOWN_KEY.getCode(), KeyBinding.INVENTORY_CATEGORY);
        this.hotbarKeys[17] = new KeyBinding("key.hotbar.18", InputUtil.UNKNOWN_KEY.getCode(), KeyBinding.INVENTORY_CATEGORY);
    }
}
