package Control.Factory_pattern;

import Control.PortfolioController;
import Entity.Asset;

public class PortfolioReport implements Report {
    private final PortfolioController portfolioController;

    public PortfolioReport(PortfolioController portfolioController) {
        this.portfolioController = portfolioController;
    }

    @Override
    public void generate(String userId) {
        System.out.println("=== Portfolio Assets ===");
        for (Asset a : portfolioController.viewPortfolio(userId)) {
            System.out.println(" - " + a.getassetName() + ", Qty: " + a.getquantity());
        }
        System.out.println();
    }
}
