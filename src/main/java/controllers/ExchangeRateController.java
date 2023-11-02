package controllers;

import entity.ExchangeRate;
import exception.AppException;
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

@WebServlet(urlPatterns = "/exchangeRate/*")
public class ExchangeRateController extends BaseController {
    ExchangeRateService exchange = new ExchangeRateServiceImpl();
    Validator validator = new Validator();
    Mapper mapper = new Mapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String base = req.getPathInfo().substring(1,4);
        String target = req.getPathInfo().substring(4,7);
        try {
            validator.incorrectCodeCurrency(base);
            validator.incorrectCodeCurrency(target);
            ExchangeRate exchangeRate = exchange.getFindExchangeRate(base, target);
            writeJson(resp, mapper.getExchangeRateDto(exchangeRate), HttpServletResponse.SC_OK);
        }catch (AppException e){
            e.sendError(resp, e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if(!method.equals("PATCH")){
            super.service(req, resp);
        }else {
            this.doPut(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String baseCurrencyId = req.getParameter("baseCurrencyId");
        String targetCurrencyId = req.getParameter("targetCurrencyId");
        String rate = req.getParameter("rate");

        try {
            validator.incorrectCodeCurrency(baseCurrencyId);
            validator.incorrectCodeCurrency(targetCurrencyId);
            validator.incorrectRate(rate);

            ExchangeRate ex = exchange.getFindExchangeRate(baseCurrencyId, targetCurrencyId);
            ex.setRate(BigDecimal.valueOf(Double.parseDouble(rate)));
            writeJson(resp, mapper.getExchangeRateDto(exchange.updateExchangeRates(ex)), HttpServletResponse.SC_OK);
        } catch (AppException e){
            e.sendError(resp, e);
        }
    }
}
