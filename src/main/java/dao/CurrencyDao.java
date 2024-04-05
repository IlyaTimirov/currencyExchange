package dao;

import entity.Currency;
import exception.already.CurrencyAlreadyExistsException;
import exception.notfound.NotFoundCurrencyException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyDao implements Dao {

    public static final String FIND_ALL_CURRENCIES = "SELECT * FROM currencies";
    public static final String BY_ID_CURRENCIES = "SELECT * FROM currencies WHERE id=?";
    public static final String BY_CODE_CURRENCIES = "SELECT * FROM currencies WHERE Code=?";
    public static final String SAVE_CURRENCIES = "INSERT INTO currencies (Code, FullName, Sign) VALUES (?,?,?)";

    public Currency save(Currency currency) {
        return execute(SAVE_CURRENCIES, statement -> {
            try {
                statement.setString(1, currency.getCode());
                statement.setString(2, currency.getFullName());
                statement.setString(3, currency.getSign());
                statement.executeUpdate();
                currency.setId(statement.getGeneratedKeys().getLong(1));
            } catch (SQLException e) {
                if (e.getErrorCode() == 19) {
                    throw new CurrencyAlreadyExistsException();
                }
                throw new RuntimeException(e);
            }
            return currency;
        });
    }

    public Optional<Currency> findByCode(String code) {
        return execute(BY_CODE_CURRENCIES, statement -> {
            try {
                statement.setString(1, code);
                return Optional.of(parseCurrencyFromResult(statement.executeQuery()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public Currency findById(Long id) {
        return execute(BY_ID_CURRENCIES, statement -> {
            try {
                statement.setLong(1, id);
                if (!statement.executeQuery().next()) {
                    throw new NotFoundCurrencyException();
                }
                return parseCurrencyFromResult(statement.executeQuery());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();
        return execute(FIND_ALL_CURRENCIES, statement -> {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    currencies.add(parseCurrencyFromResult(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return currencies;
        });
    }

    private Currency parseCurrencyFromResult(ResultSet resultSet) throws SQLException {
        Currency currency = new Currency();
        currency.setId(resultSet.getLong("Id"));
        currency.setCode(resultSet.getString("Code"));
        currency.setFullName(resultSet.getString("FullName"));
        currency.setSign(resultSet.getString("Sign"));
        return currency;
    }
}
