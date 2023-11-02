package exception;

import javax.servlet.http.HttpServletResponse;

public enum Error {
    CURRENCY_NOT_FOUND("Currency not found",
            HttpServletResponse.SC_NOT_FOUND),
    EXCHANGE_RATE_NOT_FOUND("ExchangeRate not found",
                       HttpServletResponse.SC_NOT_FOUND),
    CURRENCY_ALREADY_EXISTS("Currency already exists",
                            HttpServletResponse.SC_CONFLICT),
    EXCHANGE_RATE_ALREADY_EXISTS("ExchangeRate already exists",
            HttpServletResponse.SC_CONFLICT),
    NOT_CODE_EXCHANGE("The currency code is missing from the address",
            HttpServletResponse.SC_BAD_REQUEST),
    INCORRECT_CURRENCY_CODE("Incorrect currency code entry",
            HttpServletResponse.SC_BAD_REQUEST),
    INCORRECT_CURRENCY_FULL_NAME("Incorrect currency FullName entry",
            HttpServletResponse.SC_BAD_REQUEST),
    INCORRECT_CURRENCY_SIGN("Incorrect currency Sign entry",
            HttpServletResponse.SC_BAD_REQUEST),
    CURRENCY_PAIR_NOT_FOUND("Currency pair not",
            HttpServletResponse.SC_NOT_FOUND);

    private String message;
    private int status;

    Error(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
