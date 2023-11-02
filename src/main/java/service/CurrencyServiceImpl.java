package service;

import dao.CurrencyDaoImpl;
import entity.Currency;
import exception.notfound.NotFoundCurrencyException;

import java.util.List;

public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyDaoImpl currencyDao = new CurrencyDaoImpl();

    @Override
    public Currency add(Currency currency) {
        return currencyDao.add(currency);
    }
    @Override
    public void update(Currency currency) {

    }

    @Override
    public void removeCurrency(Long id) {

    }

    @Override
    public Currency getById(Long id) {
        return currencyDao.getById(id);
    }

    @Override
    public Currency getByCode(String code) {
        if(currencyDao.getByCode(code).isEmpty()){
            throw new NotFoundCurrencyException();
        }
        return currencyDao.getByCode(code).get();
    }

    @Override
    public List<Currency> getFindAll() {
        return currencyDao.getFindAll();
    }
}
