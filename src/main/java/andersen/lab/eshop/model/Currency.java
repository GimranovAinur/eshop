package andersen.lab.eshop.model;

public enum Currency {

    RUB(1),

    USD(2),

    EUR(2.5);

    private double coefficient;

    private Currency(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }
}
