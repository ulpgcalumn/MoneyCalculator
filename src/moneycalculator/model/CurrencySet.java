package moneycalculator.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class CurrencySet implements Iterable<Currency> {

    private static CurrencySet instance;
    private static HashSet<Currency> set;

    private CurrencySet() {
        set = new HashSet<>();
    }

    public static CurrencySet getInstance() {
        if (instance == null) {
            instance = new CurrencySet();
        }
        return instance;
    }
    
    public boolean add(Currency currency){
        return set.add(currency);
    }
    

    public Currency get(String code) throws CurrencyNotFoundException {
        for (Currency currency : set) {
            if (currency.getCode().equalsIgnoreCase(code)) {
                return currency;
            }
        }
        throw new CurrencyNotFoundException("Error: Currency code not found");
    }

    public Currency search(String string) throws CurrencyNotFoundException, MultipleCurrencyException {
        for (Currency currency : set) {
            if (currency.getCode().equalsIgnoreCase(string)) return currency;
            if (currency.getSymbol().equalsIgnoreCase(string)) return currency;
            if (currency.getName().equalsIgnoreCase(string)) return currency;
            if (currency.getName().toLowerCase().contains(string.toLowerCase())) {
                Currency[] list = currencyList(string);
                if (list.length == 1) {
                    return list[0];
                } else {
                    throw new MultipleCurrencyException(currency.getName().toLowerCase());
                }
            }
        }
        throw new CurrencyNotFoundException("Error: Currency not found");
    }

    public Currency[] currencyList(String string) {
        ArrayList<Currency> list = new ArrayList<>();
        for (Currency currency : set) {
            if (currency.getName().toLowerCase().contains(string.toLowerCase()))
                list.add(currency);
        }
        return list.toArray(new Currency[0]);
    }

    @Override
    public Iterator<Currency> iterator() {
        return set.iterator();
    }

    public static class CurrencyNotFoundException extends Exception {

        public CurrencyNotFoundException(String message) {
            super(message);
        }
    }

    public static class MultipleCurrencyException extends Exception {

        public MultipleCurrencyException() {
            super();
        }

        private MultipleCurrencyException(String msg) {
            super(msg);
        }
    }
}