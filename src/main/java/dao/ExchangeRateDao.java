package dao;

import entity.ExchangeRate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateDao {
    ExchangeRate addExchangeRates(ExchangeRate exchangeRate);

    ExchangeRate updateExchangeRates(ExchangeRate exchangeRate);

    void removeExchangeRates(Long id);

    List<ExchangeRate> getFindAll();

    Optional<ExchangeRate> getFindExchangeRate(String base, String target);

}
