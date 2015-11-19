package com.leth.projects.robogrid.console;

import com.jleth.projects.robogrid.core.Grid;
import com.leth.projects.robogrid.console.CommandHandlerBatchInput;
import com.leth.projects.robogrid.console.CommandHandlerSingleLineInput;

public class Main {

    public static void main(String[] args) {

        System.out.println("============================");
        System.out.println("|    ROBOT GRID CONSOLE    |");
        System.out.println("============================\n");


        System.out.println("============================");
        System.out.println("|        Welcome           |");
        System.out.println("============================\n");

        System.out.println("============================");
        System.out.println("|  How do you prefer to    |");
        System.out.println("|  enter values in the     |");
        System.out.println("|  console ?               |");
        System.out.println("|   S. One value per line  |");
        System.out.println("|   B. More values per line|");
        System.out.println("============================\n");

        CommandHandler handler = null;

        while (handler == null) {
            char type = Character.toUpperCase(ConsoleInputUtil.inChar(" Enter 'B' or 'S' : "));
            if (type == 'B') {
                handler = new CommandHandlerBatchInput();
            } else if (type == 'S') {
                handler = new CommandHandlerSingleLineInput();
            }
        }

        Grid grid = handler.setupGrid();
        handler.initGrid(grid);
        handler.handleMoves(grid);
    }
}

