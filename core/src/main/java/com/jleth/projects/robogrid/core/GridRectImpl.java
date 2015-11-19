package com.jleth.projects.robogrid.core;

import com.jleth.projects.robogrid.core.model.Direction;
import com.jleth.projects.robogrid.core.model.InvalidPositionException;
import com.jleth.projects.robogrid.core.model.Position;
import com.jleth.projects.robogrid.core.model.Rotation;
import com.jleth.projects.robogrid.core.model.Step;

/**
 * Rectangular {@link Grid} implantation
 */
class GridRectImpl implements Grid {

    private int left;
    private int top;
    private int right;
    private int bottom;

    private boolean initialized;
    private int objectX;
    private int objectY;
    private int objectDirDegree;

    public GridRectImpl(int cols, int rows) {
        setSize(cols, rows);
    }

    private void setSize(int width, int height) {
        if (width > 0) {
            right = width;
        } else {
            throw new IllegalArgumentException("Width must be at least 1");
        }
        if (height > 0) {
            bottom = height;
        } else {
            throw new IllegalArgumentException("Height must be at least 1");
        }
    }

    @Override
    public boolean rotate(Rotation rotation) {
        checkInitialized();
        if (rotation == null) {
            return false;
        }
        int turnDegree = (objectDirDegree + rotation.getDegree()) % 360;
        objectDirDegree = turnDegree < 0 ? turnDegree + 360 : turnDegree;
        return true;
    }

    @Override
    public boolean move(Step step) {
        checkInitialized();
        if (step == null) {
            return false;
        }

        int newX, newY;
        switch (getCurrentDirection()) {
            case NORTH:
                newX = objectX;
                newY = objectY - step.getIncrease();
                break;
            case SOUTH:
                newX = objectX;
                newY = objectY + step.getIncrease();
                break;
            case EAST:
                newX = objectX + step.getIncrease();
                newY = objectY;
                break;
            case WEST:
                newX = objectX - step.getIncrease();
                newY = objectY;
                break;
            default:
                throw new IllegalArgumentException("Current direction not valid: " + getCurrentDirection());
        }

        if (contains(newX, newY)) {
            objectX = newX;
            objectY = newY;
        }

        return false;
    }

    private Direction getCurrentDirection() {
        if (objectDirDegree >= 0 && objectDirDegree < 90) {
            return Direction.NORTH;
        } else if (objectDirDegree >= 90 && objectDirDegree < 180) {
            return Direction.WEST;
        } else if (objectDirDegree >= 180 && objectDirDegree < 270) {
            return Direction.SOUTH;
        } else if (objectDirDegree >= 270 && objectDirDegree < 360) {
            return Direction.EAST;
        }

        throw new IllegalArgumentException("Current direction degree not valid: " + objectDirDegree);
    }

    @Override
    public void init(Position position) throws InvalidPositionException {
        if (initialized) {
            throw new IllegalStateException("Grid already initialized");
        }
        if (position == null || position.getDirection() == null || !contains(position.getX(), position.getY())) {
            throw new InvalidPositionException("Position is invalid");
        }

        objectX = position.getX();
        objectY = position.getY();

        switch (position.getDirection()) {
            case EAST:
                objectDirDegree += 90;
            case SOUTH:
                objectDirDegree += 90;
            case WEST:
                objectDirDegree += 90;
            case NORTH:
                /* no-op */
        }

        initialized = true;

    }

    private void checkInitialized() {
        if (!initialized) {
            throw new IllegalStateException("Grid not initialized yet. Call init(Position) first");
        }
    }

    @Override
    public Position getCurrentPosition() {
        checkInitialized();
        return new Position(objectX, objectY, getCurrentDirection());
    }

    @Override
    public int getColumnCount() {
        return right - left;
    }

    @Override
    public int getRowCount() {
        return bottom - top;
    }

    public boolean contains(int x, int y) {
        return left < right && top < bottom && x >= left && x < right && y >= top && y < bottom;
    }
}
