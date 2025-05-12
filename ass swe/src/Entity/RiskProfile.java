package Entity;

import java.util.Map;

public class RiskProfile {
    private int score;
    private Map<String, Double> breakdown;  // مثال: {"Volatility": 0.6, "Liquidity": 0.3}
    private String userId;
    private String riskId;

    public RiskProfile(int score, Map<String, Double> breakdown, String userId, String riskId) {
        this.score = score;
        this.breakdown = breakdown;
        this.userId = userId;
        this.riskId = riskId;
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

    public String getRiskId() {
        return riskId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setBreakdown(Map<String, Double> breakdown) {
        this.breakdown = breakdown;
    }
}