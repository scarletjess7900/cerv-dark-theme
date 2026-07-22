package dev.scarletjess.cervdarktheme.mixin;

import java.util.function.IntSupplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.scarletjess.cervdarktheme.CDT;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LoadingOverlay;

@Mixin(LoadingOverlay.class)
public abstract class LoadingOverlayMixin {

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/function/IntSupplier;getAsInt()I"
            )
    )
    private int cdt$background(IntSupplier supplier) {
        return CDT.background(supplier.getAsInt());
    }

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;setColor(FFFF)V",
                    ordinal = 0
            )
    )
    private void cdt$logoTint(GuiGraphics graphics, float r, float g, float b, float a) {
        float[] rgb = CDT.logoRgb();
        graphics.setColor(rgb[0], rgb[1], rgb[2], a);
    }

    @ModifyConstant(
            method = "render",
            constant = @Constant(floatValue = 500.0F),
            require = 0
    )
    private float cdt$fadeIn(float original) {
        return CDT.fadeInMs(original);
    }

    @ModifyConstant(
            method = "render",
            constant = @Constant(floatValue = 1000.0F),
            require = 0
    )
    private float cdt$fadeOut(float original) {
        return CDT.fadeOutMs(original);
    }

    @Inject(method = "drawProgressBar", at = @At("HEAD"))
    private void cdt$barBackground(GuiGraphics graphics, int minX, int minY, int maxX, int maxY,
                                   float partialTick, CallbackInfo ci) {
        int alpha = Math.round(partialTick * 255.0F);
        graphics.fill(minX + 2, minY + 2, maxX - 2, maxY - 2, CDT.barBackground(alpha));
    }

    @ModifyArg(
            method = "drawProgressBar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V",
                    ordinal = 0
            ),
            index = 4
    )
    private int cdt$barFill(int color) {
        return CDT.bar(color);
    }

    @ModifyArg(
            method = "drawProgressBar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V",
                    ordinal = 1
            ),
            index = 4
    )
    private int cdt$borderTop(int color) {
        return CDT.border(color);
    }

    @ModifyArg(
            method = "drawProgressBar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V",
                    ordinal = 2
            ),
            index = 4
    )
    private int cdt$borderBottom(int color) {
        return CDT.border(color);
    }

    @ModifyArg(
            method = "drawProgressBar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V",
                    ordinal = 3
            ),
            index = 4
    )
    private int cdt$borderLeft(int color) {
        return CDT.border(color);
    }

    @ModifyArg(
            method = "drawProgressBar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V",
                    ordinal = 4
            ),
            index = 4
    )
    private int cdt$borderRight(int color) {
        return CDT.border(color);
    }
}
