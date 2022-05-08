package com.projectblurple.blurplemod.mixin.bclib;

import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.bclib.recipes.GridRecipe;

@Mixin(GridRecipe.class)
public abstract class GridRecipeMixin {
    @Shadow(remap = false)
    private Identifier id;

    @Inject(method = "build",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    private void build(CallbackInfo ci) {
        if ("betterend".equals(this.id.getNamespace())) {
            switch (this.id.getPath()) {
                case "respawn_obelisk", "ender_eye_amber", "iron_chandelier", "gold_chandelier", "end_stone_furnace",
                        "sugar_from_root", "endstone_flower_pot", "iron_hammer", "golden_hammer", "diamond_hammer",
                        "ender_perl_to_block", "ender_block_to_perl" -> ci.cancel();
            }
        }
    }
}
