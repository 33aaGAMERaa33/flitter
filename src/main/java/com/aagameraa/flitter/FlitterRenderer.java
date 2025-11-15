package com.aagameraa.flitter;

import com.aagameraa.flitter.material.*;
import com.aagameraa.flitter.models.Constraints;
import com.aagameraa.flitter.models.Offset;
import com.aagameraa.flitter.widgets.ViewWidget;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = Flitter.MOD_ID)
public class FlitterRenderer {
    private @Nullable Element rootElement = null;
    private @Nullable BuildTree lastBuildTree = null;
    private static final FlitterRenderer instance = new FlitterRenderer();

    private FlitterRenderer() {

    }

    @SubscribeEvent
    public void render(RenderGuiOverlayEvent.Post event) {
        if(rootElement == null || !(rootElement instanceof RenderObjectElement)) return;
        final var rootRender = rootElement.getRenderObject();
        if(rootRender == null) return;

        final var graphics = event.getGuiGraphics();
        final var offset = new Offset(0, 0);
        final var window = Minecraft.getInstance().getWindow();
        final var constraints = new Constraints(window.getGuiScaledWidth(), window.getGuiScaledHeight());

        rootRender.layout(constraints);
        rootRender.render(graphics, offset);
    }

    @SubscribeEvent
    public void preRender(RenderGuiOverlayEvent.Pre event) {
        final var currentBuildTree = this.getCurrentRootBuildTree();
        if(currentBuildTree.equals(this.lastBuildTree)) return;
        final var start = System.nanoTime();

        this.rootElement = this.buildElementTree(currentBuildTree);
        this.lastBuildTree = currentBuildTree;

        final var elapsed = (System.nanoTime() - start) / 1_000_000.0;
        System.out.printf("Builded in %sms\n", elapsed);
    }

    private @NotNull Element buildElementTree(@NotNull BuildTree buildTree) {
        final var rootElement = new ViewWidget(buildTree.widgets).createElement();
        rootElement.mount(null, null);

        return rootElement;
    }

    void initialize() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void resetBuildTree() {
        if(this.lastBuildTree == null) return;
        this.lastBuildTree = null;
        System.out.println("BuildTree reseted");
    }

    public static FlitterRenderer getInstance() {
        return instance;
    }

    public @NotNull BuildTree getCurrentRootBuildTree() {
        return Flitter.rootBuildTree;
    }
}
