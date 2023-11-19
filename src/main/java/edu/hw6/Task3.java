package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Task3 {

    private static final int MAGIC_NUMBER_MASK = 0xFF;

    private Task3() {
    }

    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;
    public static final AbstractFilter WRITABLE = Files::isWritable;

    public static AbstractFilter largerThan(long size) {
        return path -> Files.size(path) > size;
    }

    public static AbstractFilter extension(String ext) {
        return path -> path.toString().endsWith(ext);
    }

    public static AbstractFilter regexMatches(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return path -> pattern.matcher(path.getFileName().toString()).matches();
    }

    public static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return path -> pattern.matcher(path.getFileName().toString()).find();
    }

    public static AbstractFilter magicNumber(byte... magicBytes) {
        return path -> {
            try (var is = Files.newInputStream(path)) {
                for (byte magicByte : magicBytes) {
                    if (is.read() != (magicByte & MAGIC_NUMBER_MASK)) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public interface AbstractFilter extends DirectoryStream.Filter<Path> {
        default AbstractFilter and(AbstractFilter other) {
            return path -> this.accept(path) && other.accept(path);
        }
    }
}
