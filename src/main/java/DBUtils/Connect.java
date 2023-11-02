package DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static final String URL_DB = "jdbc:sqlite:C:\\Users\\Илья\\Desktop\\currencyExchange\\src\\main\\resources\\exchanger.db";
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(URL_DB);
    }
}
