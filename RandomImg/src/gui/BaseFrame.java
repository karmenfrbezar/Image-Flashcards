package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BaseFrame extends JFrame {

    public BaseFrame() {
        Image icon = Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/resources/logo_rim.PNG")
        );
        setIconImage(icon);

        setupKeyBindings(); 
    }

    private void setupKeyBindings() {
        JRootPane rootPane = getRootPane();

        InputMap inputMap =
                rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap =
                rootPane.getActionMap();

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