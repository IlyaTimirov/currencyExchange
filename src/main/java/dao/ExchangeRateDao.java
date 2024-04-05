package dao;


import entity.ExchangeRate;
import exception.notfound.NotFoundCurrencyException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExchangeRateDao implements Dao {
    public static final String SAVE_EXCHANGE_RATES = "insert into exchangeRates(BaseCurrencyId, TargetCurrencyId, Rate) values (?,?,?)";
    public static final String UPDATE_EXCHANGE_RATES = "UPDATE exchangeRates SET Rate=? WHERE id=?";
    public static final String FIND_ALL_EXCHANGE_RATES = "SELECT * FROM exchangeRates";
    public static final String FIND_CURRENCY_PAIR = "SELECT * FROM exchangeRates WHERE BaseCurrencyId=? AND TargetCurrencyId=?";

    public ExchangeRate save(ExchangeRate exchangeRate) {
        return execute(SAVE_EXCHANGE_RATES, statement -> {
            try {
                statement.setLong(1, exchangeRate.getBaseCurrencyId());
                statement.setLong(2, exchangeRate.getTargetCurrencyId());
                statement.setBigDecimal(3, exchangeRate.getRate());
                statement.executeUpdate();
                exchangeRate.setId(statement.getGeneratedKeys().getLong(1));
            } catch (SQLException e) {
                throw new RuntimeException();
            }
            return exchangeRate;
        });
    }

    public ExchangeRate update(ExchangeRate exchangeRate) {
        return execute(UPDATE_EXCHANGE_RATES, statement -> {
            try {
                statement.setBigDecimal(1, exchangeRate.getRate());
                statement.setLong(2, exchangeRate.getId());
                if (statement.executeUpdate() == 0) {
                    throw new NotFoundCurrencyException();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return exchangeRate;
        });
    }

    public List<ExchangeRate> findAll() {
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        return execute(FIND_ALL_EXCHANGE_RATES, statement -> {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    exchangeRates.add(parseExchangeRateFromResult(resultSet));
                }
                return exchangeRates;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Optional<ExchangeRate> findByBaseAndTarget(long baseId, long targetId) {
        return execute(FIND_CURRENCY_PAIR, statement -> {
            try {
                statement.setLong(1, baseId);
                statement.setLong(2, targetId);
                return Optional.ofNullable(parseExchangeRateFromResult(statement.executeQuery()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private ExchangeRate parseExchangeRateFromResult(ResultSet resultSet) {
        ExchangeRate exchangeRate = new ExchangeRate();
        try {
            exchangeRate.setId(resultSet.getLong("id"));
            exchangeRate.setBaseCurrencyId(resultSet.getLong("BaseCurrencyId"));
            exchangeRate.setTargetCurrencyId(resultSet.getLong("TargetCurrencyId"));
            exchangeRate.setRate(resultSet.getBigDecimal("Rate"));
            return exchangeRate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
