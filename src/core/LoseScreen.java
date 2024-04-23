package core;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoseScreen extends Screen {
    private JButton replay;
    private JButton goToMain;
    protected JLabel answerLabel;

    public LoseScreen(Window window){
        super(window);
        initialize();
        update();
    }


    @Override
    public void initialize() {
        JLabel winLabel = new JLabel("You Lost :(");
        JLabel LosingScore=new JLabel("Your Score is 0");
        answerLabel = new JLabel();
        replay = new JButton("Replay?");
        goToMain=new JButton("Go To Main Menu");

        this.add(answerLabel);
        this.add(winLabel);
        this.add(replay);
        this.add(goToMain);
        this.add(LosingScore);

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
