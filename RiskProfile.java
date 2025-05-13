package Entity;

import java.util.HashMap;
import java.util.Map;

public class RiskProfile {
    private int score;
    private Map<String, Double> breakdown = new HashMap<>();
    private String userId;
    private String riskProfileId;

    public RiskProfile(int score, Map<String, Double> breakdown, String userId, String riskProfileId) {
        this.score = score;
        this.breakdown = breakdown;
        this.userId = userId;
        this.riskProfileId = riskProfileId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setBreakdown(Map<String, Double> breakdown) {
        this.breakdown = breakdown;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRiskProfileId(String riskProfileId) {
        this.riskProfileId = riskProfileId;
    }

    public int getScore() {
        return score;
    }

    public Map<String, Double> getBreakdown() {
        return breakdown;
    }

    public String getUserId() {
        return userId;
    }

    public String getRiskProfileId() {
        return riskProfileId;
    }
}
