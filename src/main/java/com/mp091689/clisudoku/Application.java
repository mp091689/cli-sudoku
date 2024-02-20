package com.mp091689.clisudoku;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class Application {

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        Menu menu = new Menu();
        menu.print();

        GlobalScreen.addNativeKeyListener(menu);
    }
}
