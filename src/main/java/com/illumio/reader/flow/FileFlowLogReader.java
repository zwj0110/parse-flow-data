package com.illumio.reader.flow;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileFlowLogReader implements FlowLogReader {
    private final String filePath;

    public FileFlowLogReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> readLogs() throws IOException {
        List<String> logs = new ArrayList<>();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Resource file not found: " + filePath);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    logs.add(line);
                }
            }
        }
        return logs;
    }
}
