package moneycalculator.model;

public class ExchangeRate {

    private Currency fromCurrency;
    private Currency toCurrency;
    private Number rate;

    public ExchangeRate(Currency fromCurrency, Currency toCurrency, Number rate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public Number getRate() {
        return rate;
    }
}
