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
    private Image background;

    public WelcomeScreen(Window window){
        super(window);
        initialize();
        update();
    }


    @Override
    public void initialize() {
        background = new ImageIcon("./src/resources/screen_title.png").getImage();

        ImageIcon playIcon = new ImageIcon("./src/resources/btn_play.png");
        playBtn = Button.createButton(playIcon);

        ImageIcon helpIcon = new ImageIcon("./src/resources/btn_help.png");
        helpBtn = Button.createButton(helpIcon);

        ImageIcon aboutIcon = new ImageIcon("./src/resources/btn_about.png");
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
    public void update() {
        //handle button clicks
        // play button
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.changeScreen(ScreenType.GAMEPLAY);
            }
        });

        // help button
        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.changeScreen(ScreenType.HELP);
            }
        });

        // about button
        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.changeScreen(ScreenType.ABOUT);
            }
        });

    }

    // add background image to welcome screen
    @Override
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, null);
    }

}
