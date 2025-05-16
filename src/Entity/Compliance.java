package Entity;

public class Compliance {
    private String resultId;
    private String assetId;
    private String status;
    private String action;

    public Compliance(String resultId, String assetId, String status, String action) {
        this.resultId = resultId;
        this.assetId = assetId;
        this.status = status;
        this.action = action;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResultId() {
        return resultId;
    }

    public String getAssetId() {
        return assetId;
    }

    public String getStatus() {
        return status;
    }

    public String getAction() {
        return action;
    }
}
