package com.projectblurple.blurplemod.mixin.minecraft.client;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ExperienceOrbEntityRenderer;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbEntityRenderer.class)
public abstract class ExperienceOrbEntityRendererMixin extends EntityRenderer<ExperienceOrbEntity> {
    private ExperienceOrbEntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Shadow
    private static void vertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, int red, int green, int blue, float u, float v, int light) {
        throw new IllegalStateException();
    }

    @Redirect(method = "render(Lnet/minecraft/entity/ExperienceOrbEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/ExperienceOrbEntityRenderer;vertex(Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/util/math/Matrix3f;FFIIIFFI)V"))
    private void vertex2(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, int red, int green, int blue, float u, float v, int light) {
        int newRed = 118 - red / 9;
        int newGreen = 138 - red / 7;
        int newBlue = 216 + red / 10;
        vertex(vertexConsumer, positionMatrix, normalMatrix, x, y, newRed, newGreen, newBlue, u, v, light);
    }

    @Redirect(method = "vertex", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexConsumer;color(IIII)Lnet/minecraft/client/render/VertexConsumer;"))
    private static VertexConsumer color(VertexConsumer instance, int red, int green, int blue, int alpha) {
        return instance.color(red, green, blue, 255);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.shadowOpacity = 0;
        this.shadowRadius = 0;
    }
}
