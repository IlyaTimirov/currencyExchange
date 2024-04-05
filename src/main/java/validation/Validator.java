package validation;

import exception.validation.IncorrectCurrencyCodeException;
import exception.validation.IncorrectCurrencyFullNameException;
import exception.validation.IncorrectCurrencySignException;

public class Validator {

    public void validCorrectInputCurrency(String code, String fullName, String sign){
        incorrectCodeCurrency(code);
        if(incorrectFullName(fullName)){
            throw new IncorrectCurrencyFullNameException();
        }
        if(incorrectSign(sign)){
            throw new IncorrectCurrencySignException();
        }
    }
    public void incorrectCodeCurrency(String code) {
        if(!code.matches("^[a-zA-Z]*$") || code.length() != 3){
            throw new IncorrectCurrencyCodeException();
        }
    }

    private boolean incorrectFullName(String fullName) {
        return !fullName.matches("[\sa-zA-Z]*$");
    }

    private boolean incorrectSign(String sign) {
        return !sign.matches("[^0-9]*$");
    }

    public void incorrectRate(String rate){
        if(rate.matches("^[a-zA-Z]*$") || rate.isEmpty()){
            throw new IncorrectCurrencyCodeException();
        }
    }
    public void incorrectCurrencyPair(String base, String target){
        incorrectCodeCurrency(base);
        incorrectCodeCurrency(target);
    }
    public void incorrectExchangeRate(String baseCode, String targetCode, String rate){
        incorrectRate(rate);
        incorrectCurrencyPair(baseCode, targetCode);
    }

}