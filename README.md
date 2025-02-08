com.illumio
│
├── model.entity                # Data models for the application
│   ├── FlowLogEntry.java       # Represents a single flow log entry
│   └── Protocol.java           # Enum for network protocols
│
├── processor                   # Processing logic
│   └── FlowLogParser.java      # Parses logs and applies tags based on lookup rules
│
├── reader                      # Interfaces and implementations for reading data
│   ├── flow                    # Flow log reading implementations
│   │   ├── FileFlowLogReader.java  # Reads log entries from a file
│   │   └── FlowLogReader.java      # Interface for reading log data
│   │
│   └── tag                     # Tag lookup implementations
│       ├── FileTagLookup.java      # Reads tag rules from a CSV file
│       └── TagLookup.java          # Interface for tag lookup operations
│
├── utils                       # Utility classes
│   └── FileUtils.java          # File operations utilities
│
├── writer                      # Interfaces and implementations for writing data
    └── FlowLogWriter.java      
    └── FileFlowLogWriter.java  # write two anaylsis result to files
Main.java                       # Application entry point read flow log file and tag lookup file
│
resources                       # Resource files (logs and lookup tables)
│   ├── flow_logs.txt           # Sample flow log file
│   └── icmp.txt                # Sample flow log file only have icmp protocol 
│   └── lookup.csv              # Sample lookup file
│   └── udp.txt                 # Sample flow log file only have udp protocol 
│   └── output.txt              # Sample output file
