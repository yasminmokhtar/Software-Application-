package Control;

import Entity.Asset;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Locale;

public class PortfolioController {
    private final Map<String, List<Asset>> userPortfolios = new HashMap<>();
    private static final String BASE_PATH = "data/portfolios/";
    private final IIntegrationService integrationService;

    public List<Asset> viewPortfolio(String userId) {
        if (!userPortfolios.containsKey(userId)) {
            loadPortfolio(userId);
        }
        return userPortfolios.getOrDefault(userId, new ArrayList<>());
    }

    public boolean addAsset(String userId, Asset asset) {
        List<Asset> assets = userPortfolios.computeIfAbsent(userId, k -> new ArrayList<>());
        assets.add(asset);
        savePortfolio(userId);
        return true;
    }

    public boolean removeAsset(String userId, Asset asset) {
        if (!userPortfolios.containsKey(userId))
            return false;

        List<Asset> assets = userPortfolios.get(userId);
        boolean removed = assets.remove(asset);
        if (removed)
            savePortfolio(userId);
        return removed;
    }

    // Edit Asset (Now with automatic saving)
    public boolean editAsset(String userId, Asset oldAsset, Asset newAsset) {
        if (!userPortfolios.containsKey(userId))
            return false;

        List<Asset> assets = userPortfolios.get(userId);
        int index = assets.indexOf(oldAsset);
        if (index == -1)
            return false;

        assets.set(index, newAsset);
        savePortfolio(userId);
        return true;
    }

    private void loadPortfolio(String userId) {
        File file = new File(BASE_PATH + userId + ".csv");
        if (!file.exists())
            return;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        StandardCharsets.UTF_8 // Specify encoding here
                ))) {

            List<Asset> assets = new ArrayList<>();
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Asset asset = new Asset(
                        parts[1], // name
                        parts[0], // assetId
                        Double.parseDouble(parts[3]), // quantity
                        parts[4], // purchaseDate
                        userId,
                        Double.parseDouble(parts[2]) // price
                );
                asset.setType(parts[5]);
                assets.add(asset);
            }
            userPortfolios.put(userId, assets);
        } catch (IOException e) {
            System.err.println("Error loading portfolio: " + e.getMessage());
        }
    }

    private void savePortfolio(String userId) {
        new File(BASE_PATH).mkdirs();
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream(BASE_PATH + userId + ".csv"),
                        StandardCharsets.UTF_8))) {

            out.println("AssetID,Name,Price,Quantity,PurchaseDate,Type");
            for (Asset asset : userPortfolios.get(userId)) {
                String line = String.format(Locale.US, // Add Locale.US here
                        "%s,%s,%.2f,%.2f,%s,%s",
                        escapeCsv(asset.getassetId()),
                        escapeCsv(asset.getassetName()),
                        asset.getPrice(),
                        asset.getquantity(),
                        escapeCsv(asset.getpurchaseDate()),
                        escapeCsv(asset.getType()));
                out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error saving portfolio: " + e.getMessage());
        }
    }

    public PortfolioController(IIntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    public PortfolioController() {
        this(new IntegrationController());
    }

    private String escapeCsv(String input) {
        if (input.contains(",") || input.contains("\"")) {
            return "\"" + input.replace("\"", "\"\"") + "\"";
        }
        return input;
    }

}