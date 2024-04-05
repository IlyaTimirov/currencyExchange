package service;

import dao.CurrencyDao;
import dto.ExchangeDto;
import exception.notfound.NotCurrencyPairException;
import utils.Mapper;

public class ExchangeService {
    private final static String USD = "USD";
    private final CurrencyDao currencyDao;
    private final ExchangeRateService exchangeService;
    private final Mapper mapper;

    public ExchangeService() {
        this.currencyDao = new CurrencyDao();
        this.exchangeService = new ExchangeRateService();
        this.mapper = new Mapper();
    }

    public ExchangeDto getExchange(String base, String target, String amount) throws NotCurrencyPairException{
        return mapper.toExchangeDto(base, target, amount, convertedAmount(base, target));
    }

    private double convertedAmount(String base, String target) throws NotCurrencyPairException{
        double amount;
        if (currencyDao.findByCode(base).isEmpty() ||
                currencyDao.findByCode(target).isEmpty() || base.equals(target)) {
            throw new NotCurrencyPairException();
        } else if (isPresentCurrencyPair(base, target)) {
            amount = getExchangeRate(base, target);
        } else if (isPresentCurrencyPair(target, base)) {
            amount = 1 / getExchangeRate(target, base);
        } else if (isPresentCurrencyPair(USD, base) && isPresentCurrencyPair(USD, target)) {
            amount = getQuotes(USD, base, USD, target);
        } else if (isPresentCurrencyPair(base, USD) && isPresentCurrencyPair(USD, target)) {
            double exchangeRate1 = getExchangeRate(base, USD);
            double exchangeRate2 = getExchangeRate(USD, target);
            amount = exchangeRate1 * exchangeRate2;
        } else if (isPresentCurrencyPair(base, USD) && isPresentCurrencyPair(target, USD)) {
            amount = getQuotes(base, USD, target, USD);
        } else {
            throw new NotCurrencyPairException();
        }
        return amount;
    }

    private boolean isPresentCurrencyPair(String base, String target) {
        return exchangeService.fetchBaseAndTarget(base, target) != null;
    }

    private double getQuotes(String base1, String target1, String base2, String target2) {
        double exchangeRate1 = getExchangeRate(base1, target1);
        double exchangeRate2 = getExchangeRate(base2, target2);
        return Double.max(exchangeRate1, exchangeRate2) / Double.min(exchangeRate1, exchangeRate2);
    }

    private double getExchangeRate(String base, String target) {
        return exchangeService.fetchBaseAndTarget(base, target).getRate().doubleValue();
    }
}
