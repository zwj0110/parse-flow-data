import com.illumio.processor.FlowLogParser;
import com.illumio.reader.flow.FileFlowLogReader;
import com.illumio.reader.flow.FlowLogReader;
import com.illumio.reader.tag.FileTagLookup;
import com.illumio.reader.tag.TagLookup;
import com.illumio.writer.FileFlowLogWriter;
import com.illumio.writer.FlowLogWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FlowLogReader flowLogReader = new FileFlowLogReader("flow_logs.txt");
            TagLookup tagLookup = new FileTagLookup("lookup.csv");
            FlowLogParser parser = new FlowLogParser(flowLogReader, tagLookup);
            parser.parseLogs();
            FlowLogWriter flowLogWriter = new FileFlowLogWriter("output.txt");
            parser.setWriter(flowLogWriter);
            parser.writeTagCountsToFile();
            parser.writePortProtocolCountsToFile();
            parser.printResults();
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}
