package com.illumio.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public static void writeMapToFile(Map<String, Integer> map, String filename, boolean append) throws IOException {
        String resourcePath = "src/main/resources/" + filename;
        try (FileWriter writer = new FileWriter(resourcePath, append)) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
    }
}
