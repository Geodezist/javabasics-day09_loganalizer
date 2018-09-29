package ua.com.bpgdev.loganalyzer.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileDataSource implements DataSource{
    private BufferedReader logSourceData;

    //@Override
    FileDataSource(String path) throws IOException {
        logSourceData = new BufferedReader(new FileReader(path));
    }

    @Override
    public String readLine() throws IOException {
        return logSourceData.readLine();
    }
}
