package exception.notfound;

import exception.Error;

public class NotFoundCurrencyException extends NotFoundException{

    public NotFoundCurrencyException() {
        super(Error.CURRENCY_NOT_FOUND);
    }
}
