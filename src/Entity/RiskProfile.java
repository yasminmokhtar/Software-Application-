package Entity;

public class RiskProfile {
    private int score;
    private double volatility;
    private double liquidity;
    private double diversification;
    private String userId;
    private String riskProfileId;

    public RiskProfile(int score, double volatility, double liquidity, double diversification,
            String userId, String riskProfileId) {
        this.score = score;
        this.volatility = volatility;
        this.liquidity = liquidity;
        this.diversification = diversification;
        this.userId = userId;
        this.riskProfileId = riskProfileId;
    }

    public int getScore() {
        return score;
    }

    public double getVolatility() {
        return volatility;
    }

    public double getLiquidity() {
        return liquidity;
    }

    public double getDiversification() {
        return diversification;
    }

    public String getUserId() {
        return userId;
    }

    public String getRiskProfileId() {
        return riskProfileId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public void setLiquidity(double liquidity) {
        this.liquidity = liquidity;
    }

    public void setDiversification(double diversification) {
        this.diversification = diversification;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRiskProfileId(String riskProfileId) {
        this.riskProfileId = riskProfileId;
    }

}
