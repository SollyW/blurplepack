package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import net.minecraft.item.ExperienceBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ExperienceBottleItem.class)
public abstract class ExperienceBottleItemMixin extends Item {
    private ExperienceBottleItemMixin(Settings settings) {
        super(settings);
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite
    public boolean hasGlint(ItemStack stack) {
        return super.hasGlint(stack);
    }

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/thrown/ExperienceBottleEntity;setVelocity(Lnet/minecraft/entity/Entity;FFFFF)V"))
    public void setVelocity(ExperienceBottleEntity instance, Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        instance.setVelocity(shooter, pitch, yaw, roll, speed * 2, divergence * 1.35f);
    }
}
