package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class WelcomeScreen extends Screen {
    private static WelcomeScreen instance = null;

    private final JButton playBtn;
    private final JButton helpBtn;
    private final JButton aboutBtn;
    private final Image background;

    private WelcomeScreen(){
        background = new ImageIcon("./src/resources/screen_title.png").getImage();

        ImageIcon playIcon = new ImageIcon("./src/resources/btn_play.png");
        playBtn = createButton(playIcon);

        ImageIcon helpIcon = new ImageIcon("./src/resources/btn_help.png");
        helpBtn = createButton(helpIcon);

        ImageIcon aboutIcon = new ImageIcon("./src/resources/btn_about.png");
        aboutBtn = createButton(aboutIcon);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(350));
        this.add(playBtn);
        this.add(Box.createVerticalStrut(30));
        this.add(helpBtn);
        this.add(Box.createVerticalStrut(30));
        this.add(aboutBtn);
    }


    private JButton createButton(ImageIcon icon){
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

    public static WelcomeScreen get(){
        if (instance == null){
            instance = new WelcomeScreen();
        }
        return instance;
    }


    @Override
    public void update(JFrame frame) {
        // handle button clicks
        // play button
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Window.changeScreen(Type.GAMEPLAY);
//                GameScreen.get().requestFocusInWindow();
            }
        });

        // help button

        // about button

    }

    // add background image to welcome screen
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, null);
    }

}
