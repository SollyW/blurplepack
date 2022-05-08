package com.projectblurple.blurplemod.mixin.bclib;

import org.spongepowered.asm.mixin.Mixin;
import ru.bclib.complexmaterials.ComplexMaterial;
import ru.bclib.complexmaterials.WoodenComplexMaterial;
import ru.bclib.complexmaterials.entry.RecipeEntry;

@Mixin(WoodenComplexMaterial.class)
public abstract class WoodenComplexMaterialMixin extends ComplexMaterial {
    private WoodenComplexMaterialMixin(String modID, String baseName, String receipGroupPrefix) {
        super(modID, baseName, receipGroupPrefix);
    }

    @Override
    protected void addRecipeEntry(RecipeEntry entry) {
        switch (entry.getSuffix()) {
            case "crafting_table", "chest", "composter", "shulker", "ladder" -> {
                return;
            }
        }

        super.addRecipeEntry(entry);
    }
}
