package core;

import utils.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpScreen extends Screen {
    private JButton goBackBtn;
    private Image helpScreenBg;

    public HelpScreen(Window window) {
        super(window);
        initialize();
        update();
    }

    @Override
    protected void initialize() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));

        helpScreenBg = new ImageIcon("./src/main/java/resources/screen_help.png").getImage();

        ImageIcon crossIcon = new ImageIcon("./src/main/java/resources/btn_cross.png");
        goBackBtn = Button.createButton(crossIcon);

        this.add(goBackBtn);
    }

    @Override
    protected void update() {
        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.changeScreen(ScreenType.WELCOME);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(helpScreenBg, 0, 0, null);
    }

}