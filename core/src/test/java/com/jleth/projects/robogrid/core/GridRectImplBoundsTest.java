package com.jleth.projects.robogrid.core;

import com.jleth.projects.robogrid.core.model.Direction;
import com.jleth.projects.robogrid.core.model.InvalidPositionException;
import com.jleth.projects.robogrid.core.model.Position;
import com.jleth.projects.robogrid.core.model.Step;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class GridRectImplBoundsTest {

    @Test
    public void notMoveOutsideXBound_plus() throws Exception {
        Grid grid = new GridRectImpl(10, 10);
        grid.init(new Position(9, 5, Direction.EAST));

        assertFalse(grid.move(Step.FORWARD));
    }

    @Test
    public void notMoveOutsideXBound_minus() throws Exception {
        Grid grid = new GridRectImpl(10, 10);
        grid.init(new Position(0, 5, Direction.WEST));

        assertFalse(grid.move(Step.FORWARD));
    }

    @Test
    public void notMoveOutsideYBound_plus() throws Exception {
        Grid grid = new GridRectImpl(10, 10);
        grid.init(new Position(3, 9, Direction.SOUTH));

        assertFalse(grid.move(Step.FORWARD));
    }

    @Test
    public void notMoveOutsideYBound_minus() throws Exception {
        Grid grid = new GridRectImpl(10, 10);
        grid.init(new Position(3, 0, Direction.NORTH));

        assertFalse(grid.move(Step.FORWARD));
    }

    @Test(expected = InvalidPositionException.class)
    public void notInitializeOutsideXBound_plus() throws Exception {
        Grid grid = new GridRectImpl(10, 10);
        grid.init(new Position(10, 0, Direction.NORTH));

        assertFalse(grid.move(Step.FORWARD));
    }

    @Test(expected = InvalidPositionException.class)
    public void notInitializeOutsideXBound_minus() throws Exception {
        Grid grid = new GridRectImpl(10, 10);
        grid.init(new Position(-1, 0, Direction.NORTH));

        assertFalse(grid.move(Step.FORWARD));
    }

    @Test(expected = InvalidPositionException.class)
    public void notInitializeOutsideYBound_plus() throws Exception {
        Grid grid = new GridRectImpl(10, 10);
        grid.init(new Position(3, 10, Direction.NORTH));

        assertFalse(grid.move(Step.FORWARD));
    }

    @Test(expected = InvalidPositionException.class)
    public void notInitializeOutsideYBound_minus() throws Exception {
        Grid grid = new GridRectImpl(10, 10);
        grid.init(new Position(0, -1, Direction.NORTH));

        assertFalse(grid.move(Step.FORWARD));
    }

}