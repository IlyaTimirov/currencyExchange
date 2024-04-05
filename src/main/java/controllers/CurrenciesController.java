package controllers;

import dto.CurrencyDto;
import exception.AppException;
import service.CurrencyService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/currencies")
public class CurrenciesController extends BaseController {
    private final CurrencyService currencyService;

    public CurrenciesController() {
        this.currencyService = new CurrencyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            response(resp, currencyService.getFindAll(), HttpServletResponse.SC_OK);
        } catch (AppException e) {
            e.sendError(resp, e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter("code");
        String fullName = req.getParameter("fullName");
        String sign = req.getParameter("sign");
        try {
            validator.validCorrectInputCurrency(code, fullName, sign);
            CurrencyDto currencyDto = currencyService.add(CurrencyDto.builder()
                    .code(code)
                    .name(fullName)
                    .sign(sign)
                    .build());
            response(resp, currencyDto, HttpServletResponse.SC_CREATED);
        } catch (AppException e) {
            e.sendError(resp, e);
        }
    }

}
