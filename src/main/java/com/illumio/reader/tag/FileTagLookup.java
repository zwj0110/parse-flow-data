package com.illumio.reader.tag;

import com.illumio.utils.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTagLookup implements TagLookup {
    private final Map<String, String> lookupTable = new HashMap<>();
    private static final Map<String, Integer> PROTOCOL_MAP = new HashMap<>();

    static {
        PROTOCOL_MAP.put("tcp", 6);
        PROTOCOL_MAP.put("udp", 17);
        PROTOCOL_MAP.put("icmp", 1);
    }

    public Map<String, String> getLookupTable() {
        return lookupTable;
    }

    private final String filePath;

    public FileTagLookup(String filePath) {
        this.filePath = filePath;
        try {
            loadTags();
        } catch (IOException e) {
            System.out.println("read file tag look up error:" + e.getMessage());
        }
    }

    @Override
    public void loadTags() throws IOException {
        List<String> tagLookUp = FileUtils.readAllLines(filePath);
        // skip first line
        tagLookUp.remove(0);
        for (String line : tagLookUp) {
            String[] parts = line.split(",");
            if (parts.length < 3) continue;

            int port = Integer.parseInt(parts[0].trim());
            String protocol = parts[1].trim().toLowerCase();
            String tag = parts[2].trim();

            lookupTable.put(port + "," + protocol, tag);
        }
    }

    @Override
    public String getTag(int dstPort, String protocol) {
        return lookupTable.getOrDefault(dstPort + "," + protocol, "Untagged");
    }
}