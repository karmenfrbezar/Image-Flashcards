package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class WelcomeFrame extends BaseFrame {
  public WelcomeFrame() {

    setTitle("RandomImage");
    setSize(400, 250);
    setLocationRelativeTo(null);;
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setMinimumSize(new Dimension(300, 200));

    JLabel welcomeLabel = new JLabel("Choose folder with pictures");
    JButton selectButton = new JButton("Choose folder");

    welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    selectButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    selectButton.addActionListener(e -> chooseFolder());

    selectButton.setFocusPainted(false);

    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    add(Box.createVerticalGlue());    
    add(welcomeLabel);
    add(Box.createRigidArea(new Dimension(0, 10)));
    add(selectButton);
    add(Box.createVerticalGlue());
  }

  private void chooseFolder() {

    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    int result = chooser.showOpenDialog(this);

    if(result == JFileChooser.APPROVE_OPTION){

      File selectedFolder = chooser.getSelectedFile();

      new MainFrame(selectedFolder.toPath());
      dispose();
    }
  }
}
