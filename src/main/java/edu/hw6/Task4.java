package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Task4 {

    private Task4() {
    }

    public static void writeToFile(String text, Path path) throws IOException {
        try (OutputStream fileOut = Files.newOutputStream(path);
             CheckedOutputStream checkedOut = new CheckedOutputStream(fileOut, new Adler32());
             BufferedOutputStream bufferedOut = new BufferedOutputStream(checkedOut);
             OutputStreamWriter writer = new OutputStreamWriter(bufferedOut, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(writer)) {

            printWriter.println(text);
        }
    }
}
