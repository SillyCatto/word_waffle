package core;

import utils.Button;
import utils.FontManager;
import utils.PositionCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinScreen extends Screen {
    private JButton replayBtn;
    private JButton quitBtn;
    private JLabel scoreLabel;
    private Image winScreenBg;

    public WinScreen(Window window){
        super(window);
        initialize();
        update();
    }

    @Override
    protected void initialize() {
        scoreLabel = new JLabel();

        Font KGPrimary = FontManager.loadFont(
                "./src/main/java/resources/KGPrimaryPenmanship.ttf",
                Font.BOLD, 45f
        );

        scoreLabel.setFont(KGPrimary);
        scoreLabel.setForeground(new Color(96, 56, 20));

        // Load background image
        winScreenBg = new ImageIcon("./src/main/java/resources/screen_win.gif").getImage();

        // create replay button
        ImageIcon replayIcon = new ImageIcon("./src/main/java/resources/btn_replay.png");
        replayBtn = Button.createButton(replayIcon);
        replayBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // create quit button
        ImageIcon quitIcon = new ImageIcon("./src/main/java/resources/btn_quit.png");
        quitBtn = Button.createButton(quitIcon);
        quitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // set layout to GridBagLayout for proper vertical alignment
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // set inset for scoreLabel, replayBtn and quitBtn
        gbc.insets = new Insets(245, 0, 0, 0);
        add(scoreLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(75, 0, 0, 0);
        add(replayBtn, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(25, 0, 0, 0);
        add(quitBtn, gbc);
    }

    @Override
    protected void update() {
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

    // calculate the score based on the number of tries
    protected void setScore() {
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

        this.scoreLabel.setText(String.valueOf(score));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(winScreenBg, 0, 0, getWidth(), getHeight(), this);
    }
}
