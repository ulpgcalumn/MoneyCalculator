package moneycalculator.ui.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import moneycalculator.ui.ActionFactory;
import moneycalculator.ui.ApplicationFrame;
import moneycalculator.ui.CurrencyDialog;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.MoneyViewer;
import moneycalculator.model.Currency;
import moneycalculator.model.CurrencySet;

public class ConsoleApplicationFrame implements ApplicationFrame {

    private ActionFactory factory;
    private Action exit;
    private Action calculate;
    private ConsoleMoneyDialog moneyDialog;
    private ConsoleCurrencyDialog currencyDialog;
    private ConsoleMoneyViewer moneyViewer;

    public ConsoleApplicationFrame(ActionFactory factory) {
        this.factory = factory;
        this.createActions();
        this.createComponents();
    }

    private void createActions() {
        this.exit = (Action) factory.createAction("Exit");
        this.calculate = (Action) factory.createAction("Calculate");
    }

    private void createComponents() {
        this.moneyDialog = new ConsoleMoneyDialog(new BufferedReader(new InputStreamReader(System.in)));
        this.currencyDialog = new ConsoleCurrencyDialog(new BufferedReader(new InputStreamReader(System.in)));
        this.moneyViewer = new ConsoleMoneyViewer();
    }

    @Override
    public void execute() {
        while (true) {
            showMessage();
            showCurrencies();
            try {
                moneyDialog.execute();
                currencyDialog.execute("Introduzca la divisa a convertir: ");
                this.calculate.execute();
            } catch (Exception ex) {
                if (ex.getMessage().equalsIgnoreCase("exit"))
                    this.exit.execute();
            }
        }
    }

    @Override
    public MoneyDialog getMoneyDialog() {
        return this.moneyDialog;
    }

    @Override
    public MoneyViewer getMoneyViewer() {
        return this.moneyViewer;
    }

    @Override
    public CurrencyDialog getCurrencyDialog() {
        return this.currencyDialog;
    }

    private void showMessage() {
        System.out.println("Escriba 'exit' para salir.");
    }

    private void showCurrencies() {
        System.out.println("Divisas disponibles: ");
        for (Currency item : CurrencySet.getInstance()){
            System.out.print(item + " ");
        }
        System.out.println("");
    }
}