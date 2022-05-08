package com.projectblurple.blurplemod.mixin.bclib.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import ru.bclib.interfaces.SurvivesOnSpecialGround;

import java.util.List;

@Mixin(SurvivesOnSpecialGround.class)
public interface SurvivesOnSpecialGroundMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Environment(EnvType.CLIENT)
    @Overwrite(remap = false)
    static void appendHoverText(List<Text> list, String description) {
    }
}
