package com.aagameraa.flitter.widgets.align;

public enum Alignment {
    TOP_START(VerticalAlignment.TOP, HorizontalAlignment.START),
    TOP_CENTER(VerticalAlignment.TOP, HorizontalAlignment.CENTER),
    TOP_END(VerticalAlignment.TOP, HorizontalAlignment.END),
    CENTER_START(VerticalAlignment.CENTER, HorizontalAlignment.START),
    CENTER_CENTER(VerticalAlignment.CENTER, HorizontalAlignment.CENTER),
    CENTER_END(VerticalAlignment.CENTER, HorizontalAlignment.END),
    BOTTOM_START(VerticalAlignment.BOTTOM, HorizontalAlignment.START),
    BOTTOM_CENTER(VerticalAlignment.BOTTOM, HorizontalAlignment.CENTER),
    BOTTOM_END(VerticalAlignment.BOTTOM, HorizontalAlignment.END);

    private final HorizontalAlignment horizontalAlignment;
    private final VerticalAlignment verticalAlignment;

    Alignment(VerticalAlignment verticalAlignment, HorizontalAlignment horizontalAlignment) {
        this.verticalAlignment = verticalAlignment;
        this.horizontalAlignment = horizontalAlignment;
    }

    public HorizontalAlignment horizontalAlignment() {
        return horizontalAlignment;
    }

    public VerticalAlignment verticalAlignment() {
        return verticalAlignment;
    }
}
