package utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontManager {
    // Load custom font with a specified style and size
    public static Font loadFont(String filePath, int fontStyle, float fontSize){
        // set a default font for null safety
        Font font = new Font("Serif", fontStyle, (int)fontSize);

        try{
            font = Font.createFont(Font.TRUETYPE_FONT,
                            new File(filePath))
                    .deriveFont(fontStyle, fontSize);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    new File(filePath)));

        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading font file: " + filePath);
        }

        return font;
    }
}

