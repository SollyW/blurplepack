package com.projectblurple.blurplemod.mixin.rei.client;

import com.projectblurple.blurplemod.BlurpleMod;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.impl.client.gui.ScreenOverlayImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
@Mixin(ScreenOverlayImpl.class)
public abstract class ScreenOverlayImplMixin {
    @Inject(method = "getConfigButtonArea",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    private void getConfigButtonArea(CallbackInfoReturnable<Rectangle> cir) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null || !player.getUuid().equals(BlurpleMod.SOLLY)) {
            cir.setReturnValue(new Rectangle(10000, 10000, 0, 0));
        }
    }

    @Redirect(method = "init()V",
            slice = @Slice(
                    from = @At(value = "CONSTANT",
                            args = "stringValue=text.rei.changelog.title"),
                    to = @At(value = "CONSTANT",
                            args = "stringValue=text.rei.next_page")),
            at = @At(value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z"),
            remap = false)
    private <E> boolean add(List<E> instance, E e) {
        return true;
    }
}
