package ua.com.bpgdev.loganalyzer.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringJoiner;

public class HttpDataSource implements DataSource {
    private BufferedReader logSourceData;

    //@Override
    HttpDataSource(String path, String tag) throws IOException {
        Document doc = Jsoup.connect(path).get();
        if (doc == null) {
            return;
        }

        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        Elements pres = doc.getElementsByTag(tag);
        for (Element pre : pres) {
            stringJoiner.add(pre.text());
        }
        logSourceData = new BufferedReader( new StringReader(stringJoiner.toString()));
    }

    @Override
    public String readLine() throws IOException {
        return logSourceData.readLine();
    }
}
