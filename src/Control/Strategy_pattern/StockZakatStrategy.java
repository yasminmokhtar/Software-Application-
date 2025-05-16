package Control.Strategy_pattern;

public class StockZakatStrategy implements ZakatStrategy {
    @Override
    public double calculate(double amount) {
        return amount * 0.02; // 2%
    }
}