package com.jleth.projects.robogrid.core;

import com.jleth.projects.robogrid.core.model.Direction;
import com.jleth.projects.robogrid.core.model.Position;
import com.jleth.projects.robogrid.core.model.Rotation;
import com.jleth.projects.robogrid.core.model.Step;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class GridRectImplPositionTest {

    @Test
    public void moveFrom_1_2_N_To_1_3_N() throws Exception {
        Position expected = new Position(1, 3, Direction.NORTH);

        Grid grid = new GridRectImpl(5, 5);
        grid.init(new Position(1, 2, Direction.NORTH));
        grid.rotate(Rotation.RIGHT);
        grid.move(Step.FORWARD);
        grid.rotate(Rotation.RIGHT);
        grid.move(Step.FORWARD);
        grid.move(Step.FORWARD);
        grid.rotate(Rotation.RIGHT);
        grid.move(Step.FORWARD);
        grid.rotate(Rotation.RIGHT);
        grid.move(Step.FORWARD);

        Position actual = grid.getCurrentPosition();

        assertEquals(expected, actual);
    }

    @Test
    public void moveFrom_0_0_E_To_3_1_E() throws Exception {
        Position expected = new Position(3, 1, Direction.EAST);

        Grid grid = new GridRectImpl(5, 5);
        grid.init(new Position(0, 0, Direction.EAST));
        grid.rotate(Rotation.RIGHT);
        grid.move(Step.FORWARD);
        grid.rotate(Rotation.LEFT);
        grid.move(Step.FORWARD);
        grid.move(Step.FORWARD);
        grid.rotate(Rotation.LEFT);
        grid.rotate(Rotation.RIGHT);
        grid.move(Step.FORWARD);

        Position actual = grid.getCurrentPosition();

        assertEquals(expected, actual);
    }
}