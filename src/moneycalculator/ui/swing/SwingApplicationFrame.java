package moneycalculator.ui.swing;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import moneycalculator.ui.ActionFactory;
import moneycalculator.ui.ApplicationFrame;
import moneycalculator.ui.CurrencyDialog;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.MoneyViewer;

public class SwingApplicationFrame extends JFrame implements ApplicationFrame {

    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;
    private MoneyViewer moneyViewer;
    private ActionFactory factory;

    public SwingApplicationFrame(ActionFactory factory) throws HeadlessException {
        super("Money Calculator");
        this.factory = factory;
        this.setSize(500, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.createComponents();
    }

    private void createComponents() {
        this.add(createMainPanel(), BorderLayout.CENTER);
        this.add(createToolBar(), BorderLayout.SOUTH);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(createInputDialog());
        panel.add(createOutputDialog());
        return panel;
    }

    private JPanel createInputDialog() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(createMoneyDialog());
        panel.add(createCurrencyDialog());
        return panel;
    }

    private JPanel createOutputDialog() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(createMoneyViewer());
        return panel;
    }

    private JPanel createToolBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(createButton("Calculate"));
        panel.add(createButton("Exit"));
        return panel;
    }

    private SwingMoneyDialog createMoneyDialog() {
        SwingMoneyDialog panel = new SwingMoneyDialog();
        this.moneyDialog = panel;
        return panel;
    }

    private SwingCurrencyDialog createCurrencyDialog() {
        SwingCurrencyDialog panel = new SwingCurrencyDialog();
        this.currencyDialog = panel;
        return panel;
    }

    private SwingMoneyViewer createMoneyViewer() {
        SwingMoneyViewer panel = new SwingMoneyViewer();
        this.moneyViewer = panel;
        return panel;
    }

    private JButton createButton(String action) {
        JButton button = new JButton(action);
        button.addActionListener((ActionListener) factory.createAction(action));
        return button;
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

    @Override
    public void execute() {
        this.setVisible(true);
    }
}