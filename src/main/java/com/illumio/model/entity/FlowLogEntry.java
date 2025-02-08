package com.illumio.model.entity;

public class FlowLogEntry {
    private int dstPort;
    private String protocol;
    private String tag;

    public FlowLogEntry(int dstPort, int protocol) {
        this.dstPort = dstPort;
        this.protocol = Protocol.fromNumber(protocol).getName(); // get the protocol name
        this.tag = "Untagged"; // Default tag
    }

    public int getDstPort() {
        return dstPort;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
