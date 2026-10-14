package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class BaseFrame extends JFrame {

    public BaseFrame() {
        URL iconUrl = getClass().getResource("/logo_rim.PNG");

        if (iconUrl != null) {
            Image icon = Toolkit.getDefaultToolkit().getImage(iconUrl);
            setIconImage(icon);
        } else {
            System.err.println("Logo not found: /logo_rim.PNG");
        }

        setupKeyBindings();
    }

    private void setupKeyBindings() {
        JRootPane rootPane = getRootPane();

        InputMap inputMap =
                rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        inputMap.put(
                KeyStroke.getKeyStroke("control Q"),
                "quitApplication"
        );

        actionMap.put("quitApplication", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}