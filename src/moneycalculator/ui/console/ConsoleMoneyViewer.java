package moneycalculator.ui.console;

import moneycalculator.ui.MoneyViewer;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.model.Number;

public class ConsoleMoneyViewer implements MoneyViewer {

    public ConsoleMoneyViewer() {
    }

    @Override
    public void showMoney(Number sourceAmount, Currency fromCurrency, Money newMoney) {
        System.out.print(sourceAmount + " " + fromCurrency + " son ");
        System.out.print(newMoney.getAmount() + " ");
        System.out.println(newMoney.getCurrency() + "\n");
    }
}
