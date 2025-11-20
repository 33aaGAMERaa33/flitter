package com.aagameraa.flitter;

import com.aagameraa.flitter.material.*;
import com.aagameraa.flitter.material.elements.ComponentElement;
import com.aagameraa.flitter.material.elements.RenderObjectElement;
import com.aagameraa.flitter.models.Constraints;
import com.aagameraa.flitter.models.Offset;
import com.aagameraa.flitter.widgets.ViewWidget;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Flitter.MOD_ID)
public class FlitterRenderer {
    private @Nullable Element rootElement = null;
    private @Nullable BuildTree lastBuildTree = null;
    private static final FlitterRenderer instance = new FlitterRenderer();
    private final List<@NotNull ComponentElement> dirtyElements = new ArrayList<>();
    private final List<@NotNull RenderObject> dirtyRenderObjects = new ArrayList<>();

    private FlitterRenderer() {

    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void render(RenderGuiOverlayEvent.Post event) {
        try {
            if(rootElement == null || !(rootElement instanceof RenderObjectElement)) return;
            else if(rootElement.getRenderObject() == null) return;

            final var graphics = event.getGuiGraphics();
            final var offset = new Offset(0, 0);

            rootElement.getRenderObject().render(graphics, offset);
        }catch (Exception e) {
            rootElement = null;
            e.printStackTrace();
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void preRender(RenderGuiOverlayEvent.Post event) {
        final var currentBuildTree = this.getCurrentRootBuildTree();

        if(currentBuildTree == null) {
            if(lastBuildTree != null) this.lastBuildTree = null;
            if(this.rootElement != null) this.rootElement = null;
            return;
        }else if(currentBuildTree.equals(this.lastBuildTree)) {
            try {
                if(this.dirtyElements.isEmpty() && this.dirtyRenderObjects.isEmpty()) return;
                final var start = System.nanoTime();

                if(!this.dirtyElements.isEmpty()) this.processDirtyElements();
                if(!this.dirtyRenderObjects.isEmpty()) this.processDirtyRenderObjects();

                final var elapsed = (System.nanoTime() - start) / 1_000_000.0;
                Flitter.LOGGER.info("Updated in {}ms", elapsed);
            }catch (Exception e) {
                e.printStackTrace();
                this.dirtyElements.clear();
                this.dirtyRenderObjects.clear();
            }
            return;
        }

        final var start = System.nanoTime();

        this.rootElement = this.buildElementTree(currentBuildTree);
        this.lastBuildTree = currentBuildTree;
        final var rootRender = this.rootElement.getRenderObject();

        final var window = Minecraft.getInstance().getWindow();
        final var constraints = new Constraints(window.getGuiScaledWidth(), window.getGuiScaledHeight());

        if(rootRender != null) rootRender.layout(constraints);

        final var elapsed = (System.nanoTime() - start) / 1_000_000.0;
        Flitter.LOGGER.info("Builded in {}ms", elapsed);
    }

    private @NotNull Element buildElementTree(@NotNull BuildTree buildTree) {
        final var rootElement = new ViewWidget(buildTree.widgets).createElement();
        rootElement.mount(null, null);

        return rootElement;
    }

    private void processDirtyElements() {
        if(this.dirtyElements.isEmpty()) return;

        for(final var element : this.dirtyElements) {
            element.rebuild();
        }

        this.dirtyElements.clear();
    }

    public void scheduleBuildFor(@NotNull ComponentElement element) {
        if(this.dirtyElements.contains(element)) return;
        this.dirtyElements.add(element);
    }

    private void processDirtyRenderObjects() {
        if(this.dirtyRenderObjects.isEmpty()) return;

        for(final var element : this.dirtyRenderObjects) {
            element.relayout();
        }

        this.dirtyRenderObjects.clear();
    }

    public void scheduleLayoutFor(@NotNull RenderObject renderObject) {
        if(this.dirtyRenderObjects.contains(renderObject)) return;
        this.dirtyRenderObjects.add(renderObject);
    }


    void initialize() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void resetBuildTree() {
        if(this.lastBuildTree == null) return;
        this.lastBuildTree = null;
        if(this.rootElement != null) this.rootElement.unmount();
        Flitter.LOGGER.info("BuildTree reseted");
    }

    public static FlitterRenderer getInstance() {
        return instance;
    }

    public @Nullable BuildTree getCurrentRootBuildTree() {
        return Flitter.rootBuildTree;
    }
}
