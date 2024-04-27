package com.database.latihan;

import java.sql.*;

public class Connection {

    private java.sql.Connection connection;
    
    private String databaseName = "latihandb";
    private String url = "jdbc:mysql://localhost:3306/";
    private String username = "root";
    private String password = "";

    public Connection() {
        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public java.sql.Connection GetConnection(){
        return connection;
    }
}
