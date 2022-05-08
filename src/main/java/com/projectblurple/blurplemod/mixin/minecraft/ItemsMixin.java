package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.item.ExperienceBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Items.class)
public abstract class ItemsMixin {
    @SuppressWarnings("All")
    @Redirect(method = "<clinit>",
            at = @At(value = "NEW",
                    target = "(Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/ExperienceBottleItem;"))
    private static ExperienceBottleItem init(Item.Settings settings) {
        return new ExperienceBottleItem(settings.rarity(Rarity.COMMON));
    }
}
