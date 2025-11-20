package com.aagameraa.flitter.material;

public record Padding(
        int top,
        int right,
        int bottom,
        int left
) {

    // -------------------------------------------------
    // FACTORIES
    // -------------------------------------------------

    /** padding igual em todos os lados */
    public static Padding all(int value) {
        return new Padding(value, value, value, value);
    }

    /** padding apenas em cima, direita, baixo, esquerda (igual Flutter.fromLTRB) */
    public static Padding fromLTRB(int left, int top, int right, int bottom) {
        return new Padding(top, right, bottom, left);
    }

    /** padding apenas nos lados especificados */
    public static Padding only(
            Integer left,
            Integer top,
            Integer right,
            Integer bottom
    ) {
        return new Padding(
                top != null ? top : 0,
                right != null ? right : 0,
                bottom != null ? bottom : 0,
                left != null ? left : 0
        );
    }

    /** padding vertical e horizontal */
    public static Padding symmetric(int vertical, int horizontal) {
        return new Padding(vertical, horizontal, vertical, horizontal);
    }

    /** padding vertical (top/bottom) */
    public static Padding vertical(int value) {
        return new Padding(value, 0, value, 0);
    }

    /** padding horizontal (left/right) */
    public static Padding horizontal(int value) {
        return new Padding(0, value, 0, value);
    }


    // -------------------------------------------------
    // HELPERS
    // -------------------------------------------------

    /** soma dois paddings */
    public Padding add(Padding other) {
        return new Padding(
                this.top + other.top,
                this.right + other.right,
                this.bottom + other.bottom,
                this.left + other.left
        );
    }
}
