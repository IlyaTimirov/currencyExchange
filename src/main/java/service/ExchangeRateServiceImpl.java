package service;

import dao.CurrencyDao;
import dao.CurrencyDaoImpl;
import dao.ExchangeRateDao;
import dao.ExchangeRateDaoImpl;
import entity.ExchangeRate;
import exception.notfound.NotCurrencyPairException;

import java.util.List;

public class ExchangeRateServiceImpl implements ExchangeRateService{
    private final ExchangeRateDao exchangeRateDao = new ExchangeRateDaoImpl();
    @Override
    public ExchangeRate addExchangeRates(ExchangeRate exchangeRate) {
        return exchangeRateDao.addExchangeRates(exchangeRate);
    }

    @Override
    public ExchangeRate updateExchangeRates(ExchangeRate exchangeRate) {
        return exchangeRateDao.updateExchangeRates(exchangeRate);
    }

    @Override
    public void removeExchangeRates(Long id) {

    }

    @Override
    public List<ExchangeRate> getFindAll() {
        return exchangeRateDao.getFindAll();
    }

    @Override
    public ExchangeRate getFindExchangeRate(String base, String target) {
        if(exchangeRateDao.getFindExchangeRate(base, target).isEmpty()){
            throw new NotCurrencyPairException();
        }
        return exchangeRateDao.getFindExchangeRate(base, target).get();
    }

}
