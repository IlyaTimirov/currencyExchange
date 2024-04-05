package utils;

import dto.CurrencyDto;
import dto.ExchangeDto;
import dto.ExchangeRateDto;
import entity.Currency;
import entity.ExchangeRate;
import org.modelmapper.ModelMapper;
import service.CurrencyService;

public class Mapper {

    private final ModelMapper mapper;

    public Mapper() {
        this.mapper = new ModelMapper();
    }

    public Currency dtoToCurrency(CurrencyDto currencyDto){
        return mapper.map(currencyDto, Currency.class);
    }

    public CurrencyDto currencyToDto(Currency currency){
        return mapper.map(currency, CurrencyDto.class);
    }

    public ExchangeRateDto exchangeRateToDto(ExchangeRate exchangeRate){
        CurrencyService currencyService = new CurrencyService();
        CurrencyDto currencyDtoBase = currencyService.getById(exchangeRate.getBaseCurrencyId());
        CurrencyDto currencyDtoTarget = currencyService.getById(exchangeRate.getTargetCurrencyId());

        ExchangeRateDto exchangeRateDto = mapper.map(exchangeRate, ExchangeRateDto.class);
        exchangeRateDto.setBaseCurrencyId(currencyDtoBase);
        exchangeRateDto.setTargetCurrencyId(currencyDtoTarget);
        return exchangeRateDto;
    }

    public ExchangeDto toExchangeDto(String from, String to, String amount, double rate){
        CurrencyService currency = new CurrencyService();
        return new ExchangeDto(currency.getByCode(from), currency.getByCode(to),
                rate, Double.parseDouble(amount), rate * Double.parseDouble(amount));
    }

}
