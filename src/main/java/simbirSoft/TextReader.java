package simbirSoft;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TextReader {

    Logger logger = Logger.getLogger(TextReader.class.getName());

    public TextReader() {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/logger.properties");
            LogManager.getLogManager().readConfiguration(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.setLevel(Level.INFO);
        logger.addHandler(new java.util.logging.ConsoleHandler());
        logger.setUseParentHandlers(false);
    }

    public String readPage(String link) throws CanNotReadURLException {
        Document htmlDocument;
        String body;
        try {
            htmlDocument = Jsoup.connect(link).get();
            Document bodyDocument = Jsoup.parse(htmlDocument.html());
            body = bodyDocument.body().text();
            logger.log(Level.INFO, "URL " + link + " was successfully read");
        } catch (IOException | IllegalArgumentException e) {
            logger.log(Level.ALL, "Error during the URL reading");
            throw new CanNotReadURLException(e.getMessage(), link);
        }
        return body;
    }

    public void countWords(String text, char[] splitters, boolean ignoreRegisterFlag) {
        WordCounter counter = new WordCounter(text, splitters, ignoreRegisterFlag);
        counter.showResults();
    }
}
