package core;

import utils.PositionCounter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinScreen extends Screen {
    private JButton replay;
    private JLabel scoreLabel;

    public WinScreen(Window window){
        super(window);
        initialize();
        update();
    }

    @Override
    public void initialize() {
        JLabel winLabel = new JLabel("You Won!");

        scoreLabel = new JLabel();

        replay = new JButton("Replay?");

        this.add(winLabel);
        this.add(scoreLabel);
        this.add(replay);
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
    }

    // calculate the score based on the number of tries
    public void setScore() {
        int guessLevel = PositionCounter.getRow();
        int score = switch (guessLevel){
          case 0 -> 100;
          case 1 -> 80;
          case 2 -> 60;
          case 3 -> 40;
          case 4 -> 20;
          default -> throw new IllegalStateException("Illegal guess level: " +
                  guessLevel);
        };

        this.scoreLabel.setText("Score: " + score);
    }
}
