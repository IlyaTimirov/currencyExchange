package controllers;

import entity.ExchangeRate;
import exception.AppException;
import service.CurrencyService;
import service.CurrencyServiceImpl;
import service.ExchangeRateService;
import service.ExchangeRateServiceImpl;
import utils.Mapper;
import validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/exchangeRates")
public class ExchangeRatesController extends BaseController{
    ExchangeRateService exchangeRateService = new ExchangeRateServiceImpl();
    Mapper mapper = new Mapper();
    CurrencyService currency = new CurrencyServiceImpl();
    Validator validator = new Validator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            writeJson(resp, mapper.getAllExchangeRateDto(exchangeRateService.getFindAll()), HttpServletResponse.SC_OK);
        }catch (AppException e){
            e.sendError(resp, e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String baseCurrencyId = req.getParameter("baseCurrencyId");
        String targetCurrencyId = req.getParameter("targetCurrencyId");
        String rate = req.getParameter("rate");
        try {
            validator.incorrectCodeCurrency(baseCurrencyId);
            validator.incorrectCodeCurrency(targetCurrencyId);
            validator.incorrectRate(rate);

            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setBaseCurrencyId(currency.getByCode(baseCurrencyId).getId());
            exchangeRate.setTargetCurrencyId(currency.getByCode(targetCurrencyId).getId());
            exchangeRate.setRate(BigDecimal.valueOf(Double.parseDouble(rate)));
            writeJson(resp, mapper.getExchangeRateDto(exchangeRate), HttpServletResponse.SC_CREATED);
        }catch (AppException e){
            e.sendError(resp, e);
        }
    }
}
