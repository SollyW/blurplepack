package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.world.gen.feature.OrePlacedFeatures;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(OrePlacedFeatures.class)
public abstract class OrePlacedFeaturesMixin {
    @ModifyArg(method = "<clinit>",
            slice = @Slice(
                    from = @At(value = "FIELD",
                            target = "Lnet/minecraft/world/gen/feature/OrePlacedFeatures;ORE_COAL_LOWER:Lnet/minecraft/util/registry/RegistryEntry;",
                            opcode = Opcodes.PUTSTATIC),
                    to = @At(value = "FIELD",
                            target = "Lnet/minecraft/world/gen/feature/OrePlacedFeatures;ORE_LAPIS_BURIED:Lnet/minecraft/util/registry/RegistryEntry;",
                            opcode = Opcodes.PUTSTATIC)),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/gen/feature/OrePlacedFeatures;modifiersWithCount(ILnet/minecraft/world/gen/placementmodifier/PlacementModifier;)Ljava/util/List;"),
            index = 0)
    private static int count(int count) {
        return count * 3 / 5;
    }

    @ModifyArg(method = "<clinit>",
            slice = @Slice(
                    from = @At(value = "FIELD",
                            target = "Lnet/minecraft/world/gen/feature/OrePlacedFeatures;ORE_DEBRIS_SMALL:Lnet/minecraft/util/registry/RegistryEntry;",
                            opcode = Opcodes.PUTSTATIC),
                    to = @At(value = "FIELD",
                            target = "Lnet/minecraft/world/gen/feature/OrePlacedFeatures;ORE_COPPER_LARGE:Lnet/minecraft/util/registry/RegistryEntry;",
                            opcode = Opcodes.PUTSTATIC)),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/gen/feature/OrePlacedFeatures;modifiersWithCount(ILnet/minecraft/world/gen/placementmodifier/PlacementModifier;)Ljava/util/List;"),
            index = 0)
    private static int count0(int count) {
        return count * 3 / 5;
    }
}
