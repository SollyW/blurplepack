package com.projectblurple.blurplemod.mixin.waystones.client;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wraith.waystones.screen.UniversalWaystoneScreen;
import wraith.waystones.screen.WaystoneBlockScreen;
import wraith.waystones.screen.WaystoneBlockScreenHandler;
import wraith.waystones.util.Utils;

@Mixin(WaystoneBlockScreen.class)
public abstract class WaystoneScreenMixin extends UniversalWaystoneScreen {
    @Shadow(remap = false)
    private TextFieldWidget nameField;

    private WaystoneScreenMixin(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, null, title);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(ScreenHandler handler, PlayerInventory inventory, Text title, CallbackInfo ci) {
        this.buttons.get(9).setX(153);
        this.buttons.get(9).setY(70);
        this.buttons.remove(8);
        this.buttons.remove(7);
        this.buttons.remove(5);

        if (inventory.player.hasPermissionLevel(3)) {
            this.buttons.get(5).setX(153);
        } else {
            this.buttons.remove(5);
        }
    }

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setFocusUnlocked(Z)V"))
    private void init(CallbackInfo ci) {
        this.nameField.setMaxLength(32);
    }

    /**
     * @author Solly
     * @reason Change text
     */
    @Overwrite(remap = false)
    private void renderButtonText(MatrixStack matrices) {
        this.textRenderer.draw(matrices, new LiteralText("Please give waystones"), this.x + 10, this.y + 14, 0xbbbbbb);
        this.textRenderer.draw(matrices, new LiteralText("meaningful names."), this.x + 10, this.y + 25, 0xbbbbbb);
        this.textRenderer.draw(matrices, new LiteralText("All players can see them."), this.x + 10, this.y + 40, 0xbbbbbb);
    }

    @Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I"))
    public int draw(TextRenderer instance, MatrixStack matrices, Text text, float x, float y, int color) {
        return instance.draw(matrices, text, x, y, 0xffffff);
    }

    @Redirect(method = "drawForeground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"))
    public int draw(TextRenderer instance, MatrixStack matrices, String text, float x, float y, int color) {
        return instance.draw(matrices, text, x, y, 0xbbbbbb);
    }

    /**
     * @author Solly
     * @reason Mod has spaghetti code
     */
    @Overwrite(remap = false)
    protected void renderWaystoneBackground(MatrixStack matrixStack, int mouseX, int mouseY, int x, int y, int m) {
        for(int n = this.scrollOffset; n < m && n < getDiscoveredCount(); ++n) {
            int o = n - this.scrollOffset;
            int r = y + o * 15 + 2;
            int s = this.backgroundHeight;
            if (((WaystoneBlockScreenHandler)handler).getWaystone().equals(getDiscoveredWaystones().get(n))) {
                s += 15;
            } else if (mouseX >= x - 11 && mouseY >= r && mouseX < x + 101 && mouseY < r + 15) {
                if (mouseClicked) {
                    s += 15;
                } else {
                    s += 30;
                }
            }
            this.drawTexture(matrixStack, x - 11, r - 1, 0, s, 112, 15);
        }
    }

    /**
     * @author Solly
     * @reason Too many changes, can't be bothered
     */
    @SuppressWarnings("ConstantConditions")
    @Overwrite(remap = false)
    protected boolean tryClick(double mouseX, double mouseY) {
        int i1 = this.x + 13;
        int j1 = this.y + 45;
        int i2 = this.x + 24;
        int j2 = this.y + 39;
        int k = this.scrollOffset + 8;

        int n = getDiscoveredCount();
        for(int l = this.scrollOffset; l < k && l < n; ++l) {
            int m = l - this.scrollOffset;
            double x1 = mouseX - (double)(i1);
            double y1 = mouseY - (double)(j1 + m * 15);

            double x2 = mouseX - (double)(i2);
            double y2 = mouseY - (double)(j2 + m * 15);
            if (m < n && x1 >= 0.0D && y1 >= 0.0D && x1 <= 8 && y1 <= 8 && Screen.hasShiftDown() && (this.handler).onButtonClick(this.client.player, l * 2 + 1)) {
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.BLOCK_ANVIL_BREAK, 1.0F));
                this.scrollOffset = Math.max(0, this.scrollOffset - 1);

                NbtCompound tag = new NbtCompound();
                tag.putInt("sync_id", handler.syncId);
                tag.putInt("clicked_slot", l * 2 + 1);
                PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer()).writeNbt(tag);

                ClientPlayNetworking.send(Utils.ID("waystone_gui_slot_click"), packet);

                return true;
            }
            if (((WaystoneBlockScreenHandler)handler).getWaystone().equals(getDiscoveredWaystones().get(l))) {
                continue;
            }
            if (x2 >= 0.0D && y2 >= 0.0D && x2 < 112.0D && y2 < 15.0D && (this.handler).onButtonClick(this.client.player, l * 2)) {
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));

                NbtCompound tag = new NbtCompound();
                tag.putInt("sync_id", handler.syncId);
                tag.putInt("clicked_slot", l * 2);
                PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer()).writeNbt(tag);

                ClientPlayNetworking.send(Utils.ID("waystone_gui_slot_click"), packet);
                return true;
            }
        }

        int i3 = this.x + 141;
        int j3 = this.y + 40;
        if (mouseX >= (double)i3 && mouseX < (double)(i3 + 11) && mouseY >= (double)j3 && mouseY < (double)(j3 + 120)) {
            this.mouseClicked = true;
        }
        return false;
    }
}
