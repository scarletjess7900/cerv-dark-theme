package dev.scarletjess.cervdarktheme.mixin;

import java.util.function.IntSupplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.gui.screens.LoadingOverlay;

@Mixin(LoadingOverlay.class)
public interface LoadingOverlayAccessor {

    @Accessor("BRAND_BACKGROUND")
    @Mutable
    static void setBrandBackground(IntSupplier supplier) {
        throw new AssertionError();
    }
}
