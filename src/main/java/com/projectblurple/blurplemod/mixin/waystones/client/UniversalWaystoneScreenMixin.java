package com.projectblurple.blurplemod.mixin.waystones.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wraith.waystones.Waystones;
import wraith.waystones.screen.UniversalWaystoneScreen;
import wraith.waystones.screen.UniversalWaystoneScreenHandler;

import java.util.ArrayList;

@Mixin(UniversalWaystoneScreen.class)
public abstract class UniversalWaystoneScreenMixin extends HandledScreen<ScreenHandler> {
    private UniversalWaystoneScreenMixin(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Shadow(remap = false)
    protected abstract ArrayList<String> getDiscoveredWaystones();

    @Shadow(remap = false)
    protected int scrollOffset;

    @Shadow(remap = false)
    protected abstract int getDiscoveredCount();

    @Shadow(remap = false)
    protected boolean mouseClicked;

    @Shadow(remap = false)
    protected float scrollAmount;

    @Shadow(remap = false)
    private TextFieldWidget searchField;

    @Shadow @Final private Identifier texture;

    @Shadow
    protected abstract void renderWaystoneBackground(MatrixStack matrixStack, int mouseX, int mouseY, int x, int y, int m);

    @Shadow
    protected abstract void renderButtons(MatrixStack matrices, int mouseX, int mouseY);

    @Shadow
    protected abstract void renderButtonTooltips(MatrixStack matrices, int mouseX, int mouseY);

    @Inject(method = "init", at = @At("TAIL"))
    protected void init(CallbackInfo ci) {
        this.searchField.setMaxLength(32);
        this.searchField.setX(this.x + 26);
        this.searchField.setWidth(104);
        ((UniversalWaystoneScreenHandler)handler).toggleSearchType();
        ((UniversalWaystoneScreenHandler)handler).toggleSearchType();
    }

    /**
     * @author Solly
     * @reason Scaling
     */
    @Overwrite(remap = false)
    protected void renderWaystoneNames(MatrixStack matrices, int x, int y, int m) {
        ArrayList<String> waystones = getDiscoveredWaystones();
        matrices.push();
        matrices.scale(0.666667f, 0.666667f, 1f);

        for(int n = this.scrollOffset; n < m && n < waystones.size(); ++n) {
            int o = n - this.scrollOffset;
            int r = 3 * y / 2 + 45 * o / 2 + 3;

            String name = Waystones.WAYSTONE_STORAGE.getName(waystones.get(n));
            this.textRenderer.draw(matrices, name, 1.5f * x - 12f, r + 4f, 0x161616);
        }

        matrices.pop();
    }

    /**
     * @author Solly
     * @reason Adjust position
     */
    @Overwrite(remap = false)
    protected void renderForgetButtons(MatrixStack matrixStack, int mouseX, int mouseY, int x, int y) {
        if (!Screen.hasShiftDown()) return;

        int n = this.getDiscoveredCount();
        x -= 11;

        for(int i = 0; i < 8; ++i) {
            int r = y + i * 15;
            int v = 0;
            if (i >= n) {
                v = 8;
            } else if (mouseX >= x && mouseY >= r && mouseX < x + 8 && mouseY < r + 8) {
                v += 8 * (this.mouseClicked ? 1 : 2);
            }

            this.drawTexture(matrixStack, x, r, 199, v, 8, 8);
        }

    }

    /**
     * @author Solly
     * @reason Adjust height
     */
    @Overwrite(remap = false)
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.mouseClicked && this.shouldScroll()) {
            int i = this.y + 40;
            int j = i + 120;
            this.scrollAmount = ((float)mouseY - (float)i - 7.5F) / ((float)(j - i) - 15.0F);
            this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
            this.scrollOffset = (int)((double)(this.scrollAmount * (float)this.getMaxScroll()) + 0.5D);
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    /**
     * @author Solly
     * @reason Adjust height
     */
    @Overwrite(remap = false)
    protected boolean shouldScroll() {
        return this.getDiscoveredCount() > 8;
    }

    /**
     * @author Solly
     * @reason Adjust height
     */
    @Overwrite(remap = false)
    protected int getMaxScroll() {
        return this.getDiscoveredCount() - 8;
    }

    /**
     * @author Solly
     * @reason Adjust
     */
    @Overwrite(remap = false)
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        this.renderBackground(matrices);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.texture);
        this.drawTexture(matrices, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);
        int k = (int)(105f * this.scrollAmount);
        this.drawTexture(matrices, this.x + 141, this.y + 40 + k, 177 + (this.shouldScroll() ? 0 : 11), 0, 11, 15);
        int n = this.scrollOffset + 8;
        this.renderWaystoneBackground(matrices, mouseX, mouseY, this.x + 36, this.y + 39, n);
        this.renderForgetButtons(matrices, mouseX, mouseY, this.x + 24, this.y + 45);
        this.renderButtons(matrices, mouseX, mouseY);
        this.renderWaystoneNames(matrices, this.x + 36, this.y + 40, n);
        this.searchField.render(matrices, mouseX, mouseY, delta);
        this.renderButtonTooltips(matrices, mouseX, mouseY);
    }

    @Inject(method = "mouseReleased", at = @At("HEAD"))
    public void mouseReleased(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        this.mouseClicked = false;
    }
}
