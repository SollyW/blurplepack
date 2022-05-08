package com.projectblurple.blurplemod.mixin.minecraft.client;

import com.projectblurple.blurplemod.ClientConfig;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.client.gui.screen.option.MouseOptionsScreen;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.Option;
import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(MouseOptionsScreen.class)
public abstract class MouseOptionsScreenMixin {
    @Shadow
    @Final
    @Mutable
    private static Option[] OPTIONS;

    @Redirect(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/Option;TOUCHSCREEN:Lnet/minecraft/client/option/CyclingOption;", opcode = Opcodes.GETSTATIC))
    private static CyclingOption<Boolean> getFieldValue() {
        return ClientConfig.REACHAROUND;
    }

    @Inject(method = "<clinit>",
            at = @At("TAIL"))
    private static void clinit(CallbackInfo ci) {
        OPTIONS = ArrayUtils.addAll(OPTIONS, Option.SNEAK_TOGGLED, Option.SPRINT_TOGGLED, Option.AUTO_JUMP);
    }
}
