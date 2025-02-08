package com.illumio.reader.flow;

import com.illumio.utils.FileUtils;

import java.io.*;
import java.util.List;

public class FileFlowLogReader implements FlowLogReader {
    private final String filePath;

    public FileFlowLogReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> readLogs() throws IOException {
        return FileUtils.readAllLines(filePath);
    }
}
