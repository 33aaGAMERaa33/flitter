package com.aagameraa.flitter.utils;

import com.aagameraa.flitter.factories.IElementLayoutFactory;
import com.aagameraa.flitter.factories.IElementRendererFactory;
import com.aagameraa.flitter.material.Element;
import com.aagameraa.flitter.interfaces.IElementLayout;
import oshi.util.tuples.Pair;

public class ElementFactory extends Pair<
        IElementLayoutFactory<Element, IElementLayout>,
        IElementRendererFactory<Element, IElementLayout>
> {
    public ElementFactory(IElementLayoutFactory<Element, IElementLayout> iElementIElementLayoutIElementLayoutFactory, IElementRendererFactory<Element, IElementLayout> iElementIElementLayoutIElementRendererFactory) {
        super(iElementIElementLayoutIElementLayoutFactory, iElementIElementLayoutIElementRendererFactory);
    }
}
