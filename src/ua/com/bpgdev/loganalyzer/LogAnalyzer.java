package ua.com.bpgdev.loganalyzer;

import ua.com.bpgdev.loganalyzer.data.LogDAO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogAnalyzer {

    /* https://regex101.com/
                ^(.+?) - - \[(.+?)\] "(.+?) (.*)

            1st Capturing Group (.+?) - IP address or domain name
            .+? matches any character (except for line terminators)
            +? Quantifier — Matches between one and unlimited times, as few times as possible, expanding as needed (lazy)

             - - \[  matches the characters  - - [ literally (case sensitive)

            2nd Capturing Group (.+?) - Date & Time

            \] "  matches the character ] " literally (case sensitive)

            3rd Capturing Group (.+?) - Method

            4th Capturing Group (.*) - Message
            .* matches any character (except for line terminators)
            * Quantifier — Matches between zero and unlimited times, as many times as possible, giving back as needed (greedy)
    * */
    private static String patternRegexpString;
    private static String patternDateTime;
    private static Pattern pattern;
    private static Matcher matcher;

    public static ArrayList<LogToken> analyze(String path, LocalDateTime timeFrom, LocalDateTime timeTo) throws IOException {
        ArrayList<LogToken> resultArray = new ArrayList<>();
        LogToken logToken;
        String rawLogData;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(patternDateTime, Locale.US);

        LogDAO logSourceData = new LogDAO(path);

        while ((rawLogData = logSourceData.readLine()) != null) {
            for (String textLine : rawLogData.split(System.lineSeparator())) {
                logToken = getToken(textLine, dateTimeFormatter);
                if (logToken != null && (logToken.getTime().isAfter(timeFrom) && logToken.getTime().isBefore(timeTo))) {
                    resultArray.add(logToken);
                }
            }
        }

        return resultArray;
    }

    private static LogToken getToken(String sourceString, DateTimeFormatter dateTimeFormatter) {
        LogToken result = null;

        matcher = pattern.matcher(sourceString);
        while (matcher.find()) {
            result = new LogToken(
                    LocalDateTime.parse(matcher.group(2), dateTimeFormatter),
                    HttpMethod.valueOf(matcher.group(3)),
                    matcher.group(4)
            );
        }
        return result;
    }


    public static void setPatternRegexpString(String patternRegexpString) {
        LogAnalyzer.patternRegexpString = patternRegexpString;
        pattern = Pattern.compile(LogAnalyzer.patternRegexpString);
    }

    public static void setPatternDateTime(String patternDateTime) {
        LogAnalyzer.patternDateTime = patternDateTime;
    }

    public static String getPatternRegexpString() {
        return patternRegexpString;
    }

    public static String getPatternDateTime() {
        return patternDateTime;
    }
}
