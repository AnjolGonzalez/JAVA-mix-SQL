package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/northwind";
        String user = "root";
        String password = "Passwordblah!";
        String query = "SELECT * FROM Products";
        String query2 = "SELECT * FROM Customers ORDER BY Country";
        String query3 = "SELECT * FROM Categories ORDER BY CategoryID";
        String close = "";
        Scanner scanner = new Scanner(System.in);

        while (!close.equalsIgnoreCase("0")) {
            System.out.println("""
                    What do you want to do?
                    1) Display all products
                    2) Display all customers
                    3) Display all categories
                    0) Exit
                    Select an option
                    """);
            close = scanner.nextLine();

            switch (close) {
                case "1":
                    products(url, user, password, query);
                    break;
                case "2":
                    customers(url, user, password, query2);
                    break;
                case "3":
                    displayAllCategories(url, user, password, query3, scanner);
                    break;
                case "0": {
                    System.exit(0);
                }
                default:
                    System.out.println("Please choose from the following provided.");
                    break;
            }

        }

    }
    public static void products(String url, String user, String password, String query){

        Statement statement2 = null;
        ResultSet resultSet = null;
        Connection connection2 = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()){
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
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement2 != null) {
                try {
                    statement2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection2 != null) {
                try {
                    connection2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void customers(String url, String user, String password, String query2) {

        Statement statement2 = null;
        ResultSet resultSet = null;
        Connection connection2 = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query2);

            while (results.next()){
                System.out.println("ContactName " + results.getString("ContactName"));
                System.out.println("CompanyName " + results.getString("CompanyName"));
                System.out.println("City " + results.getString("City"));
                System.out.println("Country " + results.getString("Country"));
                System.out.println("Phone " + results.getString("Phone"));
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
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement2 != null) {
                try {
                    statement2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection2 != null) {
                try {
                    connection2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void displayAllCategories(String url, String user, String password, String query3, Scanner scanner) {
        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(query3);
                ResultSet results = preparedStatement.executeQuery();

        ) {
            while (results.next()){
                int categoryID = results.getInt("CategoryID");
                String categoryName = results.getString("CategoryName");

                System.out.println("CategoryID " + categoryID);
                System.out.println("CategoryName" + categoryName);
        }
            System.out.println("Which category ID would you like to view?");
            int ID = scanner.nextInt();
            scanner.nextLine();

            String cloneQuery3 = "SELECT * FROM Products WHERE CategoryID = ?";
            PreparedStatement  preparedStatement2 = connection.prepareStatement(cloneQuery3) ;
            preparedStatement2.setInt(1, ID);
            ResultSet results2 = preparedStatement2.executeQuery();

            while (results2.next()) {
                System.out.println("ProductID " + results2.getString("ProductID"));
                System.out.println("ProductName " + results2.getString("ProductName"));
                System.out.println("UnitPrice " + results2.getString("UnitPrice"));
                System.out.println("UnitsInStock " + results2.getString("UnitsInStock"));
                System.out.println("""
                        ----------------------------------
                        """);
            }
            results2.close();
            connection.close();

     } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
