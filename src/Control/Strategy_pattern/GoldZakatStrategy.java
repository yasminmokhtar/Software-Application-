package Control.Strategy_pattern;

public class GoldZakatStrategy implements ZakatStrategy {
    @Override
    public double calculate(double amount) {
        return amount * 0.025; // 2.5%
    }
}
