package com.aagameraa.flitter;

import com.aagameraa.flitter.events.WidgetRendererRegisterEvent;
import com.aagameraa.flitter.material.*;
import com.aagameraa.flitter.widgets.CenterWidget;
import com.aagameraa.flitter.widgets.align.*;
import com.aagameraa.flitter.widgets.text.TextElement;
import com.aagameraa.flitter.widgets.text.TextLayout;
import com.aagameraa.flitter.widgets.text.TextRenderer;
import com.aagameraa.flitter.widgets.text.TextWidget;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
    private static @NotNull BuildTree rootBuildTree = new BuildTree();

    public Flitter(FMLJavaModLoadingContext modLoadingContext) {
        IEventBus modEventBus = modLoadingContext.getModEventBus();
        MinecraftForge.EVENT_BUS.register(new FlitterBootstrap());
        MinecraftForge.EVENT_BUS.register(this);
    }

    static void rebuildRootBuildTree() {
        rootBuildTree = new BuildTree();
    }

    static @NotNull BuildTree getRootBuildTree() {
        return rootBuildTree;
    }

    static class FlitterBootstrap {
        @SubscribeEvent
        public void bootstrap(TickEvent.ClientTickEvent event) {
            FlitterRenderer.getInstance().initialize();
        }

        @SubscribeEvent
        public void registerRenderers(WidgetRendererRegisterEvent event) {
            MinecraftForge.EVENT_BUS.unregister(this);
            event.register(TextElement.class, TextLayout::new, TextRenderer::new);
            event.register(AlignElement.class, AlignLayout::new, AlignRenderer::new);

            final var app = new FlitterApp(rootBuildTree.pushNewBuildTree());

            app.pushWidget(new CenterWidget(
                    new TextWidget.Builder("Olá, Mundo").build()
            ));

            app.pushWidget(new TextWidget.Builder("Olá, Mundo!").build());
        }
    }
}