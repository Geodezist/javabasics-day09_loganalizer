package ua.com.bpgdev.loganalyzer.data;

import java.io.IOException;

public interface DataSource {

    //public void prepareSourceData(String path, String[] args) throws IOException;
    String readLine() throws IOException;

}
