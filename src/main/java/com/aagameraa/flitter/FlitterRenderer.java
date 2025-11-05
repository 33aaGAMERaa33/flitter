package com.aagameraa.flitter;

import com.aagameraa.flitter.events.WidgetRendererRegisterEvent;
import com.aagameraa.flitter.exceptions.ElementFactoryNotFoundedException;
import com.aagameraa.flitter.factories.IElementLayoutFactory;
import com.aagameraa.flitter.factories.IElementRendererFactory;
import com.aagameraa.flitter.interfaces.*;
import com.aagameraa.flitter.material.*;
import com.aagameraa.flitter.models.BoxConstraints;
import com.aagameraa.flitter.models.Position;
import com.aagameraa.flitter.utils.ElementFactory;
import com.aagameraa.flitter.utils.ElementFactoryMap;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = Flitter.MOD_ID)
public class FlitterRenderer {
    private @Nullable BuildTree lastBuildTree;
    private static final FlitterRenderer instance = new FlitterRenderer();
    private final ElementFactoryMap elementFactoryMap = new ElementFactoryMap();
    private @NotNull ArrayList<IElementRenderer> renderList = new ArrayList<>();
    private @NotNull HashMap<Element, IElementRenderer> renderMap = new HashMap<>();

    private FlitterRenderer() {

    }

    @SubscribeEvent
    public void render(RenderGuiOverlayEvent.Post event) {
        final var graphics = event.getGuiGraphics();

        try {
            for(final var elementRender : renderList) {
                elementRender.render(graphics);
            }
        }catch (Exception e) {
            if(Flitter.onError != null) Flitter.onError.accept(e);
            else throw e;
        }
    }

    @SubscribeEvent
    public void preRender(RenderGuiOverlayEvent.Pre event) {
        final var currentBuildTree = this.getRootBuildTree();
        if(currentBuildTree.equals(this.lastBuildTree)) return;
        this.lastBuildTree = new BuildTree(currentBuildTree);
        final var window = Minecraft.getInstance().getWindow();

        final var position = new Position(0, 0);
        final var boxConstraints = new BoxConstraints(
                0, window.getGuiScaledWidth(),
                0, window.getGuiScaledHeight()
        );

        final var start = System.nanoTime();
        this.renderList = this.buildElementsRenderersByBuildTree(currentBuildTree, position, boxConstraints);
        final var elapsed = System.nanoTime() - start;
        Flitter.LOGGER.info("Build time: {}ms", elapsed / 1_000_000.0);
    }

    public void rebuildRenders() {
        this.lastBuildTree = null;
    }

    private @NotNull ArrayList<IElementRenderer> buildElementsRenderersByBuildTree(
            @NotNull BuildTree buildTree,
            @NotNull Position position,
            @NotNull BoxConstraints constraints
    ) {
        final var renderersList = new ArrayList<IElementRenderer>();

        for(final var widget : buildTree.getWidgetsStack()) {
            final var renderer = this.buildElementRenderer(
                    position,
                    widget,
                    constraints
            );

            renderersList.add(renderer);
        }

        for(final var subBuildTree : buildTree.getBuildTreesStack()) {
            renderersList.addAll(this.buildElementsRenderersByBuildTree(subBuildTree, position, constraints));
        }

        return renderersList;
    }

    private @NotNull IElementRenderer buildElementRenderer(
            @NotNull Position position,
            @NotNull Widget widget,
            @NotNull BoxConstraints constraints
    ) {
        final var element = this.createElement(widget);

        final var layoutFactory = this.getElementLayoutFactory(element);
        final var rendererFactory = this.getElementRendererFactory(element);

        final var layout = layoutFactory.buildLayout(element, constraints);

        return rendererFactory.buildRenderer(position, element, layout);
    }

    private @NotNull Element createElement(@NotNull Widget widget) {
        Element element = this.buildElement(widget.createElement());
        element.mount(null);
        element.attach(widget);

        return element;
    }

    private @NotNull Element buildElement(@NotNull Element element) {
        if(element instanceof StatelessElement statelessElement) {
            element = this.buildElement(statelessElement.build().createElement());
        }else if(element instanceof StatefulElement statefulElement) {
            element = this.buildElement(statefulElement.build().createElement());
        }else if(element instanceof CompoundElement compoundElement) {

        }

        return element;
    }

    private @NotNull ElementFactory getElementFactories(@NotNull Element element) {
        final var factory = this.elementFactoryMap.get(element.getClass());
        if(factory == null) throw new ElementFactoryNotFoundedException(element);

        return factory;
    }

    private @NotNull IElementLayoutFactory<Element, IElementLayout> getElementLayoutFactory(Element element) {
        final var elementFactory = this.getElementFactories(element);
        return Objects.requireNonNull(elementFactory.getA());
    }

    private @NotNull IElementRendererFactory<Element, IElementLayout> getElementRendererFactory(Element element) {
        final var elementFactory = this.getElementFactories(element);
        return Objects.requireNonNull(elementFactory.getB());
    }

    private BuildTree getRootBuildTree() {
        return Flitter.getRootBuildTree();
    }


    void initialize() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.post(new WidgetRendererRegisterEvent(this.elementFactoryMap));
    }

    public static FlitterRenderer getInstance() {
        return instance;
    }
}
