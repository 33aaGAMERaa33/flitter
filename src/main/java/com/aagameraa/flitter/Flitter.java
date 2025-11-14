package com.aagameraa.flitter;

import com.aagameraa.flitter.material.BuildContext;
import com.aagameraa.flitter.material.BuildTree;
import com.aagameraa.flitter.material.StatelessWidget;
import com.aagameraa.flitter.material.Widget;
import com.aagameraa.flitter.widgets.RichTextWidget;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.function.Consumer;

@Mod(Flitter.MOD_ID)
public class Flitter {
    public static final String MOD_ID = "flitter";
    public static @Nullable Consumer<Exception> onError;
    public static final Logger LOGGER = LogUtils.getLogger();
    static final BuildTree rootBuildTree = new BuildTree();

    public Flitter(FMLJavaModLoadingContext modLoadingContext) {
        //IEventBus modEventBus = modLoadingContext.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        FlitterRenderer.getInstance().initialize();

        rootBuildTree.widgets.add(new StatelessWidget() {
            @Override
            public @NotNull Widget build(@NotNull BuildContext context) {
                return new StatelessWidget() {
                    @Override
                    public @NotNull Widget build(@NotNull BuildContext context) {
                        return new RichTextWidget("Olá, Mundo!");
                    }
                };
            }
        });
    }
}