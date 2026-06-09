package logic;

import model.ImageData;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageManager {

    private List<ImageData> images;
    private Random random;

    public ImageManager(Path folderPath) {
        images = new ArrayList<>();
        random = new Random();
        loadImages(folderPath);
    }

    private void loadImages(Path folderPath) {

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {

            for (Path path : stream) {

                String fileName = path.getFileName().toString().toLowerCase();

                if (Files.isRegularFile(path) &&
                        (fileName.endsWith(".jpg") ||
                         fileName.endsWith(".jpeg") ||
                         fileName.endsWith(".png"))) {

                    images.add(new ImageData(path));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageData getRandomImage() {

        if (images.isEmpty()) {
            return null;
        }

        return images.get(random.nextInt(images.size()));
    }
}
