package core;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoseScreen extends Screen {
    private JButton replay;
    protected JLabel answerLabel;
    public LoseScreen(Window window){
        super(window);
        initialize();
        update();
    }


    @Override
    public void initialize() {
        JLabel winLabel = new JLabel("You Lost :(");
        answerLabel = new JLabel();
        replay = new JButton("Replay?");
        this.add(answerLabel);
        this.add(winLabel);
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
}
