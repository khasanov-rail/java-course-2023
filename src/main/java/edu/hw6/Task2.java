package edu.hw6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Task2 {

    private Task2() {
    }

    public static Path cloneFile(Path path) throws IOException {
        if (path == null || !Files.exists(path) || !Files.isRegularFile(path)) {
            throw new FileNotFoundException("Файл не найден или путь не является файлом");
        }

        Path parentDir = path.getParent();
        String fileName = path.getFileName().toString();
        String fileBaseName = fileName;
        String extension = "";

        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            fileBaseName = fileName.substring(0, dotIndex);
            extension = fileName.substring(dotIndex);
        }

        Path newPath;
        int copyNumber = 0;
        do {
            String newFileName =
                fileBaseName + (copyNumber == 0 ? "" : " — копия" + (copyNumber > 1 ? " (" + copyNumber + ")" : ""))
                    + extension;
            newPath = parentDir.resolve(newFileName);
            copyNumber++;
        } while (Files.exists(newPath));

        Files.copy(path, newPath, StandardCopyOption.COPY_ATTRIBUTES, LinkOption.NOFOLLOW_LINKS);
        return newPath;
    }
}
