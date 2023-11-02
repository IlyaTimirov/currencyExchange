package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseController extends HttpServlet {

    protected <T> void writeJson(HttpServletResponse resp, T load, Integer status) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(status);
            resp.getWriter().write(mapper.writeValueAsString(load));
        } catch (RuntimeException e){
            throw new RuntimeException();
        }
    }
}
