package com.ngo.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static DatabaseConnection databaseConnection = null;
    private static  Connection conn = null;

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://sql5.freemysqlhosting.net/sql554667";
    private static final String USER = "sql554667";
    private static final String PASS = "qP4*aF9*";

    public static DatabaseConnection getInstance() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    private DatabaseConnection() {
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Database Connection Failure");
        }
    }
    
    public String toString()
    {
        return "Database";
    }
    public Connection getConnection() {
        return conn;
    }
}
