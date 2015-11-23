package com.jleth.projects.robogrid.console;

import com.jleth.projects.robogrid.core.Grid;

/**
 * General command handler for output and input from the console to
 */
public interface CommandHandler {

    Grid setupGrid();

    void initGrid(Grid grid);

    void handleMoves(Grid grid);

}
