package com.jleth.projects.robogrid.core.model;

/**
 * Thrown to indicate that a method has been passed an illegal or
 * inappropriate coordinates.
 *
 */
public class InvalidPositionException extends Exception {

    public InvalidPositionException(String message) {
        super(message);
    }
}
