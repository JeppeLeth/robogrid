package com.jleth.projects.robogrid.core;

import com.jleth.projects.robogrid.core.model.Direction;
import com.jleth.projects.robogrid.core.model.InvalidPositionException;
import com.jleth.projects.robogrid.core.model.Position;
import com.jleth.projects.robogrid.core.model.Rotation;
import com.jleth.projects.robogrid.core.model.Step;

import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class GridRectImplInvalidArgsTest {

    @Test(expected = IllegalStateException.class)
    public void notMoveBeforeInitialized() throws Exception {
        Grid grid = new GridRectImpl(5, 5);
        grid.move(Step.FORWARD);
    }

    @Test(expected = IllegalStateException.class)
    public void notRotateBeforeInitialized() throws Exception {
        Grid grid = new GridRectImpl(5, 5);
        grid.rotate(Rotation.LEFT);
    }

    @Test(expected = IllegalStateException.class)
    public void notInitializationMoreThanOnce() throws Exception {
        Grid grid = new GridRectImpl(5, 5);
        grid.init(new Position(1, 1, Direction.EAST));
        grid.init(new Position(1, 1, Direction.EAST));
    }

    @Test(expected = InvalidPositionException.class)
    public void noNegativePositionX() throws Exception {
        Grid grid = new GridRectImpl(5, 5);
        grid.init(new Position(-1, 1, Direction.EAST));
    }

    @Test(expected = InvalidPositionException.class)
    public void noNegativePositionY() throws Exception {
        Grid grid = new GridRectImpl(5, 5);
        grid.init(new Position(0, -1, Direction.EAST));
    }

    @Test(expected = InvalidPositionException.class)
    public void noNullPosition() throws Exception {
        Grid grid = new GridRectImpl(5, 5);
        grid.init(null);
    }

    @Test(expected = InvalidPositionException.class)
    public void noNullDirection() throws Exception {
        Grid grid = new GridRectImpl(5, 5);
        grid.init(new Position(0, 0, null));
    }
}