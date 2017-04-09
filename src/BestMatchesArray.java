import java.util.ArrayList;
import java.util.Collections;
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

    public void screenOutBestMatches() {
        Collections.sort(bestMatches);
        final int bestMatch = bestMatches.get(bestMatches.size() - 1).getProductFitness();
        bestMatches.stream()
                .filter(product -> product.getProductFitness() == bestMatch)
                .collect(Collectors.toList());
    }

    public ArrayList<Product> getBestMatches() {
        return bestMatches;
    }
}
