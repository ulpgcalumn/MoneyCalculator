package moneycalculator.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import moneycalculator.model.Currency;
import moneycalculator.model.CurrencySet;

public class DataBaseCurrencySetLoader implements CurrencySetLoader {

    private static DataBaseCurrencySetLoader instance;
    private static Connection connection;

    private DataBaseCurrencySetLoader(Connection connection) {
        DataBaseCurrencySetLoader.connection = connection;
    }

    public static void createInstance(Connection connection) {
        if (instance == null) {
            instance = new DataBaseCurrencySetLoader(connection);
        }
    }

    public static CurrencySetLoader getInstance() {
        return instance;
    }

    @Override
    public void load() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CAMBIO_EUR_A");
        CurrencySet set = CurrencySet.getInstance();

        set.add(new Currency("EUR", ""));
        while (resultSet.next()) {
            set.add(new Currency(resultSet.getString("DIVISA"), ""));
        }
    }
}