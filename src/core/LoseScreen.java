package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoseScreen extends Screen {
    private JButton replay;
    private JButton goToMain;
    protected JLabel answerLabel;
    private JLabel loseBg;

    public LoseScreen(Window window){
        super(window);
        initialize();
        update();
    }


    @Override
    public void initialize() {
        ImageIcon backgroundImage = new ImageIcon("src/resources/LoseScreen.gif");
        loseBg = new JLabel(backgroundImage);
        loseBg.setLayout(new BorderLayout(0,0));
        JLabel winLabel = new JLabel("You Lost :(");

        answerLabel = new JLabel();
        replay = new JButton("Replay?");
        goToMain=new JButton("Go To Main Menu");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));

        panel.setOpaque(false);
        panel.add(answerLabel);
        panel.add(replay);
        panel.add(goToMain);

        loseBg.add(panel, BorderLayout.CENTER);

        this.add(loseBg);


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