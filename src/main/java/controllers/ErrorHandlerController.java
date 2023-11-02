package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/error")
public class ErrorHandlerController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 404){
            try (PrintWriter printWriter = response.getWriter()){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                printWriter.write("Page not found");
            }
        }
        if(statusCode == 500){
            try (PrintWriter printWriter = response.getWriter()){
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                printWriter.write("Data base error");
            }
        }
    }
}
