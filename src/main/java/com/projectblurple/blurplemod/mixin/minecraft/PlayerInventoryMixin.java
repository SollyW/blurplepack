package com.projectblurple.blurplemod.mixin.minecraft;

import com.projectblurple.blurplemod.content.tag.BlurpleItemTags;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
    @Redirect(method = "dropAll", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"))
    public boolean isEmpty(ItemStack instance) {
        return instance.isEmpty() || instance.isIn(BlurpleItemTags.KEEP_ON_DEATH);
    }
}
