package core;

import javax.swing.*;

public abstract class Screen extends JPanel{
    protected Window window;

    public enum ScreenType {
        WELCOME,
        HELP,
        ABOUT,
        GAMEPLAY,
//        HINT,
        WIN,
        LOSE
    }


    public Screen(Window window) {
        this.window = window;
    }

    public abstract void initialize();
    public abstract void update();
}
