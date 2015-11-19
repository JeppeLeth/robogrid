package com.jleth.projects.robogrid.core.model;

/**
 * Rotation options for object
 */
public enum Rotation {

    /**
     * 90 degree east
     */
    LEFT(90),

    /**
     * 90 degree west
     */
    RIGHT(-90);

    private final int degree;

    Rotation(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }
}
