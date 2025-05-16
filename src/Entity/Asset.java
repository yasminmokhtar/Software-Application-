package Entity;

public class Asset {
    private String assetName;
    private String type;
    private String assetId;
    private double quantity;
    private String purchaseDate;
    private String userId;
    private double price;

    public Asset(String assetName, String assetId, double quantity, String purchaseDate, String userId, double price) {
        this.assetName = assetName;
        this.assetId = assetId;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.userId = userId;
        this.price = price;
    }

    public void setassetName(String assetName) {
        this.assetName = assetName;
    }

    public void setassetId(String assetId) {
        this.assetId = assetId;
    }

    public void setquantity(double quantity) {
        this.quantity = quantity;
    }

    public void setpurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getassetName() {
        return assetName;
    }

    public String getassetId() {
        return assetId;
    }

    public double getquantity() {
        return quantity;
    }

    public String getpurchaseDate() {
        return purchaseDate;
    }

    public String getuserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

}