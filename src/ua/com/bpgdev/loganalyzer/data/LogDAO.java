package ua.com.bpgdev.loganalyzer.data;

import java.io.IOException;

public class LogDAO {
    private DataSource dataSource;

    public LogDAO(String path) throws IOException {
        if (path.contains("http")) {
            this.dataSource = new HttpDataSource(path, "pre");
        } else {
            this.dataSource = new FileDataSource(path);
        }
    }

    public String readLine() throws IOException {
        return dataSource.readLine();
    }
}
