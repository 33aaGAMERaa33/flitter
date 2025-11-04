package com.aagameraa.flitter;

import com.aagameraa.flitter.events.WidgetRendererRegisterEvent;
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
    private @Nullable Context lastRenderContext;
    private static final FlitterRenderer instance = new FlitterRenderer();
    private final ElementFactoryMap elementFactoryMap = new ElementFactoryMap();
    private @NotNull ArrayList<IElementRenderer> renderList = new ArrayList<>();
    private @NotNull HashMap<Context, HashMap<IWidget, IElementRenderer>> renderCache = new HashMap<>();

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
        final var currentContext = this.getRootContext();
        if(currentContext.equals(this.lastRenderContext)) return;
        this.lastRenderContext = new Context(currentContext);
        final var window = Minecraft.getInstance().getWindow();

        final var position = new Position(0, 0);
        final var boxConstraints = new BoxConstraints(
                0, window.getGuiScaledWidth(),
                0, window.getGuiScaledHeight()
        );

        final var start = System.nanoTime();
        this.renderList = this.buildRenderTreeOfContext(currentContext, position, boxConstraints);
        final var elapsed = System.nanoTime() - start;
        Flitter.LOGGER.info("Build time: {}ms", elapsed / 1_000_000.0);
    }

    public void rebuildRenders() {
        this.lastRenderContext = null;
        this.renderCache = new HashMap<>();
    }

    private @NotNull ArrayList<IElementRenderer> buildRenderTreeOfContext(
            @NotNull Context context,
            @NotNull Position position,
            @NotNull BoxConstraints constraints
    ) {
        final var renderersList = new ArrayList<IElementRenderer>();
        final var contextRenderCache = this.renderCache.get(context);
        final var newRenderCache = new HashMap<IWidget, IElementRenderer>();

        if(contextRenderCache == null) {
            Flitter.LOGGER.info("Cached context not founded");

            for(final var widget : context.getWidgetsStack()) {
                final var renderer = this.buildElementRenderer(
                        position,
                        widget,
                        constraints
                );

                renderersList.add(renderer);

                newRenderCache.put(widget, renderer);
                Flitter.LOGGER.info("{} renderer builded", widget.getClass().getSimpleName());
            }
        }else {
            for(final var widget : context.getWidgetsStack()) {
                Flitter.LOGGER.info("{} renderer cached founded", widget.getClass().getSimpleName());
                final var renderer = contextRenderCache.get(widget);

                renderersList.add(Objects.requireNonNullElseGet(
                        renderer, () -> this.buildElementRenderer(position, widget, constraints))
                );
            }
        }

        for(final var subContext : context.getContextsStack()) {
            renderersList.addAll(this.buildRenderTreeOfContext(subContext, position, constraints));
        }

        this.renderCache.put(context, newRenderCache);
        return renderersList;
    }

    private @NotNull IElementRenderer buildElementRenderer(@NotNull Position position, @NotNull IWidget widget, @NotNull BoxConstraints constraints) {
        Element element = widget.createElement();

        if(element instanceof StatelessElement statelessElement) {
            element = statelessElement.build().createElement();
        }else if(element instanceof StatefulElement statefulElement) {
            element = statefulElement.build().createElement();
        }

        final var layoutFactory = this.getElementLayoutFactory(element);
        final var rendererFactory = this.getElementRendererFactory(element);

        final var layout = layoutFactory.buildLayout(element, constraints);

        return rendererFactory.buildRenderer(position, element, layout);
    }

    public @NotNull ElementFactory getElementFactories(Element element) {
        return Objects.requireNonNull(this.elementFactoryMap.get(element.getClass()));
    }

    public @NotNull IElementLayoutFactory<Element, IElementLayout> getElementLayoutFactory(Element element) {
        final var elementFactory = this.getElementFactories(element);
        return Objects.requireNonNull(elementFactory.getA());
    }

    public @NotNull IElementRendererFactory<Element, IElementLayout> getElementRendererFactory(Element element) {
        final var elementFactory = this.getElementFactories(element);
        return Objects.requireNonNull(elementFactory.getB());
    }

    private Context getRootContext() {
        return Flitter.getRootContext();
    }


    void initialize() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.post(new WidgetRendererRegisterEvent(this.elementFactoryMap));
    }

    public static FlitterRenderer getInstance() {
        return instance;
    }
}
