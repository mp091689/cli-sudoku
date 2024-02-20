package com.mp091689.clisudoku;

public class Printer {
    public static final String RESET = "\033[0m";
    public static final String BLACK = "\033[30m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String MAGENTA = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";
    public static final String BG_BLACK = "\033[40m";
    public static final String BG_RED = "\033[41m";
    public static final String BG_GREEN = "\033[42m";
    public static final String BG_YELLOW = "\033[43m";
    public static final String BG_BLUE = "\033[44m";
    public static final String BG_MAGENTA = "\033[45m";
    public static final String BG_CYAN = "\033[46m";
    public static final String BG_WHITE = "\033[47m";
    public static final String BOLD = "\033[1m";
    public static final String ITALIC = "\033[3m";
    public static final String UNDERLINE = "\033[4m";
    public static final String STRIKE = "\033[9m";
    private String text;

    public Printer(String text) {
        this.text = text;
    }

    public Printer(Character text) {
        this.text = String.valueOf(text);
    }

    public Printer green() {
        text = GREEN + text;
        return this;
    }

    public Printer yellow() {
        text = YELLOW + text;
        return this;
    }

    public Printer black() {
        text = BLACK + text;
        return this;
    }

    public Printer red() {
        text = RED + text;
        return this;
    }

    public Printer blue() {
        text = BLUE + text;
        return this;
    }

    public Printer magenta() {
        text = MAGENTA + text;
        return this;
    }

    public Printer cyan() {
        text = CYAN + text;
        return this;
    }

    public Printer white() {
        text = WHITE + text;
        return this;
    }

    public Printer bg_green() {
        text = BG_GREEN + text;
        return this;
    }

    public Printer bg_black() {
        text = BG_BLACK + text;
        return this;
    }

    public Printer bg_red() {
        text = BG_RED + text;
        return this;
    }

    public Printer bg_blue() {
        text = BG_BLUE + text;
        return this;
    }

    public Printer bg_white() {
        text = BG_WHITE + text;
        return this;
    }

    public Printer bg_cyan() {
        text = BG_CYAN + text;
        return this;
    }

    public Printer bg_magenta() {
        text = BG_MAGENTA + text;
        return this;
    }

    public Printer bg_yellow() {
        text = BG_YELLOW + text;
        return this;
    }

    public Printer bold() {
        text = BOLD + text;
        return this;
    }

    public Printer strike() {
        text = STRIKE + text;
        return this;
    }

    public Printer underline() {
        text = UNDERLINE + text;
        return this;
    }

    public Printer italic() {
        text = ITALIC + text;
        return this;
    }

    @Override
    public String toString() {
        return text + RESET;
    }
}
