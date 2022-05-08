package com.projectblurple.blurplemod.mixin.minecraft;

import com.projectblurple.blurplemod.content.BlurpleResourcePackProvider;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.server.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Arrays;

@Mixin(Main.class)
public abstract class MainMixin {
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyArg(method = "main",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/resource/ResourcePackManager;<init>(Lnet/minecraft/resource/ResourceType;[Lnet/minecraft/resource/ResourcePackProvider;)V"),
            index = 1)
    private static ResourcePackProvider[] resourcePackManagers(ResourcePackProvider[] providers) {
        ResourcePackProvider[] copy = Arrays.copyOf(providers, providers.length + 1);
        copy[providers.length] = new BlurpleResourcePackProvider();
        return copy;
    }
}
