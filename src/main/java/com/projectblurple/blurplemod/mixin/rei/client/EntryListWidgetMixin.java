package com.projectblurple.blurplemod.mixin.rei.client;

import com.projectblurple.blurplemod.WordReverser;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.impl.client.gui.widget.EntryListWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Comparator;

@SuppressWarnings("UnstableApiUsage")
@Mixin(EntryListWidget.class)
public abstract class EntryListWidgetMixin {
    @Shadow(remap = false)
    @Final
    @Mutable
    static Comparator<? super EntryStack<?>> ENTRY_NAME_COMPARER;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void clinit(CallbackInfo ci) {
        ENTRY_NAME_COMPARER = WordReverser.REI_COMPARATOR;
    }
}
