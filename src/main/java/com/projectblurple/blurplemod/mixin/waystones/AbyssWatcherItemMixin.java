package com.projectblurple.blurplemod.mixin.waystones;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import wraith.waystones.item.AbyssWatcherItem;

@Mixin(AbyssWatcherItem.class)
public abstract class AbyssWatcherItemMixin extends Item {
    private AbyssWatcherItemMixin(Settings settings) {
        super(settings);
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
}
