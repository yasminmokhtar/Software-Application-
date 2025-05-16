
// UserStorage.java
import java.io.*;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    private static final String CSV_FILE = "users.csv";
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean emailExists(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = parseCsvLine(line);
                if (parts.length >= 1 && parts[0].equalsIgnoreCase(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // File not found = no users yet
        }
        return false;
    }

    public static boolean saveUser(String email, String password, String name, String userId) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(CSV_FILE, true)))) {
            out.println(String.format("%s,%s,%s,%s",
                    escapeCsv(email),
                    escapeCsv(password),
                    escapeCsv(name),
                    escapeCsv(userId)));
            return true;
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
            return false;
        }
    }

    public static String validateLogin(String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = parseCsvLine(line);
                if (parts.length >= 4 &&
                        parts[0].equalsIgnoreCase(email) &&
                        parts[1].equals(password)) {
                    return parts[3]; // Return user ID
                }
            }
        } catch (IOException e) {
            // No users file
        }
        return null;
    }

    private static String[] parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                values.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        values.add(sb.toString());
        return values.toArray(new String[0]);
    }

    private static String escapeCsv(String input) {
        if (input.contains(",") || input.contains("\"")) {
            return "\"" + input.replace("\"", "\"\"") + "\"";
        }
        return input;
    }
}