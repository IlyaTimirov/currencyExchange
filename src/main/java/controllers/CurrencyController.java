package controllers;

import exception.AppException;
import service.CurrencyService;
import utils.InputParserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/currency/*")
public class CurrencyController extends BaseController {

    private final CurrencyService currency;
    private final InputParserUtils parser;

    public CurrencyController() {
        this.currency = new CurrencyService();
        this.parser = new InputParserUtils();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String code = parser.parseInputCurrencyCode(req.getPathInfo());
            validator.incorrectCodeCurrency(code);
            writeJson(resp, currency.getByCode(code), HttpServletResponse.SC_OK);
        } catch (AppException e) {
            e.sendError(resp, e);
        }
    }
}
