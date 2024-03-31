package core;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LetterBox extends JLabel {
    // color - not in word
    private final static Color gray = new Color(121, 124, 126);

    // color - in word, wrong position
    private final static Color yellow = new Color(198, 180, 102);


    // color - in word, in correct position
    private final static Color green = new Color(121, 167, 107);

    private final static Color defaultBorder = new Color(212, 214, 218);

    private final int borderThickness = 4;

    private Border border;

    public enum BoxType {
        GREEN,
        YELLOW,
        GRAY,
        DEFAULT
    }


    public LetterBox(){
        // init box
        this.setSize(64, 64);
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
        this.setOpaque(true);

        // box text properties
        this.setText(" ");
        this.setHorizontalAlignment(JLabel.CENTER);
        //changed the font to match the game theme
        this.setFont(new Font("KG Primary Penmanship", Font.BOLD, 30));

        // box border properties
        border = BorderFactory.createLineBorder(defaultBorder, borderThickness);
        this.setBorder(border);
    }


    public void setColor(BoxType type) {
        this.setForeground(Color.WHITE);
        switch (type){
            //incorrect: GRAY
            case GRAY:
                this.border = BorderFactory.createLineBorder(gray, borderThickness);
                this.setBorder(border);
                this.setBackground(gray);
                break;

            //not in right position: YELLOW
            case YELLOW:
                this.border = BorderFactory.createLineBorder(yellow, borderThickness);
                this.setBorder(border);
                this.setBackground(yellow);
                break;

            //correct: GREEN
            case GREEN:
                this.border = BorderFactory.createLineBorder(green, borderThickness);
                this.setBorder(border);
                this.setBackground(green);
                break;

            // set box color
            case DEFAULT:
                this.border = BorderFactory.createLineBorder(defaultBorder, borderThickness);
                this.setBorder(border);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
                break;

            default:
                System.err.println("Invalid Box Type");
        }
    }

    public void updateBoxColor(BoxType type){
        this.setColor(type);
    }

    public void clear(BoxType type, String inputLetter){
        this.setText(inputLetter);
        this.setColor(type);
    }
}
