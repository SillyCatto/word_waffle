package core;

import utils.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutScreen extends Screen {
    private JButton goBack;
    private Image creditImage;
    public AboutScreen(Window window) {
        super(window);
        initialize();
        update();
    }

    @Override
    public void initialize() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));

        creditImage = new ImageIcon("./src/main/java/resources/screen_about.png").getImage();

        ImageIcon crossIcon = new ImageIcon("./src/main/java/resources/btn_cross.png");
        goBack = Button.createButton(crossIcon);

        this.add(goBack);
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
        super.paintComponent( g );
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(creditImage, 0, 0, null);
    }

}