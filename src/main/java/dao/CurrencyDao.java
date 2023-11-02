package dao;

import entity.Currency;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CurrencyDao {
    Currency add(Currency currency) throws ClassNotFoundException, SQLException;

    void update(Currency currency);

    void removeCurrency(Long id);

    Currency getById(Long id);

    List<Currency> getFindAll();

    Optional<Currency> getByCode(String code);
}
