package utils;

import dto.ExchangeDTO;
import dto.ExchangeRateDTO;
import entity.Currency;
import entity.ExchangeRate;
import service.CurrencyService;
import service.CurrencyServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public List<ExchangeRateDTO> getAllExchangeRateDto(List<ExchangeRate> exchangeRates){
        List<ExchangeRateDTO> exchangeRateDTOS = new ArrayList<>();
        for(ExchangeRate exchangeRate: exchangeRates){
            exchangeRateDTOS.add(getExchangeRateDto(exchangeRate));
        }
        return exchangeRateDTOS;
    }

    public ExchangeRateDTO getExchangeRateDto(ExchangeRate exchangeRate){
        CurrencyService currency = new CurrencyServiceImpl();
        Currency baseCurrencyId = null;
        baseCurrencyId = currency.getById((long)exchangeRate.getBaseCurrencyId());
        Currency targetCurrencyId = null;
        targetCurrencyId = currency.getById((long)exchangeRate.getTargetCurrencyId());
        return new ExchangeRateDTO(exchangeRate.getId(), baseCurrencyId, targetCurrencyId, exchangeRate.getRate());
    }

    public ExchangeDTO toExchangeDto(String from, String to, String amount, double rate){
        CurrencyService currency = new CurrencyServiceImpl();
        return new ExchangeDTO(currency.getByCode(from), currency.getByCode(to),
                rate, Double.parseDouble(amount), rate * Double.parseDouble(amount));
    }

}
