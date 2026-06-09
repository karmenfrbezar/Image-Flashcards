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

    if (image == null) return;

    int panelWidth = getWidth();
    int panelHeight = getHeight();

    int imgWidth = image.getWidth();
    int imgHeight = image.getHeight();

    double widthRatio = (double) panelWidth / imgWidth;
    double heightRatio = (double) panelHeight / imgHeight;
    double scale = Math.min(widthRatio, heightRatio);

    int newWidth = (int) (imgWidth * scale);
    int newHeight = (int) (imgHeight * scale);

    int x = (panelWidth - newWidth) / 2;
    int y = (panelHeight - newHeight) / 2;

    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    
    g2d.drawImage(image, x, y, newWidth, newHeight, null);
    //nadodano
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

  }
  
}
