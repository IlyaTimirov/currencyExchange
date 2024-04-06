package dao;

import utils.UtilsDb;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

public interface Dao  {
    default <T> T execute(String query, Function<PreparedStatement, T> statement) {
        UtilsDb utilsDb = new UtilsDb();
        try (PreparedStatement preparedStatement = utilsDb.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            return statement.apply(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
