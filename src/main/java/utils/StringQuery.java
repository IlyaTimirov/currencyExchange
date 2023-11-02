package utils;

public class StringQuery {
    public static final String FIND_ALL_CURRENCIES = "SELECT * FROM currencies";
    public static final String BY_ID_CURRENCIES = "SELECT * FROM currencies WHERE id=?";
    public static final String BY_CODE_CURRENCIES = "SELECT * FROM currencies WHERE Code=?";
    public static final String SAVE_CURRENCIES = "INSERT INTO currencies (Code, FullName, Sign) VALUES (?,?,?)";
    public static final String SAVE_EXCHANGE_RATES = "insert into exchangeRates(BaseCurrencyId, TargetCurrencyId, Rate) values (?,?,?)";
    public static final String UPDATE_EXCHANGE_RATES = "UPDATE exchangeRates SET Rate=? WHERE id=?";
    public static final String FIND_ALL_EXCHANGE_RATES = "SELECT * FROM exchangeRates";
    public static final String FIND_CURRENCY_PAIR = "SELECT * FROM exchangeRates WHERE BaseCurrencyId=? AND TargetCurrencyId=?";

}
