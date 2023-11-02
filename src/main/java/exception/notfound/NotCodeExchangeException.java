package exception.notfound;

import exception.Error;
import exception.validation.ValidationException;

public class NotCodeExchangeException extends ValidationException {

    public NotCodeExchangeException() {
        super(Error.NOT_CODE_EXCHANGE);
    }
}
