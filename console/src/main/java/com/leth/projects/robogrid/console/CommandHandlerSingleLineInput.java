package com.leth.projects.robogrid.console;

import com.jleth.projects.robogrid.core.Grid;
import com.jleth.projects.robogrid.core.GridFactory;
import com.jleth.projects.robogrid.core.model.Direction;
import com.jleth.projects.robogrid.core.model.InvalidPositionException;
import com.jleth.projects.robogrid.core.model.Position;
import com.jleth.projects.robogrid.core.model.Rotation;
import com.jleth.projects.robogrid.core.model.Step;

import java.util.Locale;

/**
 * Handle one input value per line.
 */
public class CommandHandlerSingleLineInput extends CommandHandlerBase {
    @Override
    public Grid setupGrid() {
        showSetupDisplay();
        int cols = ConsoleInputUtil.inInt(" Enter number of columns: ");
        int rows = ConsoleInputUtil.inInt(" Enter number of rows: ");

        Grid grid = new GridFactory().createGridRect(cols, rows);
        return grid;
    }

    @Override
    public void initGrid(Grid grid) {
        showInitDisplay();

        Direction dir = null;
        boolean initialized = false;
        while (!initialized) {
            int x = ConsoleInputUtil.inInt(
                    String.format(Locale.ENGLISH, " Enter X coordinate (0-%d): ", grid.getColumnCount() - 1));
            int y = ConsoleInputUtil.inInt(
                    String.format(Locale.ENGLISH, " Enter Y coordinate (0-%d): ", grid.getRowCount() - 1));

            char c = ConsoleInputUtil.inChar(" Enter facing direction (N, S, E or W): ");
            switch (Character.toUpperCase(c)) {
                case 'N':
                    dir = Direction.NORTH;
                    break;
                case 'S':
                    dir = Direction.SOUTH;
                    break;
                case 'E':
                    dir = Direction.EAST;
                    break;
                case 'W':
                    dir = Direction.WEST;
                    break;
            }
            try {
                grid.init(new Position(x, y, dir));
                initialized = true;
            } catch (InvalidPositionException e) {
                System.out.println(" -- The position you selected is not valid -- ");
            }
        }

    }

    @Override
    public void handleMoves(Grid grid) {
        showReadyDisplay();

        System.out.println("| Options:                 |");
        System.out.println("|        R. Turn right     |");
        System.out.println("|        L. Turn left      |");
        System.out.println("|        F. Move forward   |");
        System.out.println("|        - - - - - - - - - |");
        System.out.println("|        P. Report position|");
        System.out.println("|        Q. Quit           |");
        System.out.println("============================");

        char inputChar = 0;
        while (inputChar != 'Q') {
            inputChar = ConsoleInputUtil.inChar(" Enter next option: ");
            inputChar = Character.toUpperCase(inputChar);
            switch (inputChar) {
                case 'R':
                    grid.rotate(Rotation.RIGHT);
                    break;
                case 'L':
                    grid.rotate(Rotation.LEFT);
                    break;
                case 'F':
                    grid.move(Step.FORWARD);
                    break;
                case 'P':
                    reportPosition(grid);
                    break;
                case 'Q':
                    System.out.println(" Quit system");
                    break;
                default:
                    System.out.println(" Invalid option");
                    break;
            }
        }
    }
}
