package controllers;

import exception.AppException;
import exception.Error;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/error")
public class ErrorHandlerController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processError(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processError(req, resp);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Error error = null;
        if (statusCode == 404) {
            error = Error.NOT_FOUND_PAGE;
        }
        if (statusCode == 500) {
            error = Error.SERVER_ERROR;
        }
        AppException appException = new AppException(error);
        appException.sendError(response, appException);
    }
}
