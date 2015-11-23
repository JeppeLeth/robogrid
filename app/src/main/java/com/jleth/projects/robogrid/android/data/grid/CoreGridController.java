package com.jleth.projects.robogrid.android.data.grid;

import com.jleth.projects.robogrid.android.model.Location;
import com.jleth.projects.robogrid.android.model.Size;
import com.jleth.projects.robogrid.core.Grid;
import com.jleth.projects.robogrid.core.GridFactory;
import com.jleth.projects.robogrid.core.model.Direction;
import com.jleth.projects.robogrid.core.model.InvalidPositionException;
import com.jleth.projects.robogrid.core.model.Position;
import com.jleth.projects.robogrid.core.model.Rotation;
import com.jleth.projects.robogrid.core.model.Step;

/**
 * Implementation if {@link GridController} using {@link com.jleth.projects.robogrid.core.Grid} as engine
 */
public class CoreGridController implements GridController {

    private final Grid mGrid;

    public static CoreGridController createInstance(Size size){
        return new CoreGridController(size);
    }

    private CoreGridController(Size size) {
        mGrid = new GridFactory().createGridRect(size.getWidth(), size.getHeight());
    }

    @Override
    public boolean setStartLocation(Location location) {
        try {
            mGrid.init(new Position(location.getX(), location.getY(), convertDirection(location.getDirection())));
            return true;
        } catch (InvalidPositionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void moveForward() {
        mGrid.move(Step.FORWARD);
    }

    @Override
    public void turnRight() {
        mGrid.rotate(Rotation.RIGHT);
    }

    @Override
    public void turnLeft() {
        mGrid.rotate(Rotation.LEFT);
    }

    @Override
    public Location getCurrentLocation() {
        Position pos = mGrid.getCurrentPosition();
        return new Location(pos.getX(), pos.getY(), convertDirection(pos.getDirection()));
    }

    @Override
    public Size getGridSize() {
        int cols = mGrid.getColumnCount();
        int rows = mGrid.getRowCount();
        return new Size(rows, cols);
    }

    @com.jleth.projects.robogrid.android.model.Direction.Constant
    private int convertDirection(Direction dir) {
        switch (dir) {
            case NORTH:
                return com.jleth.projects.robogrid.android.model.Direction.NORTH;
            case SOUTH:
                return com.jleth.projects.robogrid.android.model.Direction.SOUTH;
            case EAST:
                return com.jleth.projects.robogrid.android.model.Direction.EAST;
            case WEST:
                return com.jleth.projects.robogrid.android.model.Direction.WEST;
        }
        return com.jleth.projects.robogrid.android.model.Direction.UNKNOWN;
    }

    private Direction convertDirection(@com.jleth.projects.robogrid.android.model.Direction.Constant int dir) {
        switch (dir) {
            case com.jleth.projects.robogrid.android.model.Direction.EAST:
                return Direction.EAST;
            case com.jleth.projects.robogrid.android.model.Direction.NORTH:
                return Direction.NORTH;
            case com.jleth.projects.robogrid.android.model.Direction.WEST:
                return Direction.WEST;
            case com.jleth.projects.robogrid.android.model.Direction.SOUTH:
                return Direction.SOUTH;
        }
        return null;
    }
}
