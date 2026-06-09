import gui.WelcomeFrame;
import config.FontLoader;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {

        UIManager.put("Panel.background", new Color(40, 42, 54));
        UIManager.put("OptionPane.background", new Color(40, 42, 54));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        
        UIManager.put("Label.foreground", new Color(248, 248, 242));
        UIManager.put("Button.background", new Color(68, 71, 90));
        UIManager.put("Button.foreground", Color.WHITE);
        
        UIManager.put("TextField.background", new Color(68, 71, 90));
        UIManager.put("TextField.foreground", Color.WHITE);
        UIManager.put("TextField.caretForeground", Color.WHITE);

        UIManager.put("Table.background", new Color(40, 42, 54));
        UIManager.put("Table.foreground", Color.WHITE);
        UIManager.put("Table.gridColor", new Color(68, 71, 90));
        
        setGlobalFont(FontLoader.getSatoshiFont(16f));
        
        SwingUtilities.invokeLater(() -> {
            new WelcomeFrame().setVisible(true);
        });
    }

    private static void setGlobalFont(Font font) {
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("TitledBorder.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
    }
}
