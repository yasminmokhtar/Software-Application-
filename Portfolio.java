package Entity;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private String portfolioId;
    private String userId;
    private List<Asset> assets = new ArrayList<>();

    public Portfolio(String portfolioId, String userId) {
        this.portfolioId = portfolioId;
        this.userId = userId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public String getUserId() {
        return userId;
    }

    public List<Asset> getAssets() {
        return assets;
    }

}
