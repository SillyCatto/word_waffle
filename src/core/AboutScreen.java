package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutScreen extends Screen {
    private JButton goBack;
    public Image BackgroundImage;
    public AboutScreen(Window window) {
        super(window);
        initialize();
        update();
        BackgroundImage = new ImageIcon("./src/resources/Credit.png").getImage();

    }

    @Override
    public void initialize() {

        //adding the background image

        //JLabel label = new JLabel("Help screen");
        setLayout(new BorderLayout());
        goBack = new JButton("Go back");

        //change the font of the button
        Font buttonFont = new Font("KG Primary Penmanship", Font.PLAIN, 16);
        goBack.setFont(buttonFont);

        // this.add(label);
        this.add(goBack,BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.changeScreen(ScreenType.WELCOME);
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(BackgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

}