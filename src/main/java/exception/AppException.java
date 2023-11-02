package exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.BaseController;

import javax.servlet.http.HttpServletResponse;


public class AppException extends BaseException {
    Error error;
    ObjectMapper objectMapper = new ObjectMapper();

    public AppException(Error error) {
        this.error = error;
    }

}
