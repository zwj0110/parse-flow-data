package com.illumio.model.entity;

import java.util.HashMap;
import java.util.Map;

public enum Protocol {
    TCP(6, "tcp"),
    UDP(17, "udp"),
    ICMP(1, "icmp"),
    IGMP(2, "igmp"),
    GRE(47, "gre"),
    ESP(50, "esp"),
    AH(51, "ah"),
    ICMPV6(58, "icmpv6"),
    OSPF(89, "ospf"),
    SCTP(132, "sctp"),
    UNKNOWN(-1, "unknown");

    private static final Map<Integer, Protocol> BY_NUMBER = new HashMap<>();
    private static final Map<String, Protocol> BY_NAME = new HashMap<>();

    static {
        for (Protocol protocol : values()) {
            BY_NUMBER.put(protocol.number, protocol);
            BY_NAME.put(protocol.name.toLowerCase(), protocol);
        }
    }

    private final int number;
    private final String name;

    Protocol(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }


    public static Protocol fromNumber(int number) {
        return BY_NUMBER.getOrDefault(number, UNKNOWN);
    }


    public static Protocol fromName(String name) {
        if (name == null) return UNKNOWN;
        return BY_NAME.getOrDefault(name.toLowerCase(), UNKNOWN);
    }
}
