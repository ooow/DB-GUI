package app;

import GUI.MainMenu;

import java.sql.*;

public class Main {
    private static Connection con;
    private static Statement statement;

    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
    }

    static {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433", "usergt", "zxcxz1234");
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("use GYM select * from Clients");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Statement getStatement() {
        return statement;
    }
}


