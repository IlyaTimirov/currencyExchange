package controllers;

import dto.ExchangeDTO;
import exception.AppException;
import exception.notfound.NotCurrencyPairException;
import exception.notfound.NotFoundException;
import service.*;
import validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/exchange")
public class ExchangeController extends BaseController {
    ExchangeService exchange = new ExchangeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        String amount = req.getParameter("amount");

        Validator validator = new Validator();
        try {
            validator.incorrectCodeCurrency(from);
            validator.incorrectCodeCurrency(to);
            validator.incorrectRate(amount);
            Optional<ExchangeDTO> exchangeDTO = exchange.getExchange(from, to, amount);
            if(exchangeDTO.isPresent()) {
                writeJson(resp, exchangeDTO, HttpServletResponse.SC_OK);
            }else {
                throw new NotCurrencyPairException();
            }
        }catch (AppException e){
            e.sendError(resp, e);
        }

    }
}
