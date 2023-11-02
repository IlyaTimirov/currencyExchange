package exception.validation;

import exception.Error;

public class IncorrectCurrencyCodeException extends ValidationException{
    public IncorrectCurrencyCodeException() {
        super(Error.INCORRECT_CURRENCY_CODE);
    }
}
