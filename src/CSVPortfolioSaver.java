import java.io.*;
import java.util.Locale;
import java.util.List;
import Entity.Asset;

class CSVPortfolioSaver {
    public static void savePortfolio(List<Asset> assets, String userId) {
        String fileName = "Portfolio_" + userId + ".csv";

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(fileName), "UTF-8"))) {

            // Add UTF-8 BOM to help Excel with encoding
            writer.write('\ufeff');

            // Write CSV header (each column separated by commas)
            writer.write("AssetID,Name,Type,Quantity,Price,PurchaseDate,UserID\n");

            // Write each asset
            for (Asset asset : assets) {
                String line = String.format(Locale.US,
                        "%s,%s,%s,%.2f,%.2f,%s,%s\n",
                        asset.getassetId(),
                        asset.getassetName(),
                        asset.getType(),
                        asset.getquantity(),
                        asset.getPrice(),
                        asset.getpurchaseDate(),
                        asset.getuserId());

                writer.write(line);
            }

            System.out.println("Portfolio saved to: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving portfolio: " + e.getMessage());
        }
    }
}