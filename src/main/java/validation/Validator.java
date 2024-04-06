package validation;

import exception.InputFieldMissingException;
import exception.validation.IncorrectCurrencyCodeException;
import exception.validation.IncorrectCurrencyFullNameException;
import exception.validation.IncorrectCurrencySignException;

public class Validator {

    public void validCorrectInputCurrency(String code, String fullName, String sign){
        validField(code, fullName, sign);
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

    public void validField(String field1, String field2, String field3){
        if(isEmptyField(field1) || isEmptyField(field2) || isEmptyField(field3)){
            throw new InputFieldMissingException();
        }
    }
    private boolean isEmptyField(String field){
        return field == null;
    }
    public void incorrectCurrencyPair(String base, String target){
        if(isEmptyField(base) || isEmptyField(target)){
            throw new InputFieldMissingException();
        }
        incorrectCodeCurrency(base);
        incorrectCodeCurrency(target);
    }
    public void incorrectExchangeRate(String baseCode, String targetCode, String rate){
        validField(baseCode, targetCode, rate);
        incorrectRate(rate);
        incorrectCurrencyPair(baseCode, targetCode);
    }

}