package Control;

import Entity.Asset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegrationController implements IIntegrationService {
    @Override
    public List<Asset> fetchBank(String userId) {
        List<Asset> assets = new ArrayList<>();
        assets.add(new Asset("BankAsset1", "BA001", 2.0, "2024-01-01", userId, 1.0));
        assets.add(new Asset("BankAsset2", "BA002", 1.5, "2023-12-15", userId, 1.0));
        return assets;
    }

    @Override
    public Map<String, Double> fetchPrices(List<String> symbols) {
        Map<String, Double> prices = new HashMap<>();
        for (String symbol : symbols) {
            prices.put(symbol, 100.0);
        }
        return prices;
    }
}
