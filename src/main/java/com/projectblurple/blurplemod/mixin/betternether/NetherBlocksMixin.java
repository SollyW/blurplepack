package com.projectblurple.blurplemod.mixin.betternether;

import net.fabricmc.yarn.constants.MiningLevels;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import paulevs.betternether.registry.NetherBlocks;

@Mixin(NetherBlocks.class)
public abstract class NetherBlocksMixin {
    @ModifyArg(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lpaulevs/betternether/blocks/BlockOre;<init>(Lnet/minecraft/item/Item;IIIIZ)V",
                    ordinal = 1),
            index = 4)
    private static int miningLevel(int level) {
        return MiningLevels.IRON;
    }

    @ModifyArg(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lpaulevs/betternether/blocks/BlockOre;<init>(Lnet/minecraft/item/Item;IIIIZ)V",
                    ordinal = 1),
            index = 1)
    private static int minCount(int count) {
        return 12;
    }

    @ModifyArg(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lpaulevs/betternether/blocks/BlockOre;<init>(Lnet/minecraft/item/Item;IIIIZ)V",
                    ordinal = 1),
            index = 2)
    private static int maxCount(int count) {
        return 36;
    }
}
