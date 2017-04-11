package it.szyszka.matcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Squier on 09.04.2017.
 */
public class BestMatchesArray {

    private ArrayList<Product> bestMatches;

    public BestMatchesArray() {
    }

    public BestMatchesArray(ArrayList<Product> bestMatches) {
        this.bestMatches = bestMatches;
        if(!bestMatches.isEmpty()) {
            screenOutBestMatches();
        }
    }

    public List<Product> screenOutBestMatches() {
        Collections.sort(bestMatches);
        final int bestMatch = bestMatches.get(0).getProductFitness();
        return bestMatches.stream()
                .filter(product -> product.getProductFitness() == bestMatch)
                .collect(Collectors.toList());
    }

    public ArrayList<Product> getBestMatches() {
        return bestMatches;
    }
}
