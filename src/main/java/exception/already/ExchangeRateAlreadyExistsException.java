package exception.already;

import exception.Error;

public class ExchangeRateAlreadyExistsException extends AlreadyExistsException{
    public ExchangeRateAlreadyExistsException() {
        super(Error.EXCHANGE_RATE_ALREADY_EXISTS);
    }
}
