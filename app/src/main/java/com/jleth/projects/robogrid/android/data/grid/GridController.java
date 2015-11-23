package com.jleth.projects.robogrid.android.data.grid;

import com.jleth.projects.robogrid.android.model.Location;
import com.jleth.projects.robogrid.android.model.Size;

/**
 * App facade for interacting with a grid engine
 */
public interface GridController {
    boolean setStartLocation(Location location);

    void moveForward();

    void turnRight();

    void turnLeft();

    Location getCurrentLocation();

    Size getGridSize();
}
