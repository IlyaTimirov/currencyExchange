package service;

import dao.CurrencyDao;
import dao.CurrencyDaoImpl;
import dao.ExchangeRateDao;
import dao.ExchangeRateDaoImpl;
import dto.ExchangeDTO;
import entity.ExchangeRate;
import exception.notfound.NotCurrencyPairException;
import utils.Mapper;

import java.util.Optional;

public class ExchangeServiceImpl implements ExchangeService{
    private final static String USD = "USD";
    CurrencyDao currencyDao = new CurrencyDaoImpl();
    ExchangeRateDao exchangeRateDao = new ExchangeRateDaoImpl();
    ExchangeRateService exchangeService = new ExchangeRateServiceImpl();
    Mapper mapper = new Mapper();

    public Optional<ExchangeDTO> getExchange(String base, String target, String amount){

        double answer = getCalculatedRate(base, target);
        if(answer != 0){
            return Optional.of(mapper.toExchangeDto(base, target, amount, answer));
        }else {
            return Optional.empty();
        }
    }

    private double getCalculatedRate(String base, String target){
        double rate = 0.0;
        if(currencyDao.getByCode(base).isEmpty() ||
                currencyDao.getByCode(target).isEmpty() || base.equals(target)){
            throw new NotCurrencyPairException();
        }else if(isPresentCurrencyPair(base, target)){
            rate = getExchangeRate(base, target);
        }else if(isPresentCurrencyPair(target, base)){
            rate = 1 / getExchangeRate(target, base);
        }else if(isPresentCurrencyPair(USD, base) && isPresentCurrencyPair(USD, target)){
            rate = getQuotes(USD, base, USD, target);
        }else if(isPresentCurrencyPair(base, USD) && isPresentCurrencyPair(USD, target)){
            ExchangeRate exchangeRate1 = exchangeService.getFindExchangeRate(base, USD);
            ExchangeRate exchangeRate2 = exchangeService.getFindExchangeRate(USD, target);
            rate = exchangeRate1.getRate().doubleValue() * exchangeRate2.getRate().doubleValue();
        }else if(isPresentCurrencyPair(base, USD) && isPresentCurrencyPair(target, USD)){
            rate = getQuotes(base, USD, target, USD);
        }
        return rate;
    }
    private boolean isPresentCurrencyPair(String base, String target){
        return exchangeRateDao.getFindExchangeRate(base, target).isPresent();
    }
    private double getQuotes(String base1, String target1, String base2, String target2){
        ExchangeRate exchangeRate1 = exchangeService.getFindExchangeRate(base1, target1);
        ExchangeRate exchangeRate2 = exchangeService.getFindExchangeRate(base2, target2);
        double max = Double.max(exchangeRate1.getRate().doubleValue(), exchangeRate2.getRate().doubleValue());
        double min = Double.min(exchangeRate1.getRate().doubleValue(), exchangeRate2.getRate().doubleValue());
        return max / min;
    }

    private double getExchangeRate(String base, String target){
       return exchangeService.getFindExchangeRate(base, target).getRate().doubleValue();
    }
}
