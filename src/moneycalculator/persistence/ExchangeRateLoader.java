package moneycalculator.persistence;

import java.util.Date;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;

public interface ExchangeRateLoader {
    
    public ExchangeRate load(Currency fromCurrency, Currency toCurrency);
}