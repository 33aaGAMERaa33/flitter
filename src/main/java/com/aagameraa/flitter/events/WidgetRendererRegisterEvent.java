package com.aagameraa.flitter.events;

import com.aagameraa.flitter.factories.IElementLayoutFactory;
import com.aagameraa.flitter.factories.IElementRendererFactory;
import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.interfaces.IElementLayout;
import com.aagameraa.flitter.utils.ElementFactory;
import com.aagameraa.flitter.utils.ElementFactoryMap;
import net.minecraftforge.eventbus.api.Event;

public class WidgetRendererRegisterEvent extends Event {
    private final ElementFactoryMap elementFactoryMap;

    public WidgetRendererRegisterEvent(ElementFactoryMap elementFactoryMap) {
        this.elementFactoryMap = elementFactoryMap;
    }

    public <T extends Element, L extends IElementLayout> void register(
            Class<T> widgetClazz,
            IElementLayoutFactory<T, L> layoutFactory,
            IElementRendererFactory<T, L> rendererFactory
    ) {
        @SuppressWarnings("unchecked")
        final Class<Element> widgetClazzCasted = (Class<Element>) widgetClazz;

        @SuppressWarnings("unchecked")
        final IElementLayoutFactory<Element, IElementLayout> layoutFactoryCasted
                = (IElementLayoutFactory<Element, IElementLayout>) layoutFactory;

        @SuppressWarnings("unchecked")
        final IElementRendererFactory<Element, IElementLayout> rendererFactoryCasted
                = (IElementRendererFactory<Element, IElementLayout>) rendererFactory;

        this.elementFactoryMap.put(widgetClazzCasted, new ElementFactory(layoutFactoryCasted, rendererFactoryCasted));
    }

    @Override
    public boolean isCancelable() {
        return false;
    }
}
