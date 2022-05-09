package com.projectblurple.blurplemod.mixin.vanish;

import eu.vanish.commands.VanishCommand;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VanishCommand.class)
public abstract class VanishCommandMixin {
    @SuppressWarnings("DefaultAnnotationParam")
    @Redirect(method = "lambda$register$0",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/server/command/ServerCommandSource;hasPermissionLevel(I)Z",
                    remap = true),
            remap = false)
    private static boolean hasPermissionLevel(ServerCommandSource instance, int level) {
        return Permissions.check(instance, "vanish.command.vanish")
                || instance.hasPermissionLevel(level);
    }
}
