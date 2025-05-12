package Controller;

import Entity.BankAccount;
import java.util.HashMap;
import java.util.Map;

public class BankConnector {

    private Map<String, BankAccount> bankAccounts = new HashMap<>();

    public boolean verifyCard(String cardNumber) {
        // Simulate card verification (For simplicity, let's assume cards starting with "4" are valid)
        return cardNumber.startsWith("4");
    }

    public void sendOTP() {
        System.out.println("OTP sent to the user's phone.");
    }

    public boolean verifyOTP(String inputOtp) {
        // Simulate OTP verification
        String validOtp = "1234";
        return validOtp.equals(inputOtp);
    }

    public void linkBankAccount(String userId, String cardNumber, String otp) {
        if (verifyCard(cardNumber)) {
            sendOTP();
            if (verifyOTP(otp)) {
                BankAccount bankAccount = new BankAccount(cardNumber);
                bankAccount.setVerified(true);
                bankAccounts.put(userId, bankAccount);
                System.out.println("Bank account linked for user: " + userId);
            } else {
                System.out.println("OTP verification failed.");
            }
        } else {
            System.out.println("Card verification failed.");
        }
    }
}