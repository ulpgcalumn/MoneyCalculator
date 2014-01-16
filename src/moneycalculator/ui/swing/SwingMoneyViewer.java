package moneycalculator.ui.swing;

import java.awt.FlowLayout;
import javax.swing.*;
import moneycalculator.ui.MoneyViewer;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.model.Number;

public class SwingMoneyViewer extends JPanel implements MoneyViewer {

    private JLabel area;
    private JLabel fromCurrencyLabel;
    private JLabel toCurrencyLabel;

    public SwingMoneyViewer() {
        super(new FlowLayout(FlowLayout.LEFT));
        this.createComponents();
        this.setVisible(true);
    }

    private void createComponents() {
        area = new JLabel("");
        fromCurrencyLabel = new JLabel("");
        toCurrencyLabel = new JLabel("");
        this.add(fromCurrencyLabel);
        this.add(area);
        this.add(toCurrencyLabel);
    }

    @Override
    public void showMoney(Number sourceAmount, Currency fromCurrency, Money newMoney) {
        fromCurrencyLabel.setText(sourceAmount.toString() + " " + fromCurrency + " son ");
        area.setText(newMoney.getAmount().toString());
        toCurrencyLabel.setText(newMoney.getCurrency().toString());
    }




}