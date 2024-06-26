package exception;

import dto.ErrorDto;
import utils.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppException extends RuntimeException implements Response {
    private final Error error;

    public AppException(Error error) {
        this.error = error;
    }

    public void sendError(HttpServletResponse response, AppException error) throws IOException {
        response(response, new ErrorDto(error.error.getMessage()), error.error.getStatus());
    }
}
