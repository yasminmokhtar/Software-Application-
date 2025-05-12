package Entity;

public class Asset {
    private String assetId;
    private String assetName;
    private double value;
    private String type;

    // Constructor
    public Asset(String assetId, String assetName, double value, String type) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.value = value;
        this.type = type;
    }

    // Getters and Setters
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}