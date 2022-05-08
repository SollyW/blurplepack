package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ArmorMaterials.class)
public abstract class ArmorMaterialsMixin {
    @Shadow
    @Final
    private int[] protectionAmounts;

    @Shadow
    @Final
    @Mutable
    private float toughness;

    @Inject(method = "<init>",
            at = @At("TAIL"))
    private void init(String id, int ordinal, String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier, CallbackInfo ci) {
        switch (name) {
            case "leather" -> {
                this.protectionAmounts[0] = 1;
                this.protectionAmounts[1] = 1;
                this.protectionAmounts[2] = 1;
                this.protectionAmounts[3] = 1;
            }

            case "chainmail" -> {
                this.protectionAmounts[0] = 1;
                this.protectionAmounts[1] = 2;
                this.protectionAmounts[2] = 3;
                this.protectionAmounts[3] = 1;
            }

            case "iron", "turtle" -> {
                this.protectionAmounts[0] = 1;
                this.protectionAmounts[1] = 3;
                this.protectionAmounts[2] = 4;
                this.protectionAmounts[3] = 2;
            }

            case "gold" -> {
                this.protectionAmounts[0] = 1;
                this.protectionAmounts[1] = 2;
                this.protectionAmounts[2] = 2;
                this.protectionAmounts[3] = 1;
            }

            case "diamond" -> {
                this.protectionAmounts[0] = 2;
                this.protectionAmounts[1] = 4;
                this.protectionAmounts[2] = 6;
                this.protectionAmounts[3] = 2;
            }

            case "netherite" -> {
                this.protectionAmounts[0] = 3;
                this.protectionAmounts[1] = 5;
                this.protectionAmounts[2] = 7;
                this.protectionAmounts[3] = 3;
                this.toughness = 2f;
            }
        }
    }
}
