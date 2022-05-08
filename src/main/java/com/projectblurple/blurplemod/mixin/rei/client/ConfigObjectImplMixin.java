package com.projectblurple.blurplemod.mixin.rei.client;

import com.projectblurple.blurplemod.ReiFilter;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import me.shedaniel.rei.api.client.config.entry.EntryStackProvider;
import me.shedaniel.rei.api.client.gui.config.DisplayScreenType;
import me.shedaniel.rei.api.client.gui.config.EntryPanelOrdering;
import me.shedaniel.rei.api.client.gui.config.SearchMode;
import me.shedaniel.rei.impl.client.config.ConfigObjectImpl;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
@Mixin(ConfigObjectImpl.class)
public abstract class ConfigObjectImplMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public ModifierKeyCode getHideKeybind() {
        return ModifierKeyCode.unknown();
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isCheating() {
        return MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().player.isCreative();
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public DisplayScreenType getRecipeScreenType() {
        return DisplayScreenType.COMPOSITE;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isUsingDarkTheme() {
        return true;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isCraftableFilterEnabled() {
        return true;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @ApiStatus.Experimental
    @Overwrite(remap = false)
    public double getHorizontalEntriesBoundariesPercentage() {
        return 0.95;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean doesDisableRecipeBook() {
        return true;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean shouldAppendModNames() {
        return false;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public EntryPanelOrdering getItemListOrdering() {
        return EntryPanelOrdering.NAME;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isItemListAscending() {
        return true;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isToastDisplayedOnCopyIdentifier() {
        return false;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public SearchMode getTagSearchMode() {
        return SearchMode.NEVER;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public SearchMode getIdentifierSearchMode() {
        return SearchMode.NEVER;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public SearchMode getModSearchMode() {
        return SearchMode.NEVER;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public List<EntryStackProvider<?>> getFilteredStackProviders() {
        return ReiFilter.FILTERED_STACKS;
    }

}
