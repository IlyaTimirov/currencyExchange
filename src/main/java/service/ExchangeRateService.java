package service;

import dto.ExchangeDTO;
import dto.ExchangeRateDTO;
import entity.ExchangeRate;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateService {
    ExchangeRate addExchangeRates(ExchangeRate exchangeRate);
    ExchangeRate updateExchangeRates(ExchangeRate exchangeRate);
    void removeExchangeRates(Long id);
    List<ExchangeRate> getFindAll();
    ExchangeRate getFindExchangeRate(String base, String target);
}
