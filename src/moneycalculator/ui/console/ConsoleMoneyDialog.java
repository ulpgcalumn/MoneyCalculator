package moneycalculator.ui.console;

import java.io.BufferedReader;
import java.io.IOException;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.model.Number;

public class ConsoleMoneyDialog implements MoneyDialog {

    private BufferedReader reader;
    private Money money;
    private ConsoleCurrencyDialog currencyDialog;

    @Override
    public Money getMoney() {
        return money;
    }

    public ConsoleMoneyDialog(BufferedReader reader) {
        this.reader = reader;
        currencyDialog = new ConsoleCurrencyDialog(reader);
    }

    public void execute() throws Exception {
        Number amount;
        Currency currency;
        String input = "";
        while (true) {
            try {
                input = readAmount(reader);
                amount = Number.valueOf(input);
                currencyDialog.execute("Introduzca la divisa origen: ");
                currency = currencyDialog.getCurrency();
                this.money = new Money(amount, currency);
                break;
            } catch (NumberFormatException ex) {
                throw new Exception(input);
            } catch (IOException ex) {
                System.out.println("An exception has been catched.");
            }
        }
    }

    private static String readAmount(BufferedReader reader) throws IOException {
        System.out.print("Introduzca la cantidad de dinero: ");
        String amount = reader.readLine();
        return amount;
    }
}