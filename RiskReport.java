package Control.Factory_pattern;

import Control.RiskController;
import Entity.RiskProfile;

public class RiskReport implements Report {
    private final RiskController riskController;

    public RiskReport(RiskController riskController) {
        this.riskController = riskController;
    }

    @Override
    public void generate(String userId) {
        RiskProfile r = riskController.getRisk(userId);
        System.out.println("=== Risk Profile ===");
        System.out.println("Score: " + r.getScore());
        System.out.println("Volatility: " + (r.getVolatility() * 100) + "%");
        System.out.println("Liquidity: " + (r.getLiquidity() * 100) + "%");
        System.out.println("Diversification: " + (r.getDiversification() * 100) + "%");
        System.out.println();
    }
}
