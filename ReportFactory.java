// src/control/ReportFactory.java
package Control.Factory_pattern;

import Control.ComplianceController;
import Control.GoalsController;
import Control.PortfolioController;
import Control.RiskController;
import Control.RecommendationController;

public class ReportFactory {
    private final PortfolioController portfolioController;
    private final RiskController riskController;
    private final ComplianceController complianceController;
    private final GoalsController goalsController;
    private final RecommendationController recommendationController;

    public ReportFactory(
            PortfolioController portfolioController,
            RiskController riskController,
            ComplianceController complianceController,
            GoalsController goalsController,
            RecommendationController recommendationController) {
        this.portfolioController = portfolioController;
        this.riskController = riskController;
        this.complianceController = complianceController;
        this.goalsController = goalsController;
        this.recommendationController = recommendationController;
    }

    /**
     * Create a Report instance by type.
     * Supported types: "portfolio", "risk", "compliance", "goals",
     * "recommendation".
     */
    public Report createReport(String type) {
        switch (type.toLowerCase()) {
            case "portfolio":
                return new PortfolioReport(portfolioController);
            case "risk":
                return new RiskReport(riskController);
            case "compliance":
                return new ComplianceReport(complianceController);
            case "goals":
                return new GoalsReport(goalsController);
            case "recommendation":
                return new RecommendationReport(recommendationController);
            default:
                throw new IllegalArgumentException("Unknown report type: " + type);
        }
    }
}
