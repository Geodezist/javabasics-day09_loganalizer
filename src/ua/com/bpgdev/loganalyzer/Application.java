package ua.com.bpgdev.loganalyzer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Application {
    public static void main(String[] args) throws IOException {
        LogAnalyzer.setPatternRegexpString("^(.+?) - - \\[(.+?)\\] \"(.+?) (.*)");
        LogAnalyzer.setPatternDateTime("dd/MMM/yyyy:HH:mm:ss Z");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss", Locale.US);

        LocalDateTime timeFrom = LocalDateTime.parse("07/Mar/2004:16:30:00", dateTimeFormatter);
        LocalDateTime timeTo = LocalDateTime.parse("07/Mar/2004:17:30:00", dateTimeFormatter);


        ArrayList<LogToken> arrayList = LogAnalyzer.analyze("http://www.monitorware.com/en/logsamples/apache.php", timeFrom, timeTo);
        System.out.println("stop");


        timeFrom = LocalDateTime.parse("07/Mar/2004:16:00:00", dateTimeFormatter);
        timeTo = LocalDateTime.parse("07/Mar/2004:16:30:00", dateTimeFormatter);


        arrayList = LogAnalyzer.analyze("http://www.monitorware.com/en/logsamples/apache.php", timeFrom, timeTo);
        System.out.println("stop");
    }
}
