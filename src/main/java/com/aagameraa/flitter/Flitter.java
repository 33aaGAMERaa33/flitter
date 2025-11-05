package com.aagameraa.flitter;

import com.aagameraa.flitter.events.WidgetRendererRegisterEvent;
import com.aagameraa.flitter.material.*;
import com.aagameraa.flitter.widgets.align.*;
import com.aagameraa.flitter.widgets.column.ColumnElement;
import com.aagameraa.flitter.widgets.column.ColumnLayout;
import com.aagameraa.flitter.widgets.column.ColumnRenderer;
import com.aagameraa.flitter.widgets.row.RowElement;
import com.aagameraa.flitter.widgets.row.RowLayout;
import com.aagameraa.flitter.widgets.row.RowRenderer;
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

import java.util.Arrays;
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
            event.register(RowElement.class, RowLayout::new, RowRenderer::new);
            event.register(TextElement.class, TextLayout::new, TextRenderer::new);
            event.register(AlignElement.class, AlignLayout::new, AlignRenderer::new);
            event.register(ColumnElement.class, ColumnLayout::new, ColumnRenderer::new);

            final var app = new FlitterApp(rootBuildTree.pushNewBuildTree());
            app.pushWidget(new TestWidget());
        }
    }
}

class TestWidget extends StatefulWidget {
    @Override
    public @NotNull TestWidgetState createState() {
        return new TestWidgetState();
    }
}

class TestWidgetState extends StateWidget<TestWidget> {
    private int count = 0;

    @Override
    public @NotNull Widget build(BuildContext context) {
        return new AlignWidget(
                Alignment.CENTER_END,
                new TextWidget.Builder(String.valueOf(count)).build()
        );
    }

    @Override
    public void initState() {
        super.initState();

        new Thread(() -> {
            while(true) {
                try {
                    setState(() -> count++);
                    Thread.sleep(1000);
                }catch(Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }
}