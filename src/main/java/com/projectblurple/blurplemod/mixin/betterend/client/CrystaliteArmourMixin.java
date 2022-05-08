package com.projectblurple.blurplemod.mixin.betterend.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.betterend.item.CrystaliteBoots;
import ru.betterend.item.CrystaliteChestplate;

@Mixin({CrystaliteChestplate.class,
        CrystaliteBoots.class})
public abstract class CrystaliteArmourMixin {
    @Inject(method = "appendTooltip",
            at = @At(value = "INVOKE",
                    target = "Lru/betterend/item/CrystaliteArmor;appendTooltip(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Ljava/util/List;Lnet/minecraft/client/item/TooltipContext;)V",
                    shift = At.Shift.AFTER),
            cancellable = true)
    private void appendTooltip(CallbackInfo ci) {
        ci.cancel();
    }
}
