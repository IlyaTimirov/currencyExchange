package utils;

import exception.notfound.NotCodeExchangeException;

public class InputParserUtils {
    public String parseInputCurrencyCode(String path){
        String[] paths = path.split("/");
        if(paths.length < 1){
            throw new NotCodeExchangeException();
        }
        return paths[1];
    }
}
