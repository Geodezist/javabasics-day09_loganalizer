package ua.com.bpgdev.loganalyzer;

import java.time.LocalDateTime;

public class LogToken {
    private LocalDateTime time;
    private HttpMethod method;
    private String message;

    LogToken(LocalDateTime time, HttpMethod method, String message) {
        this.time = time;
        this.method = method;
        this.message = message;
    }

    LocalDateTime getTime() {
        return time;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getMessage() {
        return message;
    }
}
