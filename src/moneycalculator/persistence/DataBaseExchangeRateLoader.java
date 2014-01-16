package moneycalculator.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Number;

public class DataBaseExchangeRateLoader implements ExchangeRateLoader {

    private final Connection connection;

    public DataBaseExchangeRateLoader(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        if (from.getCode().equalsIgnoreCase(to.getCode()))
            return new ExchangeRate(from, to, new Number(1));

        if (from.getCode().equalsIgnoreCase("EUR"))
            return new ExchangeRate(from, to, getExchangeRate_EUR_to(to));
        else
            return new ExchangeRate(from, to,
                    getExchangeRate_from_EUR(from).multiplicate(getExchangeRate_EUR_to(to)));
    }

    private Number getExchangeRate_from_EUR(Currency from) {
        return new Number(1).divide(getExchangeRate_EUR_to(from));
    }

    private Number getExchangeRate_EUR_to(Currency currency) {
        if (currency.getCode().equalsIgnoreCase("EUR")) return new Number(1);
        String query = "SELECT * FROM CAMBIO_EUR_A WHERE DIVISA = '" + currency.getCode() + "'";
        return getRateFromDataBase(query);
    }

    private Number getRateFromDataBase(String query) {
        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            if (!result.next()) throw new SQLException();

            return new Number(result.getBigDecimal("CAMBIO").doubleValue());
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseExchangeRateLoader.class.getName()).log(Level.SEVERE, null, ex);
            return new Number(0);
        }
    }
}