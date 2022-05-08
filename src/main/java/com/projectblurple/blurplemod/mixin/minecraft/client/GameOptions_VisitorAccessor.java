package com.projectblurple.blurplemod.mixin.minecraft.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "net.minecraft.client.option.GameOptions$Visitor")
public interface GameOptions_VisitorAccessor {
    @Invoker
    boolean callVisitBoolean(String key, boolean current);
}
