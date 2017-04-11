package it.szyszka.worker;

import it.szyszka.matcher.BestMatchesArray;
import it.szyszka.matcher.Matcher;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by Squier on 11.04.2017.
 */
public class MatchWorker {

    private static final String DICTIONARY_FILEPATH_KEY = "dict_path";
    private static final String OCR_RESULT_FILEPATH_KEY = "ocr_result_path";

    private Matcher matcher;
    private Properties properties;

    public MatchWorker(Matcher matcher, Properties properties) {
        this.matcher = matcher;
        this.properties = properties;
    }

    public List<BestMatchesArray> doMatch() {
        return matcher.matchDefaultOcrResult();
    }

    public MatchWorker initializeMatcherExternalDictionary(List<String> dictionary) {
        matcher.setDictionary(dictionary);
        return this;
    }

    public MatchWorker initializeMatcherDefaultDictionary() {
        matcher.setDictionary(readDictionaryWords());
        return this;
    }

    public MatchWorker initializeMatcherDefaultOcrResult(List<String> ocrResult) {
        matcher.setOcrResult(ocrResult);
        return this;
    }

    public MatchWorker initializeMatcherDefaultOcrResult() {
        matcher.setOcrResult(readOcrResultWords());
        return this;
    }

    public List<String> readOcrResultWords() {
        try {
            return Arrays.asList(new String(
                    Files.readAllBytes(
                            Paths.get(properties.getProperty(OCR_RESULT_FILEPATH_KEY))))
                    .split(" "));
        } catch (IOException e) {
            System.err.println("Failed to load ocr result file.\n" + e.getMessage());
        }
        return null;
    }

    public List<String> readDictionaryWords() {
        try {
            return Arrays.asList(new String(
                                Files.readAllBytes(
                                        Paths.get(properties.getProperty(DICTIONARY_FILEPATH_KEY))))
                            .split(" "));
        } catch (IOException e) {
            System.err.println("Failed to load dictionary file.\n" + e.getMessage());
        }
        return null;
    }

    public MatchWorker loadProperties(File propertiesFile) {
        InputStream is;
        try {
            is = new FileInputStream(propertiesFile);
            properties.load(is);
        } catch (FileNotFoundException e) {
            System.err.println("Failed to locate properties file.\n" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Failed to load properties.\n" + e.getMessage());
        }
        return this;
    }

}
