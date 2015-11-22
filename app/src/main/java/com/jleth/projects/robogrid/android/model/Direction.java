package com.jleth.projects.robogrid.android.model;

import android.support.annotation.IntDef;

public interface Direction {
    int NORTH = 0;
    int EAST = 1;
    int SOUTH = 2;
    int WEST = 3;
    int UNKNOWN = -1;

    @IntDef({Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UNKNOWN})
    @interface Constant {
    }
}
