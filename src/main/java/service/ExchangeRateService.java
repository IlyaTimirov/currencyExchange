package service;

import dao.*;
import dto.ExchangeRateDto;
import dto.ExchangeRateUpdateDto;
import entity.ExchangeRate;
import exception.notfound.NotCurrencyPairException;
import utils.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class ExchangeRateService {
    private final CurrencyService currencyService;
    private final ExchangeRateDao exchangeRateDao;

    private final Mapper mapper;

    public ExchangeRateService() {
        this.currencyService = new CurrencyService();
        this.exchangeRateDao = new ExchangeRateDao();

        this.mapper = new Mapper();
    }

    public ExchangeRateDto save(ExchangeRate exchangeRate) {
        ExchangeRate exchangeRateSave = exchangeRateDao.save(exchangeRate);
        return mapper.exchangeRateToDto(exchangeRateSave);
    }

    public ExchangeRateDto update(ExchangeRateUpdateDto exchangeRateDto) {
        ExchangeRate exchangeRate = findBaseAndTarget(exchangeRateDto.getBaseCurrencyCode(), exchangeRateDto.getTargetCurrencyCode());
        exchangeRate.setRate(exchangeRateDto.getRate());
        ExchangeRate exchangeRateUpdate = exchangeRateDao.update(exchangeRate);
        return mapper.exchangeRateToDto(exchangeRateUpdate);
    }

    public void removeExchangeRates(Long id) {

    }

    public List<ExchangeRateDto> getFindAll() {
        return exchangeRateDao.findAll().stream().map(mapper::exchangeRateToDto).collect(Collectors.toList());
    }

    public ExchangeRateDto fetchBaseAndTarget(String base, String target) {
        return mapper.exchangeRateToDto(this.findBaseAndTarget(base, target));
    }

    private ExchangeRate findBaseAndTarget(String base, String target) {
        long baseId = currencyService.getByCode(base).getId();
        long targetId = currencyService.getByCode(target).getId();
        return exchangeRateDao.findByBaseAndTarget(baseId, targetId).orElseThrow(NotCurrencyPairException::new);
    }

}
