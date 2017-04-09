import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by E6520 on 2017-04-08.
 */
public class Main {

    private static final ArrayList<String> DICTIONARY = new ArrayList<>(Arrays.asList("Syr","Waniliowy","Ser"));
    private static final String[] OCR_RESULT = {"Svr"};
    private static final Map<String, ArrayList<String>> REPLACEMENT_MAP = initMap();

    private static Map initMap() {
        REPLACEMENT_MAP.put("o", new ArrayList<>(Arrays.asList("O", "u", "0")));
        REPLACEMENT_MAP.put("l", new ArrayList<>(Arrays.asList("1","ł","\\]","\\[","T")));
        REPLACEMENT_MAP.put("y", new ArrayList<>(Arrays.asList("v")));
        REPLACEMENT_MAP.put("v", new ArrayList<>(Arrays.asList("y")));
    }

    private static List<String> intersectionByLength(final ArrayList<String> dictionary, final String ocrResult) {
        return dictionary.stream()
                .filter(word -> ocrResult.length() == word.length())
                .collect(Collectors.toList());
    }

    private static List<Product> findMatches(final ArrayList<String> sameLengthDict, final String ocrResult) {
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

    public static void main(String[] args) {

        for (int i = 0; i < OCR_RESULT.length ; i++) {
            ArrayList <String> sameLength = (ArrayList<String>) intersectionByLength(DICTIONARY, OCR_RESULT[i]);
            BestMatchesArray bestMatchesArray = new BestMatchesArray((ArrayList<Product>) findMatches(sameLength, OCR_RESULT[i]));

            bestMatchesArray.getBestMatches().forEach(System.out::println);

            /*for (Product product : products) {
                if (!OCR_RESULT[i].equals(product.getProductName())) {
                    for (String[] iterTab : REPLACEMENT_MAP) {
                        for (int j = 0; j < iterTab.length; j++) {
                            OCR_RESULT[i] = OCR_RESULT[i].replaceFirst(String.valueOf(iterTab[j]), String.valueOf(iterTab[(j + 1) % iterTab.length]));
                            if (!OCR_RESULT[i].equals(DICTIONARY[0])) {
                                System.out.println("NOK: " + OCR_RESULT[0]);
                            } else {
                                System.out.println("OK: " + OCR_RESULT[0]);
                                System.exit(9);
                            }
                        }
                    }
                }
            }*/

        }
    }
}
