package com.illumio.writer;

import com.illumio.utils.FileUtils;

import java.io.IOException;
import java.util.Map;

public class FileFlowLogWriter implements FlowLogWriter {
    private String filename;

    public FileFlowLogWriter(String filename) {
        this.filename = filename;
    }

    @Override
    public void write(Map<String, Integer> data) throws IOException {
        FileUtils.writeMapToFile(data, filename,true);
    }
}
