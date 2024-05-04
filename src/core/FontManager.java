package core;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontManager {
    public static Font loadFontFromTTF(String fontPath, int style, float size) {
        try {
            // Load font from file
            File fontFile = new File(fontPath);
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            // Derive font with desired style and size
            return baseFont.deriveFont(style, size);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Return null in case of failure
            return null;
        }
    }
}
