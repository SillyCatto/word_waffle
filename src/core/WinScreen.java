package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.PositionCounter;

public class WinScreen extends Screen {
    private JButton replay;
    private JButton goToMain;
    private int score;
    private ImageIcon backgroundImage;

    public WinScreen(Window window) {
        super(window);
        initialize();
        update();
        // Load background image
        backgroundImage = new ImageIcon("src/resources/Winning.gif");
    }


    @Override
    public void initialize() {
        //setLayout(new BorderLayout()); // Set layout to BorderLayout to use the background

        // Create components
        JLabel winLabel = new JLabel("You Won!");
        replay = new JButton("Replay?");
        goToMain = new JButton("Go To Main Window");

        winLabel.setOpaque(false);
        replay.setOpaque(false);
        goToMain.setOpaque(false);


        // Calculate the score based on guesses
        int guesses = PositionCounter.getRow();
        switch (guesses) {
            case 0:
                score = 100;
                break;
            case 1:
                score = 80;
                break;
            case 2:
                score = 60;
                break;
            case 3:
                score = 40;
                break;
            case 4:
                score = 20;
                break;
            default:
                score = 0; // Failure
                break;
        }
        JLabel winScore = new JLabel("Your Score is " + score);

        // Add components to the panel
        add(winLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(replay);
        buttonPanel.add(goToMain);
        add(buttonPanel, BorderLayout.CENTER);
        add(winScore, BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        replay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // go back to game screen
                window.changeScreen(ScreenType.GAMEPLAY);
                // clear the grid
                window.gameScreen.reset(window.gameScreen.getGrid());
                // generate new word
                window.gameScreen.getWordListFile().generateWord();
            }
        });
        goToMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.changeScreen(ScreenType.WELCOME);
            }
        });

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
