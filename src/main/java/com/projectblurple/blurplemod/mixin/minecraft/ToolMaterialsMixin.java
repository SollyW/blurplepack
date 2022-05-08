package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ToolMaterials.class)
public abstract class ToolMaterialsMixin {
    @Shadow
    @Final
    @Mutable
    private float attackDamage;

    @Shadow
    @Final
    @Mutable
    private int itemDurability;

    @Shadow
    @Final
    @Mutable
    private float miningSpeed;

    @Inject(method = "<init>",
            at = @At("TAIL"))
    private void init(String id, int ordinal, int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient, CallbackInfo ci) {
        switch (id) {
            case "STONE" -> this.attackDamage = 0.5f;
            case "IRON" -> this.attackDamage = 1f;
            case "DIAMOND" -> {
                this.attackDamage = 1.5f;
                this.itemDurability = 1120;
            }
            case "GOLD" -> {
                this.attackDamage = 2.5f;
                this.miningSpeed = 12f;
            }
            case "NETHERITE" -> this.attackDamage = 2f;
        }
    }
}
