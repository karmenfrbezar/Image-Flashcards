package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) {
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (panelWidth <= 0 || panelHeight <= 0) {
            return;
        }

        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();

        if (imgWidth <= 0 || imgHeight <= 0) {
            return;
        }

        double scale = Math.min(
            (double) panelWidth / imgWidth,
            (double) panelHeight / imgHeight
        );

        int newWidth = Math.max(1, (int) (imgWidth * scale));
        int newHeight = Math.max(1, (int) (imgHeight * scale));

        int x = (panelWidth - newWidth) / 2;
        int y = (panelHeight - newHeight) / 2;

        Graphics2D g2d = (Graphics2D) g.create();

        try {
            g2d.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC
            );

            g2d.drawImage(
                image, x, y, newWidth, newHeight, null
            );
        } finally {
            g2d.dispose();
        }
    }
}