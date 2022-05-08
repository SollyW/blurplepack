package com.projectblurple.blurplemod.mixin.betternether;

import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paulevs.betternether.items.materials.BNArmorMaterial;

@Mixin(BNArmorMaterial.class)
public abstract class BNArmorMaterialMixin {
    @Shadow(remap = false)
    @Final
    private int[] protection;

    @Shadow(remap = false)
    @Final
    @Mutable
    private float knockbackResistance;

    @Inject(method = "<init>",
            at = @At("TAIL"),
            remap = false)
    private void init(String id, int ordinal, String name, int durabilityMultiplier, int[] protection, int enchantLevel, SoundEvent equipSound, float toughness, float knockback, ItemConvertible repairItem, CallbackInfo ci) {
        switch (name) {
            case "cincinnasite" -> {
                this.protection[0] = 3;
                this.protection[1] = 4;
                this.protection[2] = 6;
                this.protection[3] = 3;
            }

            case "nether_ruby" -> {
                this.protection[0] = 3;
                this.protection[1] = 5;
                this.protection[2] = 7;
                this.protection[3] = 3;
                this.knockbackResistance = 0.075f;
            }
        }
    }
}
