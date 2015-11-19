package com.jleth.projects.robogrid.core;

/**
 * Factory for creating a {@link Grid}
 */
public class GridFactory {

    public GridFactory() {
    }

    /**
     * Create a rectangular grid with x column with and y row height
     *
     * @param cols number of columns
     * @param rows number of rows
     * @return a {@link Grid} object.
     */
    public Grid createGridRect(int cols, int rows) {
        return new GridRectImpl(cols, rows);
    }
}