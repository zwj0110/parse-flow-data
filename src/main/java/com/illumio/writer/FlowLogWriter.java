package com.illumio.writer;

import java.io.IOException;
import java.util.Map;

public interface FlowLogWriter {
    void write(Map<String, Integer> data) throws IOException;
}
