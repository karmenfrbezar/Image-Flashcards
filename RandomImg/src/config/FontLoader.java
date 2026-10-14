package config;

import java.awt.Font;
import java.io.InputStream;
import java.io.IOException;

public class FontLoader {

    private static Font satoshiFont;

    public static Font getSatoshiFont(float size) {
        if (satoshiFont == null) {
            try (InputStream is =
                    FontLoader.class.getResourceAsStream(
                            "/fonts/Satoshi-Regular.otf")) {

                if (is == null) {
                    throw new IOException(
                            "Satoshi font not found in resources/fonts/"
                    );
                }

                satoshiFont = Font.createFont(Font.TRUETYPE_FONT, is);

            } catch (Exception e) {
                System.err.println("Greska pri učitavanju Satoshi fonta:");
                e.printStackTrace();

                return new Font(
                        "SansSerif",
                        Font.PLAIN,
                        Math.max(1, Math.round(size))
                );
            }
        }

        return satoshiFont.deriveFont(size);
    }
}