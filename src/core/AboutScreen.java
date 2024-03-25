package core;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutScreen extends Screen{
    private JButton goBack;
    public AboutScreen(Window window){
        super(window);
        initialize();
        update();
    }

    public void initialize() {
        JLabel label = new JLabel("About screen");
        goBack = new JButton("Go back");
        this.add(label);
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
}
