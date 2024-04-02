package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpScreen extends Screen {
    private JButton goBack;
    public Image BackgroundImage;
    public HelpScreen(Window window) {
        super(window);
        initialize();
        update();
        BackgroundImage = new ImageIcon("./src/resources/Help.png").getImage();

    }

    @Override
    public void initialize() {
        setLayout(new BorderLayout());

        // Create a panel to hold the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false); // Make the panel transparent

        // Create the "Go back" button
        goBack = new JButton("Go back");

        // Change the font of the button
        Font buttonFont = new Font("KG Primary Penmanship", Font.PLAIN, 16);
        goBack.setFont(buttonFont);

        // Set preferred size for the button to make it smaller
        goBack.setPreferredSize(new Dimension(100, 40)); // Adjust dimensions as needed


        //adding color to the button



        // Add ActionListener to the button
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.changeScreen(ScreenType.WELCOME);
            }
        });

        // Add the button to the panel
        buttonPanel.add(goBack);

        // Add the button panel to the center of the HelpScreen
        add(buttonPanel, BorderLayout.SOUTH);
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