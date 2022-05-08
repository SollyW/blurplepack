package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EnderDragonEntity.class)
public abstract class EnderDragonEntityMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite
    private boolean destroyBlocks(Box box) {
        return false;
    }

    @ModifyConstant(method = "createEnderDragonAttributes",
            constant = @Constant(doubleValue = 200.0))
    private static double modifyHealth(double constant) {
        return 2400;
    }
}
