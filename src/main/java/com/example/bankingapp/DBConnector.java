package com.example.bankingapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Jana Metz on 13.02.24
 */
public class DBConnector {
    public static Connection connect() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        String url = resourceBundle.getString("db.url");
        String dbName = resourceBundle.getString("db.name");
        String user = resourceBundle.getString("db.user");
        String password = resourceBundle.getString("db.password");
        return DriverManager.getConnection(url + dbName, user, password);


    }
}
