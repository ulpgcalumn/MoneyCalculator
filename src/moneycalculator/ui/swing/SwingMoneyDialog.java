package moneycalculator.ui.swing;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import moneycalculator.ui.CurrencyDialog;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.model.Money;
import moneycalculator.model.Number;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {

    private double amount;
    private CurrencyDialog currency;

    public SwingMoneyDialog() {
        super(new FlowLayout(FlowLayout.LEFT));
        this.createComponents();
    }

    private void createComponents() {
        this.add(createTextField());
        currency = createCurrencyDialog();
        this.add((Component) currency);
    }

    private JTextField createTextField() {
        final JTextField field = new JTextField(10);
        field.setText("0.0");
        field.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    amount = Double.parseDouble(field.getText());
                } catch (NumberFormatException exception) {
                    amount = 0;
                }
                System.out.println(amount);
            }
        });
        return field;
    }

    private CurrencyDialog createCurrencyDialog() {
        CurrencyDialog currencyDialog = new SwingCurrencyDialog();
        return currencyDialog;
    }

    @Override
    public Money getMoney() {
        return new Money(new Number(amount), this.currency.getCurrency());
    }
}