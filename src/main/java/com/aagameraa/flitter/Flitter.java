package com.aagameraa.flitter;

import com.aagameraa.flitter.events.WidgetRendererRegisterEvent;
import com.aagameraa.flitter.interfaces.IWidget;
import com.aagameraa.flitter.material.Context;
import com.aagameraa.flitter.material.FlitterApp;
import com.aagameraa.flitter.widgets.CenterWidget;
import com.aagameraa.flitter.widgets.align.*;
import com.aagameraa.flitter.widgets.column.ColumnElement;
import com.aagameraa.flitter.widgets.column.ColumnLayout;
import com.aagameraa.flitter.widgets.column.ColumnRenderer;
import com.aagameraa.flitter.widgets.column.ColumnWidget;
import com.aagameraa.flitter.widgets.row.RowElement;
import com.aagameraa.flitter.widgets.row.RowLayout;
import com.aagameraa.flitter.widgets.row.RowRenderer;
import com.aagameraa.flitter.widgets.row.RowWidget;
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

import java.util.List;
import java.util.function.Consumer;

@Mod(Flitter.MOD_ID)
public class Flitter {
    public static final String MOD_ID = "flitter";
    public static @Nullable Consumer<Exception> onError;
    public static final Logger LOGGER = LogUtils.getLogger();
    private static @NotNull Context rootContext = new Context();

    public Flitter(FMLJavaModLoadingContext modLoadingContext) {
        IEventBus modEventBus = modLoadingContext.getModEventBus();
        MinecraftForge.EVENT_BUS.register(new FlitterBootstrap());
        MinecraftForge.EVENT_BUS.register(this);
    }

    static void rebuildContext() {
        rootContext = new Context();
    }

    static @NotNull Context getRootContext() {
        return rootContext;
    }

    public static FlitterApp buildApp() {
        return new FlitterApp(rootContext.pushNewContext());
    }

    static class FlitterBootstrap {
        @SubscribeEvent
        public void bootstrap(TickEvent.ClientTickEvent event) {
            FlitterRenderer.getInstance().initialize();
        }

        @SubscribeEvent
        public void registerRenderers(WidgetRendererRegisterEvent event) {
            MinecraftForge.EVENT_BUS.unregister(this);
            event.register(RowElement.class, RowLayout::new, RowRenderer::new);
            event.register(TextElement.class, TextLayout::new, TextRenderer::new);
            event.register(AlignElement.class, AlignLayout::new, AlignRenderer::new);
            event.register(ColumnElement.class, ColumnLayout::new, ColumnRenderer::new);

            rootContext.pushWidget(new CenterWidget(
                    new ColumnWidget.Builder(List.of(
                            new RowWidget.Builder(List.of(
                                    new TextWidget.Builder("Olá, Mundo! 1").build(),
                                    new TextWidget.Builder("Olá, Mundo! 2").build()
                            )).spacing(12).build(),
                            new RowWidget.Builder(List.of(
                                    new TextWidget.Builder("Olá, Mundo! 3").build(),
                                    new TextWidget.Builder("Olá, Mundo! 4").build()
                            )).spacing(12).build()
                    )).spacing(12).build()
            ));
        }
    }
}
