package com.projectblurple.blurplemod.mixin;

import me.juancarloscp52.bedrockify.mixin.featureManager.MixinFeatureManager;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class BlurpleMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
        MixinFeatureManager.features.put("client.core.clientRenderTimer", true);
        MixinFeatureManager.features.put("client.core.bedrockIfyButton", false);
        MixinFeatureManager.features.put("client.features.chat", false);
        MixinFeatureManager.features.put("client.features.eatingAnimations", true);
        MixinFeatureManager.features.put("client.features.heldItemTooltips", false);
        MixinFeatureManager.features.put("client.features.idleHandAnimations", true);
        MixinFeatureManager.features.put("client.features.loadingScreens", true);
        MixinFeatureManager.features.put("client.features.panoramaBackground", true);
        MixinFeatureManager.features.put("client.features.pickupAnimations", false);
        MixinFeatureManager.features.put("client.features.reacharoundPlacement", true);
        MixinFeatureManager.features.put("client.features.savingOverlay", false);
        MixinFeatureManager.features.put("client.features.screenSafeArea", false);
        MixinFeatureManager.features.put("client.features.slotHighlight", false);
        MixinFeatureManager.features.put("client.features.worldColorNoise", true);
        MixinFeatureManager.features.put("client.features.biggerDraggingItem", false);
        MixinFeatureManager.features.put("common.features.recipes", false);
        MixinFeatureManager.features.put("common.features.useAnimations", true);
        MixinFeatureManager.features.put("common.features.worldGeneration", true);
        MixinFeatureManager.features.put("client.features.bedrockShading", true);
        MixinFeatureManager.features.put("common.features.fireAspect", false);
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
