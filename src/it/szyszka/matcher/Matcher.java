package it.szyszka.matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Squier on 11.04.2017.
 */
public class Matcher {

    private List<String> dictionary;
    private List<String> ocrResult;

    public Matcher() {
    }

    public Matcher(List<String> dictionary, List<String> ocrResult) {
        this.dictionary = dictionary;
        this.ocrResult = ocrResult;
    }

    public ArrayList<BestMatchesArray> matchDefaultOcrResult() {
        ArrayList<BestMatchesArray> matches = new ArrayList<>();
        ocrResult.forEach(result -> matches.add(new BestMatchesArray(
                (ArrayList<Product>) findMatches(
                        (ArrayList<String>) intersectionByLength(result), result))));
        return matches;
    }

    public ArrayList<BestMatchesArray> matchExternalOcrResult(final ArrayList<String> ocrResult) {
        ArrayList<BestMatchesArray> matches = new ArrayList<>();
        ocrResult.forEach(result -> matches.add(new BestMatchesArray(
                                        (ArrayList<Product>) findMatches(
                                                (ArrayList<String>) intersectionByLength(result), result))));
        return matches;
    }

    private List<Product> findMatches(final ArrayList<String> sameLengthDict, final String ocrResult) {
        ArrayList<Product> bestMatches = new ArrayList<>();

        sameLengthDict.forEach((String word) -> {
            int match = 0;
            for (int i = 0; i < word.length(); i++) {
                if(word.charAt(i) == ocrResult.charAt(i)) match++;
            }
            bestMatches.add(new Product(word, match));
        });

        return bestMatches;
    }

    private List<String> intersectionByLength(final String ocrResult) {
        return dictionary.stream()
                .filter(word -> ocrResult.length() == word.length())
                .collect(Collectors.toList());
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<String> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> getOcrResult() {
        return ocrResult;
    }

    public void setOcrResult(List<String> ocrResult) {
        this.ocrResult = ocrResult;
    }
}
