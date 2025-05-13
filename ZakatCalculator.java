package Control.Strategy_pattern;

public class ZakatCalculator {
    private ZakatStrategy strategy;

    public ZakatCalculator(ZakatStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(ZakatStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateZakat(double amount) {
        return strategy.calculate(amount);
    }
}
