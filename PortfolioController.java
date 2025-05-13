package Control;

import Entity.Asset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PortfolioController {
    private final IIntegrationService integrationService;
    private final Map<String, List<Asset>> userPortfolios = new HashMap<>();

    public PortfolioController(IIntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    public PortfolioController() {
        this(new IntegrationController());
    }

    public List<Asset> viewPortfolio(String userId) {
        if (userPortfolios.containsKey(userId)) {
            return userPortfolios.get(userId);
        }

        else {
            System.out.println("No portfolio found for user: " + userId);
            return new ArrayList<>(); // return empty list if not found
        }
    }

    public boolean addAsset(String userId, Asset asset) {
        if (userPortfolios.containsKey(userId)) {
            userPortfolios.get(userId).add(asset);
            System.out.println("Asset added to portfolio for user: " + userId);
        }

        else {
            List<Asset> assets = new ArrayList<>();
            assets.add(asset);
            userPortfolios.put(userId, assets);
            System.out.println("New portfolio created and asset added for user: " + userId);
        }
        return true;
    }

    public boolean removeAsset(String userId, Asset asset) {
        if (userPortfolios.containsKey(userId)) {
            List<Asset> assets = userPortfolios.get(userId);
            if (assets.remove(asset)) {
                System.out.println("Asset removed from portfolio for user: " + userId);
                return true;
            }

            else {
                System.out.println("Asset not found in portfolio for user: " + userId);
                return false;
            }
        }

        else {
            System.out.println("No portfolio found for user: " + userId);
            return false;
        }
    }

    public boolean editAsset(String userId, Asset oldAsset, Asset newAsset) {
        if (userPortfolios.containsKey(userId)) {
            List<Asset> assets = userPortfolios.get(userId);
            int index = assets.indexOf(oldAsset);
            if (index != -1) {
                assets.set(index, newAsset);
                System.out.println("Asset updated in portfolio for user: " + userId);
                return true;
            }

            else {
                System.out.println("Asset not found in portfolio for user: " + userId);
                return false;
            }
        }

        else {
            System.out.println("No portfolio found for user: " + userId);
            return false;
        }
    }

    public void syncWithBank(String userId) {
        List<Asset> externalAssets = integrationService.fetchBank(userId);
        for (Asset a : externalAssets) {
            addAsset(userId, a);
        }
        System.out.println("Bank assets synced for user: " + userId);
    }

}
