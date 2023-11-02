package exception.already;

import exception.Error;

public class CurrencyAlreadyExistsException extends AlreadyExistsException{
    public CurrencyAlreadyExistsException() {
        super(Error.CURRENCY_ALREADY_EXISTS);
    }
}
