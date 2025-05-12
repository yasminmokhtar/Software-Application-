package Entity;

public class BankAccount {
    private String cardNumber;
    private boolean isVerified;

    // Constructor
    public BankAccount(String cardNumber) {
        this.cardNumber = cardNumber;
        this.isVerified = false;
    }

    // Getters and Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
