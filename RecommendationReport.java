package Control.Factory_pattern;

import Control.RecommendationController;
import Entity.Recommendation;

public class RecommendationReport implements Report {
    private final RecommendationController recommendationController;

    public RecommendationReport(RecommendationController recommendationController) {
        this.recommendationController = recommendationController;
    }

    @Override
    public void generate(String userId) {
        Recommendation rec = recommendationController.recommend(userId);
        System.out.println("=== Recommendations ===");
        System.out.println("ID: " + rec.getRecId());
        System.out.println("Suggestions:");
        for (String s : rec.getSuggestions()) {
            System.out.println(" - " + s);
        }
        System.out.println();
    }
}