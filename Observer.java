package Control.observer_pattern;

import Entity.Asset;

public interface Observer {
    void update(String userId, Asset asset, String action);
}
