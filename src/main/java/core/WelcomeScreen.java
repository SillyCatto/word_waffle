package core;

import utils.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends Screen {
    private JButton playBtn;
    private JButton helpBtn;
    private JButton aboutBtn;
    private Image welcomeScreenBg;

    public WelcomeScreen(Window window){
        super(window);
        initialize();
        update();
    }


    @Override
    protected void initialize() {
        welcomeScreenBg = new ImageIcon("./src/main/java/resources/screen_title.png").getImage();

        ImageIcon playIcon = new ImageIcon("./src/main/java/resources/btn_play.png");
        playBtn = Button.createButton(playIcon);

        ImageIcon helpIcon = new ImageIcon("./src/main/java/resources/btn_help.png");
        helpBtn = Button.createButton(helpIcon);

        ImageIcon aboutIcon = new ImageIcon("./src/main/java/resources/btn_about.png");
        aboutBtn = Button.createButton(aboutIcon);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(350));
        this.add(playBtn);
        this.add(Box.createVerticalStrut(30)); // add gaps between buttons
        this.add(helpBtn);
        this.add(Box.createVerticalStrut(30));
        this.add(aboutBtn);
    }


    @Override
    protected void update() {
        //handle button clicks

        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.changeScreen(ScreenType.GAMEPLAY);
            }
        });

        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.changeScreen(ScreenType.HELP);
            }
        });

        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.changeScreen(ScreenType.ABOUT);
            }
        });

    }


    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        g.drawImage(welcomeScreenBg, 0, 0, null);
    }

}
