package Control.observer_pattern;

import Entity.Asset;

public class UserNotification implements Observer {
    @Override
    public void update(String userId, Asset asset, String action) {
        System.out.printf(
                "Notify user %s: asset %s was %s.%n",
                userId, asset.getassetId(), action);
    }
}
