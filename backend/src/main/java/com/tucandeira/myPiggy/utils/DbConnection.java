package com.tucandeira.myPiggy.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    
    // Config database
    private static final String DB_URL = "jdbc:postgresql://mypiggy_postgres:5432/myPiggy";
    private static final String DB_USER = "myuser";
    private static final String DB_PASSWD = "mypassword";

    private Connection conn;

    public DbConnection() {
        try {
            Class.forName("org.postgresql.Driver");
          
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
        } catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao tentar conectar ao banco de dados", e);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso.");
        } else {
            System.out.println("Falha na conexão com o banco de dados.");
        }
    }
}


