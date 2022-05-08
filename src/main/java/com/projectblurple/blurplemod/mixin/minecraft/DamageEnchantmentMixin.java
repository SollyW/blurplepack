package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DamageEnchantment.class)
public abstract class DamageEnchantmentMixin extends Enchantment {
    private DamageEnchantmentMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
