package com.projectblurple.blurplemod.mixin.betterend;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import ru.betterend.item.CrystaliteArmor;

@Mixin(CrystaliteArmor.class)
public abstract class CrystaliteArmorMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public static boolean hasFullSet(LivingEntity owner) {
        return false;
    }
}
