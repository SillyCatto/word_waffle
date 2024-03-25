package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {

    // creates a button with an image with hover-fade effect
    public static JButton createButton(ImageIcon icon){
        return new JButton(icon) {
            @Override
            protected void paintComponent(Graphics g) {
                setBorder(BorderFactory.createEmptyBorder());
                setContentAreaFilled(false);
                setAlignmentX(Component.CENTER_ALIGNMENT);

                if (getModel().isRollover()) { // if mouse is over the button
                    BufferedImage image = new BufferedImage(getWidth(), getHeight(),
                            BufferedImage.TYPE_INT_ARGB);

                    Graphics2D g2d = image.createGraphics();
                    g2d.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);

                    // create a dark overlay
                    g2d.setComposite(AlphaComposite.SrcOver.derive(0.35f)); // Set opacity
                    g2d.setColor(new Color(96, 56, 20));
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.dispose();
                    g.drawImage(image, 0, 0, null);
                } else {
                    super.paintComponent(g);
                }
            }
        };
    }
}
