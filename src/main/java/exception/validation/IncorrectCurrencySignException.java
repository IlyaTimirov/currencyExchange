package exception.validation;

import exception.Error;

public class IncorrectCurrencySignException extends ValidationException{
    public IncorrectCurrencySignException() {
        super(Error.INCORRECT_CURRENCY_SIGN);
    }
}
