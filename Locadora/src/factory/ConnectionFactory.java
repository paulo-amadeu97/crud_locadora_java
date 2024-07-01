package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    private static final String URL = "jdbc:sqlite:src/factory/locadora";
    public static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        try {
            Statement statement = connection.createStatement();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return connection;
    }
}
