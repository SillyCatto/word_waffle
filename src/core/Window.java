package core;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{
    private static Window instance = null;
    private static Screen currentScreen = null;

    private final JPanel mainPanel;

    CardLayout cl;

    private Window(){
        // init app frame, set app icon, title and size
        int width = 550;
        int height = 700;
        this.setSize(width, height);
        this.setTitle("Word Waffle");

        ImageIcon icon = new ImageIcon("./src/resources/icon.png");
        this.setIconImage(icon.getImage());

        WelcomeScreen welcomeScreen = WelcomeScreen.get();
        GameScreen gameScreen = GameScreen.get();


        mainPanel = new JPanel();

        cl = new CardLayout();
        mainPanel.setLayout(cl);
        mainPanel.add(welcomeScreen, "1");
        mainPanel.add(gameScreen, "2");


        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(mainPanel);
    }

    public static Window get(){
        if (instance == null){
            instance = new Window();
        }
        return instance;
    }

    public void run(){
        // set default screen to welcome screen
        Window.changeScreen(Screen.Type.WELCOME);

        Window.get().setVisible(true);
    }

    // change screen based on the type
    public static void changeScreen(Screen.Type type){
        switch (type){
            case WELCOME:
                currentScreen = WelcomeScreen.get();
                Window.get().cl.show(get().mainPanel, "1");
                currentScreen.update(Window.get());
                break;
            case GAMEPLAY:
                currentScreen = GameScreen.get();
                Window.get().cl.show(get().mainPanel, "2");
                currentScreen.update(Window.get());

                break;
            default:
                System.err.println("Invalid screen type");
                break;
        }

        // change component hierarchy in the frame
        Window.get().revalidate();

        //Place the panel in the center of the frame.
        Window.get().setLocationRelativeTo(null);

        // redraw the frame
        Window.get().repaint();
//        if (type == Screen.Type.GAMEPLAY){
//            Window.get().addKeyBoardListener();
//        }
    }
}

