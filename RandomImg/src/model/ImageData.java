package model;

import java.nio.file.Path;

public class ImageData {
  private Path imagePath;
  private String imageName;

  public ImageData(Path imagePath) {
    this.imagePath = imagePath;
    
    String fileName = imagePath.getFileName().toString();

    int dotIndex = fileName.lastIndexOf('.');
    if (dotIndex > 0) {
      this.imageName = fileName.substring(0, dotIndex);
    } else {
      this.imageName = fileName;
    }

    this.imageName = this.imageName.replace("_", " "); // ako se koriste rijeci_odvojene_donjom_crtom
  }

  public Path getImagePath() {
    return imagePath;
  }

  public String getImageName() {
    return imageName;
  }
}
