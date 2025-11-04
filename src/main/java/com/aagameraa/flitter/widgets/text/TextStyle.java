package com.aagameraa.flitter.widgets.text;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public record TextStyle(
        Font font,
        Color color
) {
    public static class Builder {
        private int fontSize = 8;
        private @NotNull Color color = Color.white;
        private @NotNull int fontStyle = Font.BOLD;

        public TextStyle build() {
            return new TextStyle(new Font("Arial", fontStyle, fontSize), color);
        }
    }
}
