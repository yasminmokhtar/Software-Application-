package Control.Factory_pattern;

public class ReportManager {
    private final ReportFactory reportFactory;

    public ReportManager(ReportFactory reportFactory) {
        this.reportFactory = reportFactory;
    }

    public void generateAll(String userId) {
        String[] types = { "portfolio", "risk", "compliance", "goals", "recommendation" };
        for (String t : types) {
            try {
                Report rpt = reportFactory.createReport(t);
                rpt.generate(userId);
            } catch (Exception e) {
                System.err.println("Error generating " + t + " report: " + e.getMessage());
            }
        }
    }
}