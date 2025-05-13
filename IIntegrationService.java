package Control;

import Entity.Asset;

import java.util.List;
import java.util.Map;

public interface IIntegrationService {
    List<Asset> fetchBank(String userId);

    Map<String, Double> fetchPrices(List<String> symbols);
}
