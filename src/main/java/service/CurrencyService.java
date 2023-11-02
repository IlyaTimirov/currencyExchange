package service;

import entity.Currency;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    Currency add(Currency currency);

    void update(Currency currency);

    void removeCurrency(Long id);

    Currency getById(Long id);

    List<Currency> getFindAll();

    Currency getByCode(String code);

}
