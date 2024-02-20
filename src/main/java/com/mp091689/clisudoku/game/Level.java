package com.mp091689.clisudoku.game;

public enum Level {
    EASY(36), MEDIUM(30), HARD(24), EXPERT(18);

    private final int level;

    Level(int level) {
        this.level = level;
    }

    public int getValue() {
        return level;
    }
}
