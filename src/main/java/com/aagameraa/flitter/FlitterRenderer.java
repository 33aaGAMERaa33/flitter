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
import java.util.Objects;

@Mod.EventBusSubscriber(modid = Flitter.MOD_ID)
public class FlitterRenderer {
    private @Nullable BuildTree lastBuildTree;
    private static final FlitterRenderer instance = new FlitterRenderer();
    private final ElementFactoryMap elementFactoryMap = new ElementFactoryMap();
    private @NotNull ArrayList<IElementRenderer> rendererList = new ArrayList<>();

    private FlitterRenderer() {

    }

    @SubscribeEvent
    public void render(RenderGuiOverlayEvent.Post event) {
        final var graphics = event.getGuiGraphics();

        try {
            for(final var elementRender : rendererList) {
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
        final var start = System.nanoTime();
        this.lastBuildTree = new BuildTree(currentBuildTree);
        final var window = Minecraft.getInstance().getWindow();

        final var position = new Position(0, 0);
        final var boxConstraints = new BoxConstraints(
                0, window.getGuiScaledWidth(),
                0, window.getGuiScaledHeight()
        );

        this.rendererList = this.buildRendererTree(position, boxConstraints, currentBuildTree);

        final var elapsed = System.nanoTime() - start;
        Flitter.LOGGER.info("Build time: {}ms", elapsed / 1_000_000.0);
    }

    private @NotNull ArrayList<IElementRenderer> buildRendererTree(
            @NotNull Position position,
            @NotNull BoxConstraints constraints,
            @NotNull BuildTree buildTree
    ) {
        final var renderers = new ArrayList<IElementRenderer>();

        for(final var widget : buildTree.getWidgetsStack()) {
            final var element = this.buildElement(widget.createElement());
            final var layoutFactory = this.getElementLayoutFactory(element);
            final var rendererFactory = this.getElementRendererFactory(element);

            final var layout = layoutFactory.buildLayout(element, constraints);
            final var renderer = rendererFactory.buildRenderer(position, element, layout);

            if(element instanceof CompoundElement compoundElement
                    && layout instanceof ICompoundElementLayout compoundElementLayout
                    && renderer instanceof ICompoundElementRenderer compoundElementRenderer
            ) {
                for(final var child : compoundElement.childrens()) {
                    final var childLayoutFactory = this.getElementLayoutFactory(child);
                    final var childRendererFactory = this.getElementRendererFactory(child);

                    final var childLayout = compoundElementLayout.buildChildLayout(child, childLayoutFactory);
                    final var childRenderer = compoundElementRenderer.buildChildRenderer(child, childLayout, childRendererFactory);
                    renderers.add(childRenderer);
                }
            }

            renderers.add(renderer);
        }

        for(final var subBuildTree : buildTree.getBuildTreesStack()) {
            renderers.addAll(this.buildRendererTree(position, constraints, subBuildTree));
        }

        return renderers;
    }

    public @NotNull Element buildElement(@NotNull Element element) {
        if(element instanceof StatelessElement statelessElement) {
            final var buildedElement = statelessElement.build().createElement();
            buildedElement.mount(element);

            return this.buildElement(buildedElement);
        }else if(element instanceof CompoundElement compoundElement) {
            final var childrens = new ArrayList<Element>();

            for(final var childToAttach : compoundElement.childrensToAttach()) {
                childrens.add(this.buildElement(childToAttach));
            }

            compoundElement.attachChildrens(childrens);
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

    void initialize() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.post(new WidgetRendererRegisterEvent(this.elementFactoryMap));
    }

    public void resetBuildTree() {
        this.lastBuildTree = null;
    }

    private BuildTree getRootBuildTree() {
        return Flitter.getRootBuildTree();
    }

    public static FlitterRenderer getInstance() {
        return instance;
    }
}
