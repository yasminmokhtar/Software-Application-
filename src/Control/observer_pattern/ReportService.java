package Control.observer_pattern;

import Entity.Asset;

public class ReportService implements Observer {
    @Override
    public void update(String userId, Asset asset, String action) {
        System.out.printf(
                "ReportService: logging %s of asset %s for user %s.%n",
                action, asset.getassetId(), userId);
    }
}
