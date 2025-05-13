import java.util.*;

import Entity.*;
import Control.*;
import Control.observer_pattern.*;
import Control.Factory_pattern.*;

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
                } else {
                    System.out.println("3) Add Asset");
                    System.out.println("4) Edit Asset");
                    System.out.println("5) Remove Asset");
                    System.out.println("6) View Portfolio");
                    System.out.println("7) View Goals");
                    System.out.println("8) Calculate Zakat");
                    System.out.println("9) Generate All Reports");
                    System.out.println("0) Log out / Exit");
                }
                System.out.print("Choose an option: ");
                String choice = scanner.nextLine().trim();

                if (currentUser == null) {
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
                        default:
                            System.out.println("Invalid option.");
                    }
                } else {
                    switch (choice) {
                        case "3":

                            System.out.println("=== Add New Asset ===");
                            System.out.println("Select type: Stocks, Real Estate, Crypto, Gold");
                            String assetType = scanner.nextLine().trim();
                            System.out.print("Asset Name: ");
                            String assetName = scanner.nextLine().trim();
                            System.out.print("Quantity (>0): ");
                            String qtyInput = scanner.nextLine().trim();
                            System.out.print("Purchase Date (YYYY-MM-DD): ");
                            String purchaseDate = scanner.nextLine().trim();

                            // Validation
                            if (assetName.isEmpty()
                                    || assetType.isEmpty()
                                    || purchaseDate.isEmpty()
                                    || (!assetType.equalsIgnoreCase("Stocks")
                                            && !assetType.equalsIgnoreCase("Real Estate")
                                            && !assetType.equalsIgnoreCase("Crypto")
                                            && !assetType.equalsIgnoreCase("Gold"))) {
                                System.out.println("Please fill in all required fields.");
                                break;
                            }
                            double quantity;
                            try {
                                quantity = Double.parseDouble(qtyInput);
                                if (quantity <= 0)
                                    throw new NumberFormatException();
                            } catch (NumberFormatException e) {
                                System.out.println("Please fill in all required fields.");
                                break;
                            }

                            // Create and add
                            String assetId = "A-" + System.currentTimeMillis();
                            Asset asset = new Asset(assetName, assetId, quantity, purchaseDate, currentUser);
                            asset.setType(assetType);
                            portfolioCtrl.addAsset(currentUser, asset);
                            System.out.println("Asset added successfully.");
                            break;
                        case "0":
                            System.out.println("Goodbye!");
                            return; // scanner will be closed automatically
                        default:
                            System.out.println("Invalid option.");
                    }
                }
            }
        }
    }
}
