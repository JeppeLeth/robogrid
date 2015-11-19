package com.jleth.projects.robogrid.core;

import com.jleth.projects.robogrid.core.model.InvalidPositionException;
import com.jleth.projects.robogrid.core.model.Position;
import com.jleth.projects.robogrid.core.model.Rotation;
import com.jleth.projects.robogrid.core.model.Step;

/**
 * Control interface for moving object in grid
 */
public interface Grid {

    /**
     * Set the starting position of the object.
     *
     * @param position coordinate and facing direction
     * @throws InvalidPositionException if position is out of grid bounds
     */
    void init(Position position) throws InvalidPositionException;

    /**
     * Rotate objects facing position
     *
     * @param rotation degree
     * @return true if rotation was executed, otherwise false
     */
    boolean rotate(Rotation rotation);

    /**
     * Move objects position coordinate
     *
     * @param step step
     * @return true if move was executed, otherwise false
     */
    boolean move(Step step);

    /**
     * Get the current position of the object.
     *
     * @return the current coordinate and facing direction
     */
    Position getCurrentPosition();

    /**
     * Number of columns. Contains X coordinates from from 0 to width - 1
     *
     * @return number of columns
     */
    int getColumnCount();


    /**
     * Number of rows. Contains Y coordinates from from 0 to height - 1
     *
     * @return number of rows
     */
    int getRowCount();
}
