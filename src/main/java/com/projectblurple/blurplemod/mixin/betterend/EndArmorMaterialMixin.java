package com.projectblurple.blurplemod.mixin.betterend;

import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.betterend.item.material.EndArmorMaterial;

import java.util.function.Supplier;

@Mixin(EndArmorMaterial.class)
public abstract class EndArmorMaterialMixin {
    @Shadow(remap = false)
    @Final
    private int[] protectionAmounts;

    @Shadow(remap = false)
    @Final
    @Mutable
    private float toughness;

    @Shadow(remap = false)
    @Final
    @Mutable
    private float knockbackResistance;

    @Inject(method = "<init>",
            at = @At("TAIL"),
            remap = false)
    private void init(String id, int ordinal, String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, CallbackInfo ci) {
        switch (name) {
            case "thallasium" -> {
                this.protectionAmounts[0] = 1;
                this.protectionAmounts[1] = 3;
                this.protectionAmounts[2] = 4;
                this.protectionAmounts[3] = 2;
                this.toughness = 8.5f;
            }

            case "terminite" -> {
                this.protectionAmounts[0] = 3;
                this.protectionAmounts[1] = 5;
                this.protectionAmounts[2] = 7;
                this.protectionAmounts[3] = 3;
                this.toughness = 5f;
                this.knockbackResistance = 0.05f;
            }

            case "aeternium" -> {
                this.protectionAmounts[0] = 3;
                this.protectionAmounts[1] = 6;
                this.protectionAmounts[2] = 8;
                this.protectionAmounts[3] = 3;
                this.toughness = 4f;
                this.knockbackResistance = 0.075f;
            }

            case "crystalite" -> {
                this.protectionAmounts[0] = 3;
                this.protectionAmounts[1] = 6;
                this.protectionAmounts[2] = 8;
                this.protectionAmounts[3] = 3;
                this.toughness = 2f;
                this.knockbackResistance = 0f;
            }
        }
    }
}
