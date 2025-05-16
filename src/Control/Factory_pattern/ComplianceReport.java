package Control.Factory_pattern;

import Control.ComplianceController;
import Entity.Asset;
import java.util.List;

public class ComplianceReport implements Report {
    private final ComplianceController complianceController;

    public ComplianceReport(ComplianceController complianceController) {
        this.complianceController = complianceController;
    }

    @Override
    public void generate(String userId) {
        List<Asset> halal = complianceController.getHalal(userId);
        System.out.println("=== Halal Assets (" + halal.size() + ") ===");
        for (Asset a : halal) {
            System.out.println(" - " + a.getassetName() + " (ID:" + a.getassetId() + ")");
        }
        System.out.println();
    }
}
