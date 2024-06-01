package core;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{
    private final JPanel containerPanel;
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

        // set app icon
        ImageIcon icon = new ImageIcon("./src/main/java/resources/icon.png");
        this.setIconImage(icon.getImage());

        // Create custom cursor and set custom cursor to the JFrame
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImage = toolkit.getImage("./src/main/java/resources/cursor.png");
        Image scaledCursorImg = cursorImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        Cursor customCursor = toolkit.createCustomCursor(scaledCursorImg,
                new Point(0, 0), "Cursor");
        setCursor(customCursor);

        // initialize the screens
        welcomeScreen = new WelcomeScreen(this);
        gameScreen = new GameScreen(this);
        helpScreen = new HelpScreen(this);
        aboutScreen = new AboutScreen(this);
        winScreen = new WinScreen(this);
        loseScreen = new LoseScreen(this);

        // the main panel which contains other panels
        containerPanel = new JPanel();

        cardLayout = new CardLayout();
        containerPanel.setLayout(cardLayout);
        containerPanel.add(welcomeScreen, Screen.ScreenType.WELCOME.toString());
        containerPanel.add(gameScreen, Screen.ScreenType.GAMEPLAY.toString());
        containerPanel.add(helpScreen, Screen.ScreenType.HELP.toString());
        containerPanel.add(aboutScreen, Screen.ScreenType.ABOUT.toString());
        containerPanel.add(winScreen, Screen.ScreenType.WIN.toString());
        containerPanel.add(loseScreen, Screen.ScreenType.LOSE.toString());

        this.add(containerPanel);

        // set first screen to welcome
        this.changeScreen(Screen.ScreenType.WELCOME);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // change screen based on the type
    protected void changeScreen(Screen.ScreenType screen) {
        String screenID = switch (screen) {
            case WELCOME -> Screen.ScreenType.WELCOME.toString();
            case GAMEPLAY -> Screen.ScreenType.GAMEPLAY.toString();
            case HELP -> Screen.ScreenType.HELP.toString();
            case ABOUT -> Screen.ScreenType.ABOUT.toString();
            case WIN -> Screen.ScreenType.WIN.toString();
            case LOSE -> Screen.ScreenType.LOSE.toString();
        };
        cardLayout.show(containerPanel, screenID);
    }
}

