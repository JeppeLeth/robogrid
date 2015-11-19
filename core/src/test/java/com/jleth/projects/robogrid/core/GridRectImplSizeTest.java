package com.jleth.projects.robogrid.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class GridRectImplSizeTest {

    @Test
    public void correctWidth() throws Exception {
        Grid grid = new GridRectImpl(10, 5);

        assertEquals(10, grid.getColumnCount());
    }

    @Test
    public void correctHeight() throws Exception {
        Grid grid = new GridRectImpl(5, 10);

        assertEquals(10, grid.getRowCount());
    }

    @Test
    public void minimumSize1X1() throws Exception {
        Grid grid = new GridRectImpl(1, 1);

        assertEquals(1, grid.getRowCount());
        assertEquals(1, grid.getColumnCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void notZeroWidth() throws Exception {
        new GridRectImpl(0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notZeroHeight() throws Exception {
        new GridRectImpl(10, 0);
    }
}