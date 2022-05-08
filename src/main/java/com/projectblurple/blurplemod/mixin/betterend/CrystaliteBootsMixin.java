package com.projectblurple.blurplemod.mixin.betterend;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import ru.betterend.item.CrystaliteBoots;
import ru.betterend.item.CrystaliteChestplate;

@Mixin(CrystaliteBoots.class)
public abstract class CrystaliteBootsMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public void applyEffect(LivingEntity owner) {
    }
}
