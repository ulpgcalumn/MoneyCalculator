package moneycalculator.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import moneycalculator.ui.CurrencyDialog;
import moneycalculator.model.Currency;
import moneycalculator.model.CurrencySet;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {

    private Currency currency;

    public SwingCurrencyDialog() {
        this.add(createComboBox());
        
    }

    private JComboBox createComboBox() {
        final JComboBox currencyBox = new JComboBox();
        for (Currency item : CurrencySet.getInstance())
            currencyBox.addItem(item);
        currency = CurrencySet.getInstance().iterator().next();
        currencyBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency = (Currency) currencyBox.getSelectedItem();
            }
        });
        return currencyBox;
    }

    @Override
    public Currency getCurrency() {
        return this.currency;
    }
}