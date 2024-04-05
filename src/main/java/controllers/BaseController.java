package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import validation.Validator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseController extends HttpServlet {
    private final ObjectMapper mapper;
    protected final Validator validator;

    public BaseController() {
        this.mapper = new ObjectMapper();
        this.validator = new Validator();
    }

    protected <T> void writeJson(HttpServletResponse resp, T load, Integer status) throws IOException {
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
