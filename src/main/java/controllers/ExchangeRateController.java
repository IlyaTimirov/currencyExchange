package controllers;

import dto.ExchangeRateDto;
import dto.ExchangeRateUpdateDto;
import exception.AppException;
import service.ExchangeRateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/exchangeRate/*")
public class ExchangeRateController extends BaseController {
    private final ExchangeRateService exchange;

    public ExchangeRateController() {
        this.exchange = new ExchangeRateService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String base = req.getPathInfo().substring(1, 4);
        String target = req.getPathInfo().substring(4, 7);
        try {
            validator.incorrectCurrencyPair(base, target);
            ExchangeRateDto exchangeRate = exchange.fetchBaseAndTarget(base, target);
            response(resp, exchangeRate, HttpServletResponse.SC_OK);
        } catch (AppException e) {
            e.sendError(resp, e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (!method.equals("PATCH")) {
            super.service(req, resp);
        } else {
            this.doPut(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String baseCurrencyId = req.getParameter("baseCurrencyId");
        String targetCurrencyId = req.getParameter("targetCurrencyId");
        String rate = req.getParameter("rate");

        try {
            validator.incorrectExchangeRate(baseCurrencyId, targetCurrencyId, rate);

            response(resp, exchange.update(ExchangeRateUpdateDto.builder()
                    .baseCurrencyCode(baseCurrencyId)
                    .targetCurrencyCode(targetCurrencyId)
                    .rate(BigDecimal.valueOf(Double.parseDouble(rate)))
                    .build()), HttpServletResponse.SC_OK);
        } catch (AppException e) {
            e.sendError(resp, e);
        }
    }
}
