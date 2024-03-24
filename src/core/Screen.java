package core;

import javax.swing.*;

public abstract class Screen extends JPanel{
    public enum Type{
        WELCOME,
        HELP,
        ABOUT,
        GAMEPLAY,
        HINT,
        WIN,
        LOSE
    }

    public abstract void update(JFrame frame);
}
