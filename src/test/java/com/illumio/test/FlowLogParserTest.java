package com.illumio.test;

import com.illumio.processor.FlowLogParser;
import com.illumio.reader.flow.FileFlowLogReader;
import com.illumio.reader.flow.FlowLogReader;
import com.illumio.reader.tag.FileTagLookup;
import com.illumio.reader.tag.TagLookup;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class FlowLogParserTest {
    FlowLogParser parser;

    @Before
    public void init() throws IOException {
        parser = new FlowLogParser();
        TagLookup tagLookup = new FileTagLookup("lookup.csv");
        parser.setTagLookup(tagLookup);
    }

    @Test
    public void testFlowLogTagCounts() throws IOException {
        FlowLogReader fileFlowLogReader = new FileFlowLogReader("flow_logs.txt");
        parser.setLogReader(fileFlowLogReader);
        parser.parseLogs();
        Map<String, Integer> tagCounts = parser.getTagCounts();

        assertEquals(2, (long) tagCounts.get("sv_P1"));
        assertEquals(1, (long) tagCounts.get("sv_P2"));
        assertEquals(3, (long) tagCounts.get("email"));
        assertEquals(8, (long) tagCounts.get("Untagged"));
    }

    @Test
    public void testPortProtocolCounts() throws IOException {
        FlowLogReader fileFlowLogReader = new FileFlowLogReader("flow_logs.txt");
        parser.setLogReader(fileFlowLogReader);
        parser.parseLogs();
        Map<String, Integer> portProtocolCounts = parser.getPortProtocolCounts();
        assertEquals(1, (long) portProtocolCounts.get("23,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("993,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("80,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("25,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("49157,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("443,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("49155,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("110,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("49153,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("49158,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("143,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("49156,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("49154,tcp"));
        assertEquals(1, (long) portProtocolCounts.get("1024,tcp"));
    }
    @Test
    public void testOnlyICMP() throws IOException {
        FlowLogReader fileFlowLogReader = new FileFlowLogReader("icmp.txt");
        parser.setLogReader(fileFlowLogReader);
        parser.parseLogs();
        Map<String, Integer> portProtocolCounts = parser.getPortProtocolCounts();
        Map<String, Integer> tagCounts = parser.getTagCounts();
        assertEquals(1, (long) tagCounts.get("sv_P5"));
        assertEquals(1, (long) portProtocolCounts.get("0,icmp"));
    }

    @Test
    public void testOnlyUdp() throws IOException {
        FlowLogReader fileFlowLogReader = new FileFlowLogReader("udp.txt");
        parser.setLogReader(fileFlowLogReader);
        parser.parseLogs();
        Map<String, Integer> portProtocolCounts = parser.getPortProtocolCounts();
        Map<String, Integer> tagCounts = parser.getTagCounts();
        assertEquals(1, (long) tagCounts.get("sv_P2"));
        assertEquals(1, (long) tagCounts.get("SV_P3"));
        assertEquals(1, (long) portProtocolCounts.get("31,udp"));
        assertEquals(1, (long) portProtocolCounts.get("68,udp"));
    }

}
