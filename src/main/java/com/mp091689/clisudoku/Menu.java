package com.mp091689.clisudoku;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.mp091689.clisudoku.game.Grid;
import com.mp091689.clisudoku.game.Level;

import java.util.ArrayList;
import java.util.List;

public class Menu implements NativeKeyListener {
    private final Cursor cursor;
    private final List<String> mainMenu = List.of("New Game", "Continue", "Exit");
    private final List<String> newGameMenu = List.of("Easy", "Medium", "Hard", "Expert", "Main Menu");
    private List<String> options;
    private Grid sudoku;

    public Menu() {
        options = mainMenu;
        cursor = new Cursor(0, 0, 0, mainMenu.size() - 1, 0, 0);
    }

    public void print() {
        System.out.printf("%n".repeat(50));

        if (sudoku == null && options.isEmpty()) {
            options = mainMenu;
        }

        if (options.isEmpty()) {
            sudoku.print();
            if (sudoku.isValid() && sudoku.hasEmpty()) {
                Printer p = new Printer("!!!!!!!!!!!!!!!!YOU WIN!!!!!!!!!!!!!!!!");
                p.bg_red().black().bold().underline();
                System.out.println(p);
            }
            return;
        }

        for (int i = 0; i < options.size(); i++) {
            Printer p = new Printer(options.get(i));
            if (cursor.y() == i) {
                p.magenta();
            }
            System.out.println(p);
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        switch (nativeEvent.getKeyCode()) {
            case 1 -> exit();
            case 28 -> action();
            case 57416 -> cursor.up();
            case 57424 -> cursor.down();
        }
        print();
    }

    private void action() {
        if (options.equals(mainMenu)) {
            switch (cursor.y()) {
                case 0 -> loadMenu(newGameMenu);
                case 1 -> continueGame();
                case 2 -> System.exit(0);
            }
        } else if (options.equals(newGameMenu)) {
            switch (cursor.y()) {
                case 0 -> loadSudoku(Level.EASY);
                case 1 -> loadSudoku(Level.MEDIUM);
                case 2 -> loadSudoku(Level.HARD);
                case 3 -> loadSudoku(Level.EXPERT);
                case 4 -> loadMenu(mainMenu);
            }
        }
    }

    private void continueGame() {
        if (sudoku != null) {
            options = new ArrayList<>();
        }
    }

    private void loadMenu(List<String> menu) {
        cursor.setyMax(menu.size() - 1);
        options = menu;
    }

    private void loadSudoku(Level level) {
        options = new ArrayList<>();
        sudoku = new Grid(level);

        GlobalScreen.addNativeKeyListener(sudoku);
    }

    private void exit() {
        if (options.equals(mainMenu)) {
            continueGame();
        } else if (options.equals(newGameMenu) || sudoku != null) {
            loadMenu(mainMenu);
        }
    }
}
