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
 * Handle batches of input value per line.
 * This requires the user to be very accurate when entering keystrokes
 */
public class CommandHandlerBatchInput extends CommandHandlerBase {
    @Override
    public Grid setupGrid() {
        showSetupDisplay();

        Grid grid = null;
        while (grid == null) {
            try {
                String input = com.leth.projects.robogrid.console.ConsoleInputUtil.inString(" Enter number of columns and rows (e.g. '8 9'): ");
                String[] values = input.split(" ");
                int cols = Integer.valueOf(values[0]);
                int rows = Integer.valueOf(values[1]);
                grid = new GridFactory().createGridRect(cols, rows);
            } catch (IllegalArgumentException e) {
                System.out.println(" The entered values was not accepted. Please try again");
            } catch (Exception e) {
                System.out.println(" The entered values was not in the correct format. Please try again");
            }
        }
        return grid;
    }

    @Override
    public void initGrid(Grid grid) {
        showInitDisplay();
        boolean initialized = false;
        while (!initialized) {
            try {
                System.out.println(" Enter X and Y coordinate and direction");
                System.out.println(String.format(Locale.ENGLISH, " X coordinate (0-%d) - Y coordinate (0-%d) - Direction (N, S, E or W): ", grid.getColumnCount() - 1, grid.getRowCount() - 1));
                String input = com.leth.projects.robogrid.console.ConsoleInputUtil.inString(" Please enter values (e.g. '1 2 W'): ");
                String[] values = input.split(" ");
                int x = Integer.valueOf(values[0]);
                int y = Integer.valueOf(values[1]);
                char c = values[2].charAt(0);

                Direction dir = null;
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
                grid.init(new Position(x, y, dir));
                initialized = true;
            } catch (InvalidPositionException e) {
                System.out.println(" -- The position you selected is not valid -- ");
            } catch (IllegalArgumentException e) {
                System.out.println(" The entered values was not accepted. Please try again");
            } catch (Exception e) {
                System.out.println(" The entered values was not in the correct format. Please try again");
            }
        }

    }

    @Override
    public void handleMoves(Grid grid) {
        showReadyDisplay();

        System.out.println("| Command options:         |");
        System.out.println("|        R. Turn right     |");
        System.out.println("|        L. Turn left      |");
        System.out.println("|        F. Move forward   |");
        System.out.println("============================");

        boolean finished = false;
        while (!finished) {
            String input = ConsoleInputUtil.inString(" Enter series of commands: ");
            input = input.replaceAll("\\s", "").toUpperCase();
            if (input.matches("^[RLF]+$")) {
                for (int i = 0; i < input.length(); i++) {
                    switch (input.charAt(i)) {
                        case 'R':
                            grid.rotate(Rotation.RIGHT);
                            break;
                        case 'L':
                            grid.rotate(Rotation.LEFT);
                            break;
                        case 'F':
                            grid.move(Step.FORWARD);
                            break;
                        default: // Should not be able to happen
                            System.out.println(" Invalid option");
                            break;
                    }
                }
                reportPosition(grid);
                finished = true;
            } else {
                System.out.println(" The series of commands was inconsistent. Please try again.");
            }
        }
    }
}
