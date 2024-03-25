package core;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinScreen extends Screen {
    private JButton replay;

    public WinScreen(Window window){
        super(window);
        initialize();
        update();
    }

    @Override
    public void initialize() {
        JLabel winLabel = new JLabel("You Won!");
        replay = new JButton("Replay?");
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
