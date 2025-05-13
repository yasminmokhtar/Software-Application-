package Entity;

public class Zakat {
    private String zakatId;
    private String assetId;
    private double amount;

    public Zakat(String zakatId, String assetId, double amount) {
        this.zakatId = zakatId;
        this.assetId = assetId;
        this.amount = amount;
    }

    public void setZakatId(String zakatId) {
        this.zakatId = zakatId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getZakatId() {
        return zakatId;
    }

    public String getAssetId() {
        return assetId;
    }

    public double getAmount() {
        return amount;
    }
}
