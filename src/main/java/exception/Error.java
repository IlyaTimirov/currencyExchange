package exception;

import javax.servlet.http.HttpServletResponse;

public enum Error {
    CURRENCY_NOT_FOUND("Валюта не найдена!",
            HttpServletResponse.SC_NOT_FOUND),
    EXCHANGE_RATE_NOT_FOUND("Обменный курс не найден!",
            HttpServletResponse.SC_NOT_FOUND),
    CURRENCY_ALREADY_EXISTS("Такая валюта уже существует!",
            HttpServletResponse.SC_CONFLICT),
    EXCHANGE_RATE_ALREADY_EXISTS("Обменный курс уже существует!",
            HttpServletResponse.SC_CONFLICT),
    NOT_CODE_EXCHANGE("Введите код валюты!",
            HttpServletResponse.SC_BAD_REQUEST),
    INCORRECT_CURRENCY_CODE("Некорректно введен код валюты!",
            HttpServletResponse.SC_BAD_REQUEST),
    INCORRECT_CURRENCY_FULL_NAME("Некорректно введено имя валюты!",
            HttpServletResponse.SC_BAD_REQUEST),
    INCORRECT_CURRENCY_SIGN("Некорректно введ символ валюты!",
            HttpServletResponse.SC_BAD_REQUEST),
    CURRENCY_PAIR_NOT_FOUND("Валютной пары нету",
            HttpServletResponse.SC_NOT_FOUND),
    NOT_FOUND_PAGE("Страница не найдена!", HttpServletResponse.SC_NOT_FOUND),
    SERVER_ERROR("Ошибка на сервере, мы уже чиним...", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

    private final String message;
    private final int status;

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
