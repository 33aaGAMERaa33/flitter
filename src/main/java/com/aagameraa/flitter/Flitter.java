package com.aagameraa.flitter;

import com.aagameraa.flitter.material.*;
import com.aagameraa.flitter.widgets.CenterWidget;
import com.aagameraa.flitter.widgets.ColumnWidget;
import com.aagameraa.flitter.widgets.RichTextWidget;
import com.aagameraa.flitter.widgets.RowWidget;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
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
    static final BuildTree rootBuildTree = new BuildTree();

    public Flitter(FMLJavaModLoadingContext modLoadingContext) {
        //IEventBus modEventBus = modLoadingContext.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        FlitterRenderer.getInstance().initialize();

        rootBuildTree.widgets.add(new Counter());
    }
}

class Counter extends StatefulWidget {
    @Override
    public @NotNull StateWidget<? extends StatefulWidget> createState() {
        return new CounterState();
    }
}


@Mod.EventBusSubscriber(modid = Flitter.MOD_ID)
class CounterState extends StateWidget<Counter> {
    private int count = 0;
    private @Nullable Thread job;

    @Override
    public @NotNull Widget build(@NotNull BuildContext context) {
        return new RowWidget.Builder(
                List.of(
                        new RichTextWidget(String.valueOf(count)),
                        new RichTextWidget(String.valueOf(count))
                )
        ).build();
    }

    @Override
    public void initState() {
        super.initState();

        this.job = new Thread(() -> {
            try {
                while(true) {
                    setState(() -> count++);
                    Thread.sleep(2000);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.job.start();
    }

    @Override
    public void dispose() {
        super.dispose();
        assert this.job != null;
        this.job.interrupt();
        this.job = null;
    }
}