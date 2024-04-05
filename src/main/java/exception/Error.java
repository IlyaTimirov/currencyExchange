package exception;

import javax.servlet.http.HttpServletResponse;

public enum Error {
    CURRENCY_NOT_FOUND("������ �� �������!",
            HttpServletResponse.SC_NOT_FOUND),
    EXCHANGE_RATE_NOT_FOUND("�������� ���� �� ������!",
            HttpServletResponse.SC_NOT_FOUND),
    CURRENCY_ALREADY_EXISTS("����� ������ ��� ����������!",
            HttpServletResponse.SC_CONFLICT),
    EXCHANGE_RATE_ALREADY_EXISTS("�������� ���� ��� ����������!",
            HttpServletResponse.SC_CONFLICT),
    NOT_CODE_EXCHANGE("������� ��� ������!",
            HttpServletResponse.SC_BAD_REQUEST),
    INCORRECT_CURRENCY_CODE("����������� ������ ��� ������!",
            HttpServletResponse.SC_BAD_REQUEST),
    INCORRECT_CURRENCY_FULL_NAME("����������� ������� ��� ������!",
            HttpServletResponse.SC_BAD_REQUEST),
    INCORRECT_CURRENCY_SIGN("����������� ���� ������ ������!",
            HttpServletResponse.SC_BAD_REQUEST),
    CURRENCY_PAIR_NOT_FOUND("�������� ���� ����",
            HttpServletResponse.SC_NOT_FOUND),
    NOT_FOUND_PAGE("�������� �� �������!", HttpServletResponse.SC_NOT_FOUND),
    SERVER_ERROR("������ �� �������, �� ��� �����...", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

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
