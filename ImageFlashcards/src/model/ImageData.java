package model;

import java.nio.file.Path;
import java.util.Objects;

public class ImageData {

    private final Path imagePath;
    private final String imageName;

    public ImageData(Path imagePath) {
        this.imagePath = Objects.requireNonNull(
            imagePath, "Putanja slike ne smije biti null"
        );

        String fileName = imagePath.getFileName().toString();

        int dotIndex = fileName.lastIndexOf('.');
        String name = dotIndex > 0
            ? fileName.substring(0, dotIndex)
            : fileName;

        this.imageName = name.replace('_', ' ');
    }

    public Path getImagePath() {
        return imagePath;
    }

    public String getImageName() {
        return imageName;
    }
}