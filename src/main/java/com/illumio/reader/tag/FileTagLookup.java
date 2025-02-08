package com.illumio.reader.tag;

import com.illumio.model.entity.Protocol;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Resource file not found: " + filePath);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 3) continue;

                    int port = Integer.parseInt(parts[0].trim());
                    String protocol = parts[1].trim().toLowerCase();
                    String tag = parts[2].trim();

                    lookupTable.put(port + "," + protocol, tag);
                }
            }
        }
    }

    @Override
    public String getTag(int dstPort, String protocol) {
        return lookupTable.getOrDefault(dstPort + "," + protocol, "Untagged");
    }
}