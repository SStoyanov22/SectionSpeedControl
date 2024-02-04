package org.sectionspeedcontrol;

import java.io.IOException;
import java.util.List;

public interface DataProcessor {
    List<Vehicle> read(String filePath);
    void write(String output, String fileName) throws IOException;

}
