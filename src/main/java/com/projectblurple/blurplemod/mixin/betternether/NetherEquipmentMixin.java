package com.projectblurple.blurplemod.mixin.betternether;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import paulevs.betternether.items.*;

import java.util.Map;

@Mixin({NetherArmor.class,
        NetherAxe.class,
        NetherHoe.class,
        NetherPickaxe.class,
        NetherShovel.class,
        NetherSword.class})
public abstract class NetherEquipmentMixin {
    @Redirect(method = "initializeState",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;set(Ljava/util/Map;Lnet/minecraft/item/ItemStack;)V"))
    private void set(Map<Enchantment, Integer> enchantments, ItemStack stack) {
    }
}
