package com.illumio.processor;

import com.illumio.model.entity.FlowLogEntry;
import com.illumio.model.entity.Protocol;
import com.illumio.reader.flow.FlowLogReader;
import com.illumio.reader.tag.TagLookup;
import com.illumio.writer.FlowLogWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlowLogParser {
    private TagLookup tagLookup;
    private Map<String, Integer> tagCounts = new HashMap<>();
    private Map<String, Integer> portProtocolCounts = new HashMap<>();
    private FlowLogReader logReader;
    private FlowLogWriter writer;

    public Map<String, Integer> getTagCounts() {
        return tagCounts;
    }

    public Map<String, Integer> getPortProtocolCounts() {
        return portProtocolCounts;
    }

    public FlowLogParser() {
    }

    public FlowLogParser(FlowLogReader flowLogReader, TagLookup tagLookup) throws IOException {
        this.logReader = flowLogReader;
        this.tagLookup = tagLookup;
    }

    public void setWriter(FlowLogWriter writer) {
        this.writer = writer;
    }

    public void setLogReader(FlowLogReader logReader) {
        this.logReader = logReader;
    }

    public void setTagLookup(TagLookup tagLookup) {
        this.tagLookup = tagLookup;
    }


    public void parseLogs() throws IOException {
        List<String> logs = logReader.readLogs();

        for (String line : logs) {
            String[] fields = line.split(" ");
            if (fields.length < 13) continue;
            // the index of dstPort is 6 because of Amazon doc
            int dstPort = Integer.parseInt(fields[6]);
            int protocol = Integer.parseInt(fields[7]);
            FlowLogEntry flowLogEntry = new FlowLogEntry(dstPort, protocol);


            String tag = tagLookup.getTag(flowLogEntry.getDstPort(), flowLogEntry.getProtocol());
            flowLogEntry.setTag(tag);
            tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);

            String key = buildKey(flowLogEntry);
            portProtocolCounts.put(key, portProtocolCounts.getOrDefault(key, 0) + 1);
        }
    }

    public String buildKey(FlowLogEntry flowLogEntry) {
        return (flowLogEntry.getDstPort() + "," + Protocol.fromName(flowLogEntry.getProtocol()).getName()).toLowerCase();
    }


    public void printResults() {
        System.out.println("Tag Counts:");
        tagCounts.forEach((tag, count) -> System.out.println(tag + "," + count));

        System.out.println("\nPort/Protocol Combination Counts:");
        portProtocolCounts.forEach((combo, count) -> System.out.println(combo + "," + count));
    }

    public void writeTagCountsToFile() throws IOException {
        writer.write(tagCounts);
    }

    public void writePortProtocolCountsToFile() throws IOException {
        writer.write(portProtocolCounts);
    }
}
