package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.Response;
import validation.Validator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseController extends HttpServlet implements Response {
    private final ObjectMapper mapper;
    protected final Validator validator;

    public BaseController() {
        this.mapper = new ObjectMapper();
        this.validator = new Validator();
    }

}
