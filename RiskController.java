package Control;

import Entity.RiskProfile;
import java.util.HashMap;
import java.util.Map;

public class RiskController {

    // Dummy storage for user risk profiles (could be replaced with database later)
    private Map<String, RiskProfile> riskProfiles;

    public RiskController() {
        this.riskProfiles = new HashMap<>();
    }

    // Get the RiskProfile for a user by userId
    public RiskProfile getRisk(String userId) {
        if (riskProfiles.containsKey(userId)) {
            System.out.println("Risk profile retrieved for user: " + userId);
            return riskProfiles.get(userId);
        }

        else {
            // Generate a dummy risk profile for demonstration
            Map<String, Double> breakdown = new HashMap<>();
            breakdown.put("Volatility", 0.6);
            breakdown.put("Liquidity", 0.3);
            breakdown.put("Diversification", 0.1);

            RiskProfile riskProfile = new RiskProfile(55, breakdown, userId, "RISK-" + System.currentTimeMillis());
            riskProfiles.put(userId, riskProfile);

            System.out.println("New risk profile generated for user: " + userId);
            return riskProfile;
        }
    }
}
