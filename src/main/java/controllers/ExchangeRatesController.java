package controllers;

import entity.ExchangeRate;
import exception.AppException;
import service.CurrencyService;
import service.ExchangeRateService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/exchangeRates")
public class ExchangeRatesController extends BaseController{
    private final ExchangeRateService exchangeRateService;
    private final CurrencyService currency;

    public ExchangeRatesController() {
        this.exchangeRateService = new ExchangeRateService();
        this.currency = new CurrencyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            response(resp, exchangeRateService.getFindAll(), HttpServletResponse.SC_OK);
        }catch (AppException e){
            e.sendError(resp, e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String baseCurrencyId = req.getParameter("baseCurrencyCode");
        String targetCurrencyId = req.getParameter("targetCurrencyCode");
        String rate = req.getParameter("rate");
        try {
            validator.incorrectExchangeRate(baseCurrencyId, targetCurrencyId, rate);

            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setBaseCurrencyId(currency.getByCode(baseCurrencyId).getId());
            exchangeRate.setTargetCurrencyId(currency.getByCode(targetCurrencyId).getId());
            exchangeRate.setRate(BigDecimal.valueOf(Double.parseDouble(rate)));

            response(resp, exchangeRateService.save(exchangeRate), HttpServletResponse.SC_CREATED);
        }catch (AppException e){
            e.sendError(resp, e);
        }
    }
}
