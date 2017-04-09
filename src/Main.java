import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by E6520 on 2017-04-08.
 */
public class Main {

    public static void main(String[] args) {
        String[] ok = {"Syr","Waniliowy","cycki","Ser"};    //slownik
        String [][] change = {
                {"o", "O", "u"}, {"l","1","ł","\\]","\\[","T"},{"y","v"}
        };
        String[] s = {"Svr"};

        for (int i = 0; i <s.length ; i++) {
            int wordLength=s[i].length();
            ArrayList <String> sameLength= new ArrayList<>();
            for (String word : ok) {
                if(wordLength==word.length()){
                    sameLength.add(word);
                }
            }
            String[] splitted = s[i].split("");
            ArrayList<ProductMatch> productMatches = null;
            int highestMatch=0;
            for (String word : sameLength) {
                String[] wordSplitted = word.split("");
                int match=0;

                for (int j = 0; j < wordSplitted.length; j++) {
                    if(wordSplitted[j].equals(splitted[j])) {
                        match += 1;
                    }
                }
                if(highestMatch<match){
                    productMatches=new ArrayList<>();
                    productMatches.add(new ProductMatch(word, match));
                    highestMatch = match;
                } else if(highestMatch == match && productMatches != null) {
                    productMatches.add(new ProductMatch(word, match));
                }
            }

            if(productMatches!=null) {
                productMatches.forEach(word -> System.out.println(word.toString()));
            }
            for (ProductMatch product :
                    productMatches) {
                if (!s[i].equals(product.getProductName())) {
                    for (String[] iterTab : change) {
                        for (int j = 0; j < iterTab.length; j++) {
                            s[i] = s[i].replaceFirst(String.valueOf(iterTab[j]), String.valueOf(iterTab[(j + 1) % iterTab.length]));
                            if (!s[i].equals(ok[0])) {
                                System.out.println("NOK: " + s[0]);
                            } else {
                                System.out.println("OK: " + s[0]);
                                System.exit(9);
                            }
                        }
                    }
                }
            }

        }








    }
}
