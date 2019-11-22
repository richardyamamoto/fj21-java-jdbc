package br.com.caelum.jdbc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/fj21", "root", "000000");
        }catch(SQLException err) {
            throw new RuntimeException(err);
        }
    }
}
