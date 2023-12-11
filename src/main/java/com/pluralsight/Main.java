package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/northwind";
        String user = "root";
        String password = "password!";
        String query = "SELECT * FROM Products";

        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                System.out.println("ProductID " + results.getString("ProductID"));
                System.out.println("ProductName " + results.getString("ProductName"));
                System.out.println("UnitPrice " + results.getString("UnitPrice"));
                System.out.println("UnitsInStock " + results.getString("UnitsInStock"));
                System.out.println("""
                        ----------------------------------
                        """);

            }

            results.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

}
