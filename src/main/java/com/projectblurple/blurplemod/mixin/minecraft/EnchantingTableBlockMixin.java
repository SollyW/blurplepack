package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import potionstudios.byg.common.block.BookshelfBlock;
import ru.bclib.blocks.BaseBookshelfBlock;

@Mixin(EnchantingTableBlock.class)
public abstract class EnchantingTableBlockMixin {
    @Redirect(method = "canAccessBookshelf",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private static boolean isOf(BlockState instance, Block block) {
        return instance.isOf(block)
                || instance.getBlock() instanceof BookshelfBlock
                || instance.getBlock() instanceof BaseBookshelfBlock;
    }
}
