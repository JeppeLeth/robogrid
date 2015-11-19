package com.jleth.projects.robogrid.core.model;

/**
 * Move object in grid
 */
public enum Step {
    FORWARD(1);

    private final int increase;

    Step(int increase) {
        this.increase = increase;
    }

    public int getIncrease() {
        return increase;
    }
}
