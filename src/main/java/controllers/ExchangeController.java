package controllers;

import exception.AppException;
import service.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/exchange")
public class ExchangeController extends BaseController {
    private final ExchangeService exchange;

    public ExchangeController() {
        this.exchange = new ExchangeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        String amount = req.getParameter("amount");

        try {
            validator.incorrectExchangeRate(from, to, amount);

            writeJson(resp, exchange.getExchange(from, to, amount), HttpServletResponse.SC_OK);

        } catch (AppException e) {
            e.sendError(resp, e);
        }
    }
}
