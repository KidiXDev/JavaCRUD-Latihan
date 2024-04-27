package com.database.latihan;

import java.sql.*;

public class Connection {

    private java.sql.Connection connection;
    
    private String url = "jdbc:mysql://localhost:3306/latihandb";
    private String username = "root";
    private String password = "";

    public Connection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public java.sql.Connection GetConnection(){
        return connection;
    }
}
