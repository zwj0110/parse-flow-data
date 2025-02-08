# Project Structure

- `com.illumio`
  - `model.entity`                # Data models for the application
    - `FlowLogEntry.java`         # Represents a single flow log entry
    - `Protocol.java`             # Enum for network protocols
  - `processor`                   # Processing logic
    - `FlowLogParser.java`        # Parses logs and applies tags based on lookup rules
  - `reader`                      # Interfaces and implementations for reading data
    - `flow`                      # Flow log reading implementations
      - `FileFlowLogReader.java`  # Reads log entries from a file
      - `FlowLogReader.java`      # Interface for reading log data
    - `tag`                       # Tag lookup implementations
      - `FileTagLookup.java`      # Reads tag rules from a CSV file
      - `TagLookup.java`          # Interface for tag lookup operations
  - `utils`                       # Utility classes
    - `FileUtils.java`            # File operations utilities
  - `writer`                      # Interfaces and implementations for writing data
    - `FlowLogWriter.java`
    - `FileFlowLogWriter.java`    # Writes analysis results to files
  - `Main.java`                   # Application entry point read flow log file and tag lookup file

- `resources`                     # Resource files (logs and lookup tables)
  - `flow_logs.txt`               # Sample flow log file
  - `icmp.txt`                    # Sample flow log file only have icmp protocol 
  - `lookup.csv`                  # Sample lookup file
  - `udp.txt`                     # Sample flow log file only have udp protocol 
  - `output.txt`                  # Sample output file

Enhanced Testing and Development Efficiency: The program also includes comprehensive test classes related to the parser functionality. These tests cover basic sample flow logs, including datasets exclusively containing ICMP-type data and datasets with only UDP-type data. When modifications are made to the parser class, running all the predefined test methods allows developers to quickly ascertain if the changes have introduced any errors. This testing framework significantly enhances both testing and development efficiency by ensuring that any changes can be validated promptly and accurately.

Current Support and Limitations: The program currently supports only the default log format; custom formats are not supported. Additionally, only version 2 of the log format is supported at this time. 

Future Flexibility and Expansion Capabilities: However, due to the use of interfaces for data acquisition and output, the system is designed to be adaptable to different data formats in the future. Moreover, it can be rapidly extended to support data ingestion from sources like Kafka or databases, and likewise, it can facilitate data output to Kafka or databases instead of being limited to file-based input and output operations. This architectural choice ensures scalability and adaptability to evolving data handling requirements.
