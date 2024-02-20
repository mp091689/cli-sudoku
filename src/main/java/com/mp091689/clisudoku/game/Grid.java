package com.mp091689.clisudoku.game;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.mp091689.clisudoku.Cursor;
import com.mp091689.clisudoku.Printer;

import java.util.*;

public class Grid implements NativeKeyListener {
    private final Cursor cursor;
    private final List<Character> VALUES = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');
    private final Cell[][] grid = new Cell[9][9];

    public Grid(Level level) {
        cursor = new Cursor(0, 8, 0, 8, 0, 8);
        Collections.shuffle(VALUES);
        initialize();
        switch (level) {
            case EASY -> erase(81 - Level.EASY.getValue());
            case MEDIUM -> erase(81 - Level.MEDIUM.getValue());
            case HARD -> erase(81 - Level.HARD.getValue());
            case EXPERT -> erase(81 - Level.EXPERT.getValue());
        }
    }


    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        switch (e.getKeyCode()) {
            case 57416 -> cursor.up();
            case 57424 -> cursor.down();
            case 57419 -> cursor.left();
            case 57421 -> cursor.right();
            case 2, 3, 4, 5, 6, 7, 8, 9, 10 -> cell().setValue(String.valueOf(e.getKeyCode() - 1).charAt(0));
            case 57 -> cell().erase();
        }
        print();
    }

    public Cell cell() {
        return grid[cursor.y()][cursor.x()];
    }

    public void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String c = String.valueOf(Cell.EMPTY);
                if (grid[i][j] != null) {
                    c = grid[i][j].toString();
                }
                Printer p = (new Printer(" " + c + " ")).bold();
                if (cursor.x() == j && cursor.y() == i) {
                    p.bg_green().black();
                } else if (grid[i][j].isLocked()) {
                    p.cyan();
                } else {
                    p.magenta();
                }

                System.out.print(p);
                if ((j + 1) % 3 == 0 && j < 8) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if ((i + 1) % 3 == 0 && i < 8) {
                System.out.println("---------+---------+---------");
            }
        }
        System.out.println();
    }

    public boolean isValid() {
        for (int i = 0; i < 8; i++) {
            if (!isValidRow(i) || !isValidCol(i)) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isValidBlock(i * 3, j * 3)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasEmpty() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == null || grid[i][j].getValue() == Cell.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidRow(int row) {
        List<Character> values = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] != null) {
                if (values.contains(grid[row][i].getValue())) {
                    return false;
                }
                values.add(grid[row][i].getValue());
            }

        }
        return true;
    }

    private boolean isValidCol(int col) {
        List<Character> values = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] != null) {
                if (values.contains(grid[i][col].getValue())) {
                    return false;
                }
                values.add(grid[i][col].getValue());
            }
        }
        return true;
    }

    private boolean isValidBlock(int x, int y) {
        List<Character> values = new ArrayList<>();

        int offsetX = (x / 3) * 3;
        int offsetY = (y / 3) * 3;

        for (int i = offsetX; i < offsetX + 3; i++) {
            for (int j = offsetY; j < offsetY + 3; j++) {
                if (grid[i][j] != null) {
                    if (values.contains(grid[i][j].getValue())) {
                        return false;
                    }
                    values.add(grid[i][j].getValue());
                }
            }
        }
        return true;
    }

    private boolean initialize() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (grid[x][y] != null) {
                    continue;
                }

                for (char value : VALUES) {
                    grid[x][y] = new Cell(value);

                    if (!isValidRow(x) || !isValidCol(y) || !isValidBlock(x, y)) {
                        grid[x][y] = null;
                        continue;
                    }

                    if (initialize()) {
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

    private void erase(int cellsToErase) {
        Random r = new Random();

        int counter = 0;
        while (counter < cellsToErase) {
            int x = r.nextInt(9);
            int y = r.nextInt(9);
            if (grid[x][y].isLocked()) {
                grid[x][y] = new Cell(Cell.EMPTY);
                counter++;
            }
        }
    }
}
