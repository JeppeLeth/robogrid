package com.leth.projects.robogrid.console;

import com.jleth.projects.robogrid.core.Grid;
import com.jleth.projects.robogrid.core.model.Position;

/**
 * Common functionality for handling commandline input
 */
public abstract class CommandHandlerBase implements CommandHandler {
    protected void showSetupDisplay() {
        System.out.println("============================");
        System.out.println("| First we need to setup   |");
        System.out.println("| The size of the grid     |");
        System.out.println("============================");
    }

    protected void showInitDisplay() {
        System.out.println("\n============================");
        System.out.println("| Now set the initial      |");
        System.out.println("| values for start point   |");
        System.out.println("============================");
    }

    protected void showReadyDisplay() {
        System.out.println("\n============================");
        System.out.println("|  Now you are ready  !!!  |");
        System.out.println("============================");
    }

    protected static void reportPosition(Grid grid) {
        Position pos = grid.getCurrentPosition();
        System.out.printf(" The current position is: %d %d %s\n", pos.getX(), pos.getY(), pos.getDirection().name().charAt(0));
    }
}
