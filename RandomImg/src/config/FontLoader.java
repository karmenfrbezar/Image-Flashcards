package config;

import java.awt.Font;
import java.io.InputStream;

public class FontLoader {

    private static Font satoshiFont;

    public static Font getSatoshiFont(float size) {
        try {
            if (satoshiFont == null) {
                InputStream is = FontLoader.class.getResourceAsStream("/resources/fonts/Satoshi-Regular.otf");
                satoshiFont = Font.createFont(Font.TRUETYPE_FONT, is);
            }
            return satoshiFont.deriveFont(size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) size); // fallback font
        }
    }
}
