package dao;

import DBUtils.Connect;
import entity.ExchangeRate;
import exception.already.ExchangeRateAlreadyExistsException;
import exception.notfound.NotFoundCurrencyException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static utils.StringQuery.*;
public class ExchangeRateDaoImpl implements ExchangeRateDao {
    private final CurrencyDao currencyDao = new CurrencyDaoImpl();
    @Override
    public ExchangeRate addExchangeRates(ExchangeRate exchangeRate) {
        Connect connect = new Connect();
        try (PreparedStatement preparedStatement = connect.getConnection().prepareStatement(SAVE_EXCHANGE_RATES, Statement.RETURN_GENERATED_KEYS);){
            preparedStatement.setInt(1, exchangeRate.getBaseCurrencyId());
            preparedStatement.setInt(2, exchangeRate.getTargetCurrencyId());
            preparedStatement.setBigDecimal(3, exchangeRate.getRate());
            ResultSet resultSet = preparedStatement.executeQuery();
            exchangeRate.setId(resultSet.getLong("id"));
        }catch (SQLException e){
            if(e.getErrorCode() == 19){
                throw new ExchangeRateAlreadyExistsException();
            }
            throw new RuntimeException(e);
        }
        return exchangeRate;
    }

    @Override
    public ExchangeRate updateExchangeRates(ExchangeRate exchangeRate) {
        Connect connect = new Connect();

        try(PreparedStatement statement = connect.getConnection().prepareStatement(UPDATE_EXCHANGE_RATES);) {
            statement.setBigDecimal(1, exchangeRate.getRate());
            statement.setLong(2, exchangeRate.getId());
            if(statement.executeUpdate() == 0){
                throw new NotFoundCurrencyException();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return exchangeRate;
    }

    @Override
    public void removeExchangeRates(Long id) {

    }

    @Override
    public List<ExchangeRate> getFindAll() {
        Connect connect = new Connect();
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        try(Statement statement = connect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_EXCHANGE_RATES);) {
            while (resultSet.next()){
                exchangeRates.add(parseExchangeRateFromResult(resultSet));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return exchangeRates;
    }

    @Override
    public Optional<ExchangeRate> getFindExchangeRate(String base, String target) {
        Connect connect = new Connect();
        try(PreparedStatement statement = connect.getConnection().prepareStatement(FIND_CURRENCY_PAIR)){
            statement.setInt(1, currencyDao.getByCode(base).get().getId());
            statement.setInt(2, currencyDao.getByCode(target).get().getId());
            if(statement.executeQuery().next()){
                return Optional.of(parseExchangeRateFromResult(statement.executeQuery()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    private ExchangeRate parseExchangeRateFromResult(ResultSet resultSet) {
        ExchangeRate exchangeRate = new ExchangeRate();
        try {
            exchangeRate.setId(resultSet.getLong("id"));
            exchangeRate.setBaseCurrencyId(resultSet.getInt("BaseCurrencyId"));
            exchangeRate.setTargetCurrencyId(resultSet.getInt("TargetCurrencyId"));
            exchangeRate.setRate(resultSet.getBigDecimal("Rate"));
            return exchangeRate;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
