package utils;

import exception.AppException;
import exception.Error;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class UtilsDb {
    public Connection getConnection() throws SQLException {
        Connection connection;
        try {
            URL urlDb = UtilsDb.class.getClassLoader().getResource("exchanger.db");
            if (urlDb != null) {
                String path = "jdbc:sqlite:" + new File(urlDb.toURI()).getAbsolutePath();
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(path);
            } else {
                throw new AppException(Error.SERVER_ERROR);
            }
        } catch (ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
