package com.illumio.reader.flow;

import java.io.IOException;
import java.util.List;

public interface FlowLogReader {
    List<String> readLogs() throws IOException;
}
