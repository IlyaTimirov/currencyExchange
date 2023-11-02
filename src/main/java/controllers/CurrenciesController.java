package controllers;

import entity.Currency;
import exception.AppException;
import service.CurrencyService;
import service.CurrencyServiceImpl;
import validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/currencies")
public class CurrenciesController extends BaseController {
    CurrencyService currency = new CurrencyServiceImpl();
    Validator validator = new Validator();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            writeJson(resp, currency.getFindAll(), HttpServletResponse.SC_OK);
        }catch (AppException e){
            e.sendError(resp, e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String fullName = req.getParameter("fullName");
        String sign = req.getParameter("sign");
        try {
            validator.validCorrectInputCurrency(code, fullName, sign);
            currency.add(new Currency(code, fullName, sign));
            writeJson(resp, currency.getByCode(code), HttpServletResponse.SC_CREATED);
        }catch (AppException e){
            e.sendError(resp, e);
        }
    }

}
