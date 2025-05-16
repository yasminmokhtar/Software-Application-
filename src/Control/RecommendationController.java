package Control;

import Entity.Recommendation;
import java.util.ArrayList;
import java.util.List;

public class RecommendationController {

    public Recommendation recommend(String userId) {
        // Generate dummy recommendation ID
        String recId = "REC-" + System.currentTimeMillis();

        // Dummy suggestions (you can enhance this logic later)
        List<String> suggestions = new ArrayList<>();
        suggestions.add("Diversify your portfolio.");
        suggestions.add("Rebalance your asset allocation quarterly.");
        suggestions.add("Invest in halal-compliant assets.");

        // Create and return the Recommendation object
        return new Recommendation(recId, suggestions);
    }
}
