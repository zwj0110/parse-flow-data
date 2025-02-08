package com.illumio.utils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Consumer;

public class FileUtils {
    public static void processLines(String fileName, Consumer<String> action) throws IOException {
        InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new FileNotFoundException("Resource file not found: " + fileName);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                action.accept(line);
            }
        }
    }
    public static List<String> readAllLines(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        processLines(fileName, lines::add);
        return lines;
    }
}
