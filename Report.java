package Entity;

public class Report {
    private String userId;
    private String reportId;
    private String date;

    public Report(String userId, String reportId, String date) {
        this.userId = userId;
        this.reportId = reportId;
        this.date = date;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public String getReportId() {
        return reportId;
    }

    public String getDate() {
        return date;
    }
}
