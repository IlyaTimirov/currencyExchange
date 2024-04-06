package service;

import dao.CurrencyDao;
import dto.CurrencyDto;
import entity.Currency;
import exception.notfound.NotFoundCurrencyException;
import utils.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class CurrencyService {
    private final CurrencyDao currencyDao;

    private final Mapper mapper;

    public CurrencyService() {
        this.currencyDao = new CurrencyDao();
        this.mapper = new Mapper();
    }

    public CurrencyDto add(CurrencyDto currencyDto) {
        Currency currency = currencyDao.save(mapper.dtoToCurrency(currencyDto));
        return mapper.currencyToDto(currency);
    }

    public CurrencyDto getById(Long id) {
        Currency currency = currencyDao.findById(id).orElseThrow(NotFoundCurrencyException::new);
        return mapper.currencyToDto(currency);
    }

    public CurrencyDto getByCode(String code) {
        Currency currency = currencyDao.findByCode(code).orElseThrow(NotFoundCurrencyException::new);
        return mapper.currencyToDto(currency);
    }

    public List<CurrencyDto> getFindAll() {
        return currencyDao.findAll().stream().map(mapper::currencyToDto)
                .collect(Collectors.toList());
    }
}
