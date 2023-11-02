package exception.notfound;

import exception.Error;

public class NotCurrencyPairException extends NotFoundException{
    public NotCurrencyPairException() {
        super(Error.CURRENCY_PAIR_NOT_FOUND);
    }
}
