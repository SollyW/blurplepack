package com.projectblurple.blurplemod.mixin.charm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import svenhjol.charm.loader.CharmModule;

@Mixin(CharmModule.class)
public abstract class CharmModuleMixin {
    @Shadow(remap = false)
    public abstract String getName();

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isEnabledInConfig() {
        return switch (this.getName()) {
            case "AutoRestock", "BumblezoneIntegration", "Chairs", "ExtraBoats", "InventoryTidying",
                    "NoCuredVillagerDiscount", "NoNitwits", "RepairElytraFromLeather", "StrongerAnvils",
                    "TotemOfPreserving", "VariantBarrels", "VariantChests", "VariantLadders", "VariantMobTextures",
                    "HoverSorting", "ShulkerBoxTooltip", "ExtraRecipes", "Woodcutters", "QuickReplant",
                    "PortableCrafting", "Atlases" -> false;
            default -> true;
        };
    }
}
