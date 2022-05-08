package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.enchantment.UnbreakingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(UnbreakingEnchantment.class)
public abstract class UnbreakingEnchantmentMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite
    public int getMaxLevel() {
        return 10;
    }
}
