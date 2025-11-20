package com.aagameraa.flitter;

import com.aagameraa.flitter.material.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.function.Consumer;

@Mod(Flitter.MOD_ID)
public class Flitter {
    public static final String MOD_ID = "flitter";
    static @Nullable BuildTree rootBuildTree;
    public static @Nullable Consumer<Exception> onError;
    public static final Logger LOGGER = LogUtils.getLogger();

    public Flitter(FMLJavaModLoadingContext modLoadingContext) {
        //IEventBus modEventBus = modLoadingContext.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        FlitterRenderer.getInstance().initialize();
    }

    @SubscribeEvent
    public void onLoggedIn(ClientPlayerNetworkEvent.LoggingIn event) {
        rootBuildTree = new BuildTree();
        rootBuildTree.widgets.add(new Counter());
    }

    @SubscribeEvent
    public void onLoggedOut(ClientPlayerNetworkEvent.LoggingOut event) {
        rootBuildTree = null;
    }
}