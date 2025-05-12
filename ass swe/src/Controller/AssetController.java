package Controller;

import Entity.Asset;
import java.util.List;
import java.util.ArrayList;

public class AssetController {

    private List<Asset> assets = new ArrayList<>();

    public void addAsset(Asset asset) {
        assets.add(asset);
        System.out.println("Asset added: " + asset.getAssetName());
    }

    public List<Asset> getAssets() {
        return assets;
    }
}