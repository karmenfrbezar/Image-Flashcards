package gui;

import model.ImageData;
import logic.ImageManager;

import javax.swing.*;
import config.FontLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class MainFrame extends BaseFrame {

    private ImageManager manager;
    private ImagePanel imagePanel;
    private JLabel answerLabel;
    private ImageData currentImage;

    public MainFrame(Path folderPath) {

        manager = new ImageManager(folderPath);

        setTitle("Images");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(500, 400));

        imagePanel = new ImagePanel();

        answerLabel = new JLabel(" ");
        answerLabel.setFont(FontLoader.getSatoshiFont(28f));
        answerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        
        JButton returnToSelect = new JButton("Choose folder"); 
        returnToSelect.setBackground(new Color(136, 107, 142));
        //returnToSelect.setForeground(Color.WHITE);

        JButton revealButton = new JButton("Show answer");
        JButton nextButton = new JButton("Next");

        returnToSelect.addActionListener(e -> chooseFolder()); 
        revealButton.addActionListener(e -> showAnswer());
        nextButton.addActionListener(e -> nextImage());

        returnToSelect.setFocusable(false);
        revealButton.setFocusable(false);
        nextButton.setFocusable(false);

        returnToSelect.setFocusPainted(false);
        revealButton.setFocusPainted(false); 
        nextButton.setFocusPainted(false);

        JPanel centerButtonsPanel = new JPanel();
        centerButtonsPanel.add(revealButton);
        centerButtonsPanel.add(nextButton);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(returnToSelect, BorderLayout.WEST);
        buttonPanel.add(centerButtonsPanel, BorderLayout.CENTER);
    

        setLayout(new BorderLayout());

        add(imagePanel, BorderLayout.CENTER);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 10, 20));
        topPanel.add(answerLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        
        add(buttonPanel, BorderLayout.SOUTH);

        nextImage();

        setupKeyBindings();

        

        setVisible(true); 
    }

    private void nextImage() {
        currentImage = manager.getRandomImage();

        if (currentImage == null) {
            JOptionPane.showMessageDialog(this, "No images in this folder!");
            imagePanel.setImage(null);
            return;
        }

        try {
            BufferedImage img = ImageIO.read(currentImage.getImagePath().toFile());
            imagePanel.setImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }

        answerLabel.setText(" ");
    }

    private void showAnswer() {
        if (currentImage != null) {
            answerLabel.setText(currentImage.getImageName());
        }
    }

    private void chooseFolder() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            this.manager = new ImageManager(selectedFolder.toPath());
            nextImage();
        }
    }

    private void setupKeyBindings() {
        JRootPane rootPane = getRootPane();

        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        // Space key - Show Answer
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "showAnswer");
        actionMap.put("showAnswer", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showAnswer();
            }
        });

        // Enter key - Next Image
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "nextImage");
        actionMap.put("nextImage", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                nextImage();
            }
        });


    }
}