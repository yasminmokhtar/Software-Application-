package Controller;

import Entity.RiskProfile;
import java.util.HashMap;
import java.util.Map;

public class RiskController {
    private Map<String, RiskProfile> riskProfiles = new HashMap<>();

    // تعيين ملف تعريف المخاطر
    public void setRiskProfile(String userId, RiskProfile profile) {
        riskProfiles.put(userId, profile);
        System.out.println("Risk profile set for user: " + userId);
    }

    // الحصول على ملف تعريف المخاطر
    public RiskProfile getRisk(String userId) {
        RiskProfile profile = riskProfiles.get(userId);
        if (profile != null) {
            System.out.println("Risk score for user " + userId + ": " + profile.getScore());
            return profile;
        } else {
            System.out.println("No risk profile found for user: " + userId);
            return null;
        }
    }
}