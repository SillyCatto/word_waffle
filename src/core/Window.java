package core;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{
    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    protected final WelcomeScreen welcomeScreen;
    protected final GameScreen gameScreen;
    protected final HelpScreen helpScreen;
    protected final AboutScreen aboutScreen;
    protected final WinScreen winScreen;
    protected final LoseScreen loseScreen;


    public Window(){
        // init app frame, set app icon, title and size
        int width = 550;
        int height = 700;
        this.setSize(width, height);
        this.setTitle("Word Waffle");

        ImageIcon icon = new ImageIcon("./src/resources/icon.png");
        this.setIconImage(icon.getImage());

        // initialize the screens
        welcomeScreen = new WelcomeScreen(this);
        gameScreen = new GameScreen(this);
        helpScreen = new HelpScreen(this);
        aboutScreen = new AboutScreen(this);
        winScreen = new WinScreen(this);
        loseScreen = new LoseScreen(this);

        // the main panel which contains other panels
        mainPanel = new JPanel();

        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(welcomeScreen, Screen.ScreenType.WELCOME.toString());
        mainPanel.add(gameScreen, Screen.ScreenType.GAMEPLAY.toString());
        mainPanel.add(helpScreen, Screen.ScreenType.HELP.toString());
        mainPanel.add(aboutScreen, Screen.ScreenType.ABOUT.toString());
        mainPanel.add(winScreen, Screen.ScreenType.WIN.toString());
        mainPanel.add(loseScreen, Screen.ScreenType.LOSE.toString());

        this.add(mainPanel);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // set first screen to welcome
        this.changeScreen(Screen.ScreenType.WELCOME);
    }

    // change screen based on the type
    public void changeScreen(Screen.ScreenType screen) {
        String screenID = switch (screen) {
            case WELCOME -> Screen.ScreenType.WELCOME.toString();
            case GAMEPLAY -> Screen.ScreenType.GAMEPLAY.toString();
            case HELP -> Screen.ScreenType.HELP.toString();
            case ABOUT -> Screen.ScreenType.ABOUT.toString();
            case WIN -> Screen.ScreenType.WIN.toString();
            case LOSE -> Screen.ScreenType.LOSE.toString();
        };
        cardLayout.show(mainPanel, screenID);
    }
}

