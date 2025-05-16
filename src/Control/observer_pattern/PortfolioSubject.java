package Control.observer_pattern;

import Entity.Asset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PortfolioSubject {
    private final String userId;
    private final List<Asset> assets = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    public PortfolioSubject(String userId) {
        this.userId = userId;
    }

    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    private void notifyObservers(Asset asset, String action) {
        for (Observer o : observers) {
            o.update(userId, asset, action);
        }
    }

    public void addAsset(Asset asset) {
        assets.add(asset);
        notifyObservers(asset, "added");
    }

    public void removeAsset(Asset asset) {
        if (assets.remove(asset)) {
            notifyObservers(asset, "removed");
        }
    }

    public List<Asset> getAssets() {
        return Collections.unmodifiableList(assets);
    }
}
