import java.util.*;

import Entity.*;
import Control.*;
import Control.observer_pattern.*;
import Control.Factory_pattern.*;
import Control.Strategy_pattern.*;

public class App {
    public static void main(String[] args) {
        // Scanner is now auto-closed when main exits
        try (Scanner scanner = new Scanner(System.in)) {

            // 1. Bootstrap core services/controllers
            IIntegrationService integrationService = new IntegrationController();
            PortfolioController portfolioCtrl = new PortfolioController(integrationService);
            GoalsController goalsCtrl = new GoalsController();
            RiskController riskCtrl = new RiskController();
            ComplianceController complianceCtrl = new ComplianceController(portfolioCtrl);
            RecommendationController recCtrl = new RecommendationController();
            AuthenticationController authCtrl = new AuthenticationController();

            // 2. Observer setup
            PortfolioSubject portfolioSubject = new PortfolioSubject("");
            portfolioSubject.addObserver(new UserNotification());
            portfolioSubject.addObserver(new ReportService());

            // 3. ReportFactory & Manager
            ReportFactory reportFactory = new ReportFactory(
                    portfolioCtrl, riskCtrl, complianceCtrl, goalsCtrl, recCtrl);
            ReportManager reportManager = new ReportManager(reportFactory);

            // 4. Authentication state
            String currentUser = null;

            // 5. Main menu loop
            while (true) {
                System.out.println("\n=== InvestWise ===");
                if (currentUser == null) {
                    System.out.println("1) Sign up");
                    System.out.println("2) Log in");
                    System.out.println("3) Exit");
                    System.out.print("Choose an option: ");
                    String choice = scanner.nextLine().trim();

                    switch (choice) {
                        case "1":
                            System.out.println("=== Sign Up ===");
                            System.out.print("Name: ");
                            String name = scanner.nextLine().trim();

                            System.out.print("Email: ");
                            String email = scanner.nextLine().trim();

                            System.out.print("Choose a username: ");
                            String userId = scanner.nextLine().trim();

                            System.out.print("Password: ");
                            String password = scanner.nextLine(); // you may hide input in a real app

                            // simple validations
                            if (name.isEmpty() || email.isEmpty() || userId.isEmpty()
                                    || password.length() < 8 || !email.matches("\\S+@\\S+\\.\\S+")) {
                                System.out.println("Please enter valid information.");
                                break;
                            }

                            // attempt sign-up
                            User newUser = new User(name, email, userId);
                            newUser.setpassword(password);
                            if (authCtrl.signup(newUser)) {
                                System.out.println("Signed up successfully please log in.");
                            } else {
                                System.out.println("User already exists.");
                            }

                            break;
                        case "2":
                            System.out.println("=== Log In ===");
                            System.out.print("Email: ");
                            String loginEmail = scanner.nextLine().trim();

                            System.out.print("Password: ");
                            String loginPassword = scanner.nextLine();

                            // call the updated login(...)
                            User loggedInUser = authCtrl.login(loginEmail, loginPassword);
                            if (loggedInUser != null) {
                                currentUser = loggedInUser.getUserId();
                                // also rewire the PortfolioSubject to this user:
                                portfolioSubject = new PortfolioSubject(currentUser);
                                portfolioSubject.addObserver(new UserNotification());
                                portfolioSubject.addObserver(new ReportService());

                                System.out.println("Welcome, " + loggedInUser.getName() + "!");
                            }
                            break;
                        case "3":
                            System.out.println("Goodbye!");
                            System.exit(0);
                        default:
                            System.out.println("Invalid option.");
                    }
                } else {
                    System.out.println("1) Add Asset");
                    System.out.println("2) Edit Asset");
                    System.out.println("3) Remove Asset");
                    System.out.println("4) Calculate Zakat");
                    System.out.println("5) Generate All Reports");
                    System.out.println("6) Log out");
                    System.out.print("Choose an option: ");
                    String choice = scanner.nextLine().trim();

                    switch (choice) {
                        case "1": // Add Asset
                            System.out.println("=== Add New Asset ===");

                            // 1) Choose asset type by number
                            System.out.println("Select asset type:");
                            System.out.println(" 1) Stocks");
                            System.out.println(" 2) Real Estate");
                            System.out.println(" 3) Crypto");
                            System.out.println(" 4) Gold");
                            System.out.print("Type number [1-4]: ");
                            String typeChoice = scanner.nextLine().trim();

                            String assetType = null;
                            switch (typeChoice) {
                                case "1":
                                    assetType = "Stocks";
                                    break;
                                case "2":
                                    assetType = "Real Estate";
                                    break;
                                case "3":
                                    assetType = "Crypto";
                                    break;
                                case "4":
                                    assetType = "Gold";
                                    break;
                                default:
                                    System.out.println("Invalid selection. Returning to menu.");
                                    break;
                            }

                            if (assetType == null)
                                break;

                            // 2) Now collect the rest of the details
                            System.out.print("Asset Name: ");
                            String assetName = scanner.nextLine().trim();

                            System.out.print("Quantity (>0): ");
                            String qtyInput = scanner.nextLine().trim();

                            System.out.print("Purchase Date (YYYY-MM-DD): ");
                            String purchaseDate = scanner.nextLine().trim();

                            System.out.print("Price per unit: ");
                            double price = Double.parseDouble(scanner.nextLine().trim());

                            // 3) Validation
                            double quantity;
                            if (assetName.isEmpty() || purchaseDate.isEmpty()) {
                                System.out.println("Please fill in all required fields.");
                                break;
                            }
                            try {
                                quantity = Double.parseDouble(qtyInput);
                                if (quantity <= 0)
                                    throw new NumberFormatException();
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a positive numeric quantity.");
                                break;
                            }

                            // 4) Create and add the asset
                            String assetId = "A-" + System.currentTimeMillis();
                            Asset asset = new Asset(assetName, assetId, quantity, purchaseDate, currentUser, price);
                            asset.setType(assetType);
                            portfolioCtrl.addAsset(currentUser, asset);
                            System.out.println("Asset added successfully.");
                            break;
                        case "2":
                            // case “2”: Edit Asset
                            List<Asset> assetsToEdit = portfolioCtrl.viewPortfolio(currentUser);
                            if (assetsToEdit.isEmpty()) {
                                System.out.println("No assets to edit.");
                                break;
                            }

                            // 1) Show list
                            System.out.println("Select an asset to edit:");
                            for (int i = 0; i < assetsToEdit.size(); i++) {
                                Asset a = assetsToEdit.get(i);
                                System.out.println((i + 1) + ") "
                                        + a.getassetName()
                                        + " (" + a.getquantity() + " "
                                        + a.getType() + ")");

                            }
                            System.out.print("Enter number: ");
                            String editInput = scanner.nextLine().trim();

                            // 2) Parse & validate
                            int editIdx;
                            try {
                                editIdx = Integer.parseInt(editInput) - 1;
                                if (editIdx < 0 || editIdx >= assetsToEdit.size())
                                    throw new NumberFormatException();
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid selection.");
                                break;
                            }

                            // 3) Prompt for new values
                            Asset oldAsset = assetsToEdit.get(editIdx);
                            System.out.printf("Editing %s (ID:%s)%n", oldAsset.getassetName(), oldAsset.getassetId());

                            System.out.print("New Name (or press ENTER to keep \"" + oldAsset.getassetName() + "\"): ");
                            String newName = scanner.nextLine().trim();
                            if (newName.isEmpty())
                                newName = oldAsset.getassetName();

                            System.out.print("New Quantity (or press ENTER to keep " + oldAsset.getquantity() + "): ");
                            String newQtyInput = scanner.nextLine().trim();
                            double newQty;
                            if (newQtyInput.isEmpty()) {
                                newQty = oldAsset.getquantity();
                            } else {
                                try {
                                    newQty = Double.parseDouble(newQtyInput);
                                    if (newQty <= 0)
                                        throw new NumberFormatException();
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid quantity.");
                                    break;
                                }
                            }

                            System.out.print("New Purchase Date (YYYY-MM-DD) (or press ENTER to keep \""
                                    + oldAsset.getpurchaseDate() + "\"): ");
                            String newDate = scanner.nextLine().trim();
                            if (newDate.isEmpty())
                                newDate = oldAsset.getpurchaseDate();

                            System.out
                                    .print("New Price per unit (or press ENTER to keep " + oldAsset.getPrice() + "): ");
                            String newPriceInput = scanner.nextLine().trim();
                            double newPrice;
                            if (newPriceInput.isEmpty()) {
                                newPrice = oldAsset.getPrice();
                            } else {
                                try {
                                    newPrice = Double.parseDouble(newPriceInput);
                                    if (newPrice <= 0)
                                        throw new NumberFormatException();
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid price.");
                                    break;
                                }
                            }

                            // 4) Construct new asset & call edit
                            Asset newAsset = new Asset(
                                    newName,
                                    oldAsset.getassetId(),
                                    newQty,
                                    newDate,
                                    currentUser,
                                    newPrice);
                            newAsset.setType(oldAsset.getType());

                            boolean updated = portfolioCtrl.editAsset(currentUser, oldAsset, newAsset);
                            if (updated) {
                                System.out.println("Asset updated successfully.");
                            } else {
                                System.out.println("Failed to update asset.");
                            }
                            break;
                        case "3":
                            // case “3”: Remove Asset
                            List<Asset> assetsToRemove = portfolioCtrl.viewPortfolio(currentUser);
                            if (assetsToRemove.isEmpty()) {
                                System.out.println("No assets to remove.");
                                break;
                            }

                            // 1) Show list
                            System.out.println("Select an asset to remove:");
                            for (int i = 0; i < assetsToRemove.size(); i++) {
                                Asset a = assetsToRemove.get(i);
                                System.out.println((i + 1) + ") "
                                        + a.getassetName()
                                        + " (" + a.getquantity() + " "
                                        + a.getType() + ")");

                            }
                            System.out.print("Enter number: ");
                            String remInput = scanner.nextLine().trim();

                            // 2) Parse & validate
                            int remIdx;
                            try {
                                remIdx = Integer.parseInt(remInput) - 1;
                                if (remIdx < 0 || remIdx >= assetsToRemove.size())
                                    throw new NumberFormatException();
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid selection.");
                                break;
                            }

                            // 3) Remove
                            Asset remAsset = assetsToRemove.get(remIdx);
                            boolean removed = portfolioCtrl.removeAsset(currentUser, remAsset);
                            if (removed) {
                                System.out.println("Asset removed successfully.");
                            } else {
                                System.out.println("Failed to remove asset.");
                            }
                            break;
                        case "4": // User Story #8: Calculate Zakat
                            System.out.println("=== Calculate Zakat ===");
                            List<Asset> assetsForZakat = portfolioCtrl.viewPortfolio(currentUser);
                            if (assetsForZakat.isEmpty()) {
                                System.out.println("You have no assets, so no Zakat is due.");
                                break;
                            }

                            double totalZakat = 0.0;
                            System.out.println("Asset-by-asset Zakat breakdown:");
                            for (Asset a : assetsForZakat) {
                                ZakatStrategy strat;
                                String key = a.getType().toLowerCase();
                                if (key.equals("gold")) {
                                    strat = new GoldZakatStrategy();
                                } else if (key.equals("stocks")) {
                                    strat = new StockZakatStrategy();
                                } else if (key.equals("real-estate") || key.equals("real estate")) {
                                    strat = new RealEstateZakatStrategy();
                                } else {
                                    System.out.println(" - " + a.getassetName() + ": unknown type, skipped.");
                                    continue;
                                }

                                // Calculate on monetary value:
                                double assetValue = a.getquantity() * a.getPrice();
                                double zakatAmt = strat.calculate(assetValue);
                                totalZakat += zakatAmt;

                                System.out.println(
                                        " - " + a.getassetName() +
                                                " (value=" + assetValue + "): zakat=" + zakatAmt);
                            }

                            System.out.println("Total Zakat due: " + totalZakat);
                            break;

                        case "5":
                            System.out.println("=== Reports Menu ===");
                            System.out.println("1) Portfolio Report");
                            System.out.println("2) Risk Report");
                            System.out.println("3) Compliance Report");
                            System.out.println("4) Goals Report");
                            System.out.println("5) Recommendation Report");
                            System.out.println("6) Generate All Reports");
                            System.out.print("Choose an option [1-6]: ");
                            String rptChoice = scanner.nextLine().trim();

                            switch (rptChoice) {
                                case "1":
                                    reportFactory.createReport("portfolio").generate(currentUser);
                                    break;
                                case "2":
                                    reportFactory.createReport("risk").generate(currentUser);
                                    break;
                                case "3":
                                    reportFactory.createReport("compliance").generate(currentUser);
                                    break;
                                case "4":
                                    reportFactory.createReport("goals").generate(currentUser);
                                    break;
                                case "5":
                                    reportFactory.createReport("recommendation").generate(currentUser);
                                    break;
                                case "6":
                                    reportManager.generateAll(currentUser);
                                    break;
                                default:
                                    System.out.println("Invalid option, returning to main menu.");
                            }
                            break;

                        case "6":
                            // Log out
                            currentUser = null;
                            System.out.println("Logged out.");
                            break;
                        default:
                            System.out.println("Invalid option.");

                    }
                }
            }
        }
    }
}
