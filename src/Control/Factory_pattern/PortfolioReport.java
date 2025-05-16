package Control.Factory_pattern;

import Control.PortfolioController;
import Entity.Asset;
import java.io.*;

public class PortfolioReport implements Report {
    private final PortfolioController portfolioController;
    private static final String REPORT_PATH = "reports/";

    public PortfolioReport(PortfolioController portfolioController) {
        this.portfolioController = portfolioController;
    }

    @Override
    public void generate(String userId) {
        new File(REPORT_PATH).mkdirs();
        String fileName = REPORT_PATH + "portfolio_" + userId + ".csv";

        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println("Asset Name,Type,Quantity,Unit Price,Total Value,Purchase Date");
            double total = 0;

            for (Asset a : portfolioController.viewPortfolio(userId)) {
                double value = a.getquantity() * a.getPrice();
                total += value;
                out.println(String.format("%s,%s,%.2f,%.2f,%.2f,%s",
                        a.getassetName(),
                        a.getType(),
                        a.getquantity(),
                        a.getPrice(),
                        value,
                        a.getpurchaseDate()));
            }

            out.println("\nTotal Portfolio Value,,,," + total);
            System.out.println("Portfolio report generated: " + fileName);
        } catch (IOException e) {
            System.err.println("Error generating portfolio report: " + e.getMessage());
        }
    }
}