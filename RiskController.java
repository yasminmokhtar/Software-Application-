package Control;

import Entity.RiskProfile;

public class RiskController {
    public RiskProfile getRisk(String userId) {
        // generate fixed sample profile
        int score = 55;
        double vol = 0.6;
        double liq = 0.3;
        double div = 0.1;
        String id = "RISK-" + System.currentTimeMillis();
        return new RiskProfile(score, vol, liq, div, userId, id);
    }
}
