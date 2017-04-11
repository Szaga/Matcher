package it.szyszka;

import it.szyszka.matcher.BestMatchesArray;
import it.szyszka.matcher.Matcher;
import it.szyszka.worker.MatchWorker;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by E6520 on 2017-04-08.
 */
public class Main {

    private static final String EXTERNAL_FILES_PROPERTIES = "external_files.properties";

    public static void main(String[] args) {

        new MatchWorker(new Matcher(), new Properties())
                .loadProperties(new File(EXTERNAL_FILES_PROPERTIES))
                .initializeMatcherDefaultDictionary()
                .initializeMatcherDefaultOcrResult()
                .doMatch().forEach(array -> {
                    array.screenOutBestMatches().forEach(System.out::println);
                });

    }
}
