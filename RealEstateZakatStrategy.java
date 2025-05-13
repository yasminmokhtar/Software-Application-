package Control.Strategy_pattern;

public class RealEstateZakatStrategy implements ZakatStrategy {
    @Override
    public double calculate(double amount) {
        return amount * 0.015; // 1.5%
    }
}