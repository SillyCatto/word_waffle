package core;

import utils.PositionCounter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinScreen extends Screen {
    private JButton replay;
    private JButton goToMain;
    private int score;

    public WinScreen(Window window){
        super(window);
        initialize();
        update();
    }

    @Override
    public void initialize() {
        JLabel winLabel = new JLabel("You Won!");
        replay = new JButton("Replay?");
        goToMain = new JButton("Go To Main Window");

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

        this.add(winLabel);
        this.add(replay);
        this.add(goToMain);
        this.add(winScore);
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
}
