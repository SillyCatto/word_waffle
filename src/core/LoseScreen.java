package core;

import utils.Button;
import utils.FontManager;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoseScreen extends Screen {
    private JButton replayBtn;
    private JButton quitBtn;
    protected JLabel answerLabel;
    private Image loseImage;

    public LoseScreen(Window window){
        super(window);
        initialize();
        update();
    }


    @Override
    public void initialize() {



        loseImage = new ImageIcon("src/resources/lose_screen.gif").getImage();

        ImageIcon replayIcon = new ImageIcon("./src/resources/btn_replay.png");
        replayBtn = Button.createButton(replayIcon);
        replayBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon quitIcon = new ImageIcon("./src/resources/btn_quit.png");
        quitBtn = Button.createButton(quitIcon);
        quitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        //to show the correct answer
        Font KGPrimary = FontManager.loadFont(
                "./src/resources/KGPrimaryPenmanship.ttf",
                Font.BOLD, 25f
        );
        answerLabel = new JLabel();
        answerLabel.setFont(KGPrimary);


        this.add(replayBtn);
        this.add(quitBtn);
        this.add(answerLabel);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        //gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(350, 0, 0, 0);
        add(answerLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 0, 0);
        add(replayBtn, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 0, 0);
        add(quitBtn, gbc);

    }

    @Override
    public void update() {
        replayBtn.addActionListener(new ActionListener() {
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

        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // release all resources and close the app
                window.dispose();
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(loseImage, 0, 0, getWidth(), getHeight(), this);
    }
}


