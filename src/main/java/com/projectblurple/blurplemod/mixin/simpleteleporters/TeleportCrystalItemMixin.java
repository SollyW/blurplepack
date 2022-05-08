package com.projectblurple.blurplemod.mixin.simpleteleporters;

import com.shnupbups.simpleteleporters.item.TeleportCrystalItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

@Mixin(TeleportCrystalItem.class)
public abstract class TeleportCrystalItemMixin {
    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite
    public static boolean hasPosition(NbtCompound nbt) {
        return nbt != null && nbt.contains("LodestonePos");
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite
    public static BlockPos getPosition(NbtCompound nbt) {
        return !hasPosition(nbt) ? null : NbtHelper.toBlockPos(nbt.getCompound("LodestonePos")).up();
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite
    public static String getDimensionName(NbtCompound nbt) {
        return nbt != null && nbt.contains("LodestoneDimension") ? nbt.getString("LodestoneDimension") : World.OVERWORLD.getValue().toString();
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        return ActionResult.PASS;
    }

    /**
     * @author Sally
     * @reason aaa
     */
    @Overwrite
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext options) {
    }
}
