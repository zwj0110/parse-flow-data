package com.illumio.reader.tag;

import java.io.IOException;

public interface TagLookup {
    void loadTags() throws IOException;
    String getTag(int dstPort, String protocol);
}
