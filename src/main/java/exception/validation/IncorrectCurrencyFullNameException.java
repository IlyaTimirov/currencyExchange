package exception.validation;

import exception.Error;

public class IncorrectCurrencyFullNameException extends ValidationException{
    public IncorrectCurrencyFullNameException() {
        super(Error.INCORRECT_CURRENCY_FULL_NAME);
    }
}
