package moneycalculator.ui.console;

import java.io.BufferedReader;
import java.io.IOException;
import moneycalculator.ui.CurrencyDialog;
import moneycalculator.model.Currency;
import moneycalculator.model.CurrencySet;

public class ConsoleCurrencyDialog implements CurrencyDialog {

    private BufferedReader reader;
    private Currency currency;

    public ConsoleCurrencyDialog(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    public void execute(String message) throws Exception {
        while (true) {
            try {
                this.currency = CurrencySet.getInstance().search(readCurrency(reader, message));
                break;
            } catch (CurrencySet.CurrencyNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (CurrencySet.MultipleCurrencyException ex) {
                showCurrencies(ex.getMessage());
            } catch (IOException ex) {
                System.out.println("An exception has been catched.");
            }
        }
    }

    private String readCurrency(BufferedReader reader, String string) throws IOException, Exception {
        System.out.print(string);
        String currencyReaded = reader.readLine();
        if(currencyReaded.equalsIgnoreCase("exit")) throw new Exception("exit");
        return currencyReaded;
    }

    private void showCurrencies(String msg) {
        System.out.println("Divisas disponibles: ");
        Currency[] list = CurrencySet.getInstance().currencyList(msg);
        for (int i = 0; i < list.length; i++) {
            System.out.println("--------------------" + list[i]);
        }
    }
}