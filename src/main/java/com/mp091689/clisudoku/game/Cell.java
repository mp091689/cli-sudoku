package com.mp091689.clisudoku.game;

public class Cell {
    public static final char EMPTY = '_';
    private final boolean locked;
    private char value;

    public Cell(char value) {
        this.value = value;
        this.locked = value != EMPTY;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        if (!isLocked()) {
            this.value = value;
        }
    }

    public boolean isLocked() {
        return locked;
    }

    public void erase() {
        setValue(EMPTY);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
