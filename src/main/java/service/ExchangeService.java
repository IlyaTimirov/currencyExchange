package service;

import dto.ExchangeDTO;

import java.util.Optional;

public interface ExchangeService {
    Optional<ExchangeDTO> getExchange(String base, String target, String amount);
}
