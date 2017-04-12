package it.szyszka.matcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Squier on 09.04.2017.
 */
public class BestMatchesArray {

    private ArrayList<ProductMatch> bestMatches;

    public BestMatchesArray() {
    }

    public BestMatchesArray(ArrayList<ProductMatch> bestMatches) {
        this.bestMatches = bestMatches;
        if(!bestMatches.isEmpty()) {
            screenOutBestMatches();
        }
    }

    public List<ProductMatch> screenOutBestMatches() {
        Collections.sort(bestMatches);
        final int bestMatch = bestMatches.get(0).getMatch();
        return bestMatches.stream()
                .filter(productMatch -> productMatch.getMatch() == bestMatch)
                .collect(Collectors.toList());
    }

    public ArrayList<ProductMatch> getBestMatches() {
        return bestMatches;
    }
}
