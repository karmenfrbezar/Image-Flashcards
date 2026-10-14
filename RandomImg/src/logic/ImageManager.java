package logic;

import model.ImageData;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageManager {

    private final List<ImageData> images = new ArrayList<>();
    private final Random random = new Random();

    public ImageManager(Path folderPath) {
        loadImages(folderPath);
    }

    private void loadImages(Path folderPath) {
        if (folderPath == null || !Files.isDirectory(folderPath)) {
            throw new IllegalArgumentException(
                "Putanja ne postoji ili nije direktorij: " + folderPath
            );
        }

        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(folderPath)) {

            for (Path path : stream) {
                if (!Files.isRegularFile(path)) {
                    continue;
                }

                String fileName =
                    path.getFileName().toString().toLowerCase(
                        java.util.Locale.ROOT
                    );

                if (fileName.endsWith(".jpg")
                        || fileName.endsWith(".jpeg")
                        || fileName.endsWith(".png")) {
                    images.add(new ImageData(path));
                }
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                "Nije moguće učitati slike iz mape: " + folderPath,
                e
            );
        }
    }

    public ImageData getRandomImage() {
        if (images.isEmpty()) {
            return null;
        }

        return images.get(random.nextInt(images.size()));
    }

    public int getImageCount() {
        return images.size();
    }
}