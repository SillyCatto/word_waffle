package core;

import javax.swing.*;

public abstract class Screen extends JPanel{
    protected Window window;

    public enum ScreenType {
        WELCOME,
        HELP,
        ABOUT,
        GAMEPLAY,
        WIN,
        LOSE
    }


    public Screen(Window window) {
        this.window = window;
    }

    protected abstract void initialize();
    protected abstract void update();
}
