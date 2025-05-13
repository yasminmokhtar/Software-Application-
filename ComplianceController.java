package Control;

import Control.Strategy_pattern.ZakatCalculator;
import Control.Strategy_pattern.ZakatStrategy;
import Control.Strategy_pattern.GoldZakatStrategy;
import Control.Strategy_pattern.RealEstateZakatStrategy;
import Control.Strategy_pattern.StockZakatStrategy;

import Entity.Asset;
import Entity.Compliance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ComplianceController {
    private final PortfolioController portfolioController;
    private final Map<String, List<Compliance>> userCompliance = new HashMap<>();

    public ComplianceController(PortfolioController portfolioController) {
        this.portfolioController = portfolioController;
    }

    // Returns list of halal assets
    public List<Asset> getHalal(String userId) {
        List<Compliance> compliances = userCompliance.get(userId);
        List<Asset> assets = portfolioController.viewPortfolio(userId);
        List<Asset> halalAssets = new ArrayList<>();

        if (compliances == null || assets == null) {
            System.out.println("No compliance or asset data found for user: " + userId);
            return halalAssets;
        }

        Set<String> halalAssetIds = new HashSet<>();
        for (Compliance c : compliances) {
            if ("halal".equalsIgnoreCase(c.getStatus())) {
                halalAssetIds.add(c.getAssetId());
            }
        }
        for (Asset a : assets) {
            if (halalAssetIds.contains(a.getassetId())) {
                halalAssets.add(a);
            }
        }
        return halalAssets;
    }

    // Dynamically calculate zakat using Strategy pattern
    public double getZakat(String userId) {
        List<Asset> assets = portfolioController.viewPortfolio(userId);
        if (assets == null || assets.isEmpty()) {
            System.out.println("No assets found for user: " + userId);
            return 0.0;
        }

        double totalZakat = 0.0;
        for (Asset a : assets) {
            ZakatStrategy strategy;
            switch (a.getType().toLowerCase()) {
                case "gold":
                    strategy = new GoldZakatStrategy();
                    break;
                case "stock":
                    strategy = new StockZakatStrategy();
                    break;
                case "real-estate":
                    strategy = new RealEstateZakatStrategy();
                    break;
                default:
                    System.out.println("Unknown asset type: " + a.getType());
                    continue;
            }
            ZakatCalculator calc = new ZakatCalculator(strategy);
            totalZakat += calc.calculateZakat(a.getquantity());
        }
        return totalZakat;
    }
}
