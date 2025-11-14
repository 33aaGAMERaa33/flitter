package com.aagameraa.flitter;

import com.aagameraa.flitter.material.*;
import com.aagameraa.flitter.models.Constraints;
import com.aagameraa.flitter.models.Offset;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Flitter.MOD_ID)
public class FlitterRenderer {
    private @Nullable BuildTree lastBuildTree = null;
    private List<@NotNull RenderObject> rendererTree = new ArrayList<>();
    private static final FlitterRenderer instance = new FlitterRenderer();

    private FlitterRenderer() {

    }

    @SubscribeEvent
    public void render(RenderGuiOverlayEvent.Post event) {
        final var graphics = event.getGuiGraphics();
        final var window = Minecraft.getInstance().getWindow();
        final var offset = new Offset(0, 0);
        final var constraints = new Constraints(window.getGuiScaledWidth(), window.getGuiScaledHeight());

        for(final var renderer : this.rendererTree) {
            renderer.layout(constraints);
            renderer.render(graphics, offset);
        }
    }

    @SubscribeEvent
    public void preRender(RenderGuiOverlayEvent.Pre event) {
        final var currentBuildTree = this.getCurrentRootBuildTree();
        if(currentBuildTree.equals(this.lastBuildTree)) return;
        final var start = System.nanoTime();

        final var elements = this.buildElementsTree(currentBuildTree);
        this.rendererTree = this.buildRenderTree(elements);

        this.lastBuildTree = currentBuildTree;

        final var elapsed = (System.nanoTime() - start) / 1_000_000.0;
        System.out.printf("Builded %s widget(s) in %sms\n", elements.size(), elapsed);
    }

    public @NotNull List<@NotNull Element> buildElementsTree(@NotNull BuildTree buildTree) {
        final var elements = new ArrayList<Element>();

        for(final var widget : buildTree.widgets) {
            final var element = widget.createElement();
            element.mount(null, null);

            elements.add(element);
        }

        for(final var subBuildTree : buildTree.subBuildTrees) {
            elements.addAll(this.buildElementsTree(subBuildTree));
        }

        return elements;
    }

    public @NotNull List<@NotNull RenderObject> buildRenderTree(@NotNull List<Element> elements) {
        final var rendersObjects = new ArrayList<@NotNull RenderObject>();

        elements.stream().parallel().forEach(element -> {
            @Nullable RenderObject renderObject;

            if(element instanceof ComponentElement componentElement) {
                final var child = componentElement.getChild();
                if(child.getRenderObject() == null) return;

                renderObject = child.getRenderObject();
            }else {
                if(element.getRenderObject() == null) return;
                renderObject = element.getRenderObject();
            }

            if(renderObject == null) return;
            rendersObjects.add(renderObject);
        });

        return rendersObjects;
    }

    void initialize() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void resetBuildTree() {
        if(this.lastBuildTree == null) return;
        this.lastBuildTree = null;
        System.out.println("BuildTree reseted");
    }

    public @NotNull BuildTree getCurrentRootBuildTree() {
        return Flitter.rootBuildTree;
    }

    public static FlitterRenderer getInstance() {
        return instance;
    }
}
