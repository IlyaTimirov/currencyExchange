package controllers;

import exception.AppException;
import service.CurrencyService;
import service.CurrencyServiceImpl;
import utils.InputParserUtils;
import validation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/currency/*")
public class CurrencyController extends BaseController {

    CurrencyService currency = new CurrencyServiceImpl();
    InputParserUtils parser = new InputParserUtils();
    Validator validator = new Validator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String code = parser.parseInputCurrencyCode(req.getPathInfo());
            validator.incorrectCodeCurrency(code);
            writeJson(resp, currency.getByCode(code), HttpServletResponse.SC_OK);
        }catch (AppException e){
            e.sendError(resp, e);
        }

    }
}
