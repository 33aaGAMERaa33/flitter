package com.aagameraa.flitter.mixins;

import com.aagameraa.flitter.FlitterRenderer;
import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {
    @Inject(method = "onResize", at = @At("TAIL"))
    private void onWindowResize(long window, int width, int height, CallbackInfo ci) {
        FlitterRenderer.getInstance().resetBuildTree();
    }
}
