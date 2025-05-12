package Controller;

import Entity.Asset;
import java.util.List;

public class ZakatController {

    public double calculateZakat(List<Asset> assets) {
        double totalValue = assets.stream().mapToDouble(Asset::getValue).sum();
        return totalValue * 0.025; // 2.5% zakat
    }

    public void generateZakatReport(List<Asset> assets) {
        double zakatAmount = calculateZakat(assets);
        System.out.println("Total Zakat calculated: " + zakatAmount);
        // Here you can add logic to generate PDF report
    }
}