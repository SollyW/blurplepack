package com.projectblurple.blurplemod.mixin.waystones;

import com.sollyw.biginv.BigInvModInfo;
import com.sollyw.biginv.ScreenHandlerExt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.SoftOverride;
import wraith.waystones.screen.UniversalWaystoneScreenHandler;

@Mixin(UniversalWaystoneScreenHandler.class)
public abstract class UniversalWaystoneScreenHandlerMixin implements ScreenHandlerExt {
    @SoftOverride
    public BigInvModInfo biginv$getModInfo() {
        System.out.println("e");
        return BigInvModInfo.EMULATE;
    }
}
