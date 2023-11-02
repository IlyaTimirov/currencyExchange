package dao;

import DBUtils.Connect;
import entity.Currency;
import exception.already.CurrencyAlreadyExistsException;
import exception.notfound.NotFoundCurrencyException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static utils.StringQuery.*;
public class CurrencyDaoImpl implements CurrencyDao{

    @Override
    public Currency add(Currency currency) {
        Connect connect = new Connect();
        try(PreparedStatement statement = connect.getConnection().prepareStatement(SAVE_CURRENCIES, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, currency.getCode());
            statement.setString(2, currency.getFullName());
            statement.setString(3, currency.getSign());
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
            currency.setId(resultSet.getInt("id"));
        }catch (SQLException e){
            if(e.getErrorCode() == 19){
                throw new CurrencyAlreadyExistsException();
            }
            throw new RuntimeException(e);
        }
        return currency;
    }

    @Override
    public void update(Currency currency) {

    }

    @Override
    public void removeCurrency(Long id) {

    }

    @Override
    public Optional<Currency> getByCode(String code) {
        Connect connect = new Connect();
        try(PreparedStatement statement = connect.getConnection().prepareStatement(BY_CODE_CURRENCIES);){
            statement.setString(1, code);
            if(statement.executeQuery().next()){
                return Optional.of(parseCurrencyFromResult(statement.executeQuery()));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Currency getById(Long id) {
        Connect connect = new Connect();
        try(PreparedStatement statement = connect.getConnection().prepareStatement(BY_ID_CURRENCIES);){
            statement.setLong(1, id);
            if(!statement.executeQuery().next()){
                throw new NotFoundCurrencyException();
            }
            return parseCurrencyFromResult(statement.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Currency> getFindAll() {
        List<Currency> currencies = new ArrayList<>();
        Connect connect = new Connect();
        try(Statement statement = connect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_CURRENCIES);){
            while (resultSet.next()){
                currencies.add(parseCurrencyFromResult(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return currencies;
    }

    private Currency parseCurrencyFromResult(ResultSet resultSet) throws SQLException{
        Currency currency = new Currency();
            currency.setId(resultSet.getInt("Id"));
            currency.setCode(resultSet.getString("Code"));
            currency.setFullName(resultSet.getString("FullName"));
            currency.setSign(resultSet.getString("Sign"));
        return currency;
    }
}
