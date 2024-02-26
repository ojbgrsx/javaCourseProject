package sample;

import java.sql.*;
import java.util.*;

public class Other_Methods {
    public static String quarter(int n) {
        return switch (n) {
            case 0 -> "First";
            case 3 -> "Second";
            case 6 -> "Third";
            case 9 -> "Fourth";
            default -> "";
        };
    }

    public static void wrongLog() {
        System.out.println("Apologies, but we could not find an account with such login or password, please repeat.");
    }

    public static String createCode() {
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrustuwxyz";
        String password = "";
        password += String.valueOf(random.nextInt(10));
        password += String.valueOf(random.nextInt(10));
        password += String.valueOf(random.nextInt(10));
        password += alphabet.charAt(random.nextInt(26));
        password += alphabet.charAt(random.nextInt(26));
        password += alphabet.charAt(random.nextInt(26));
        password += String.valueOf(random.nextInt(10));
        password += String.valueOf(random.nextInt(10));
        password += String.valueOf(random.nextInt(10));
        return password;
    }

    public static String month(int n) {
        return switch (n) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "";
        };
    }

    public static ArrayList<ArrayList<String>> getData(Connection connection, String at) throws Exception {
        Statement stmt = connection.createStatement();
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        if (at.equals("w")) {
            ResultSet resultset = stmt.executeQuery("select * from bankworker");
            while (resultset.next()) {
                ArrayList<String> l = new ArrayList<>();
                l.add(String.valueOf(resultset.getInt("bankworker_id")));
                l.add(resultset.getString("Fname"));
                l.add(resultset.getString("Lname"));
                l.add(resultset.getString("Login"));
                l.add(resultset.getString("Password"));
                list.add(l);
            }
        } else if (at.equals("c")) {
            ResultSet resultset = stmt.executeQuery("select * from client");
            while (resultset.next()) {
                ArrayList<String> l = new ArrayList<>();
                l.add(String.valueOf(resultset.getInt("client_id")));
                l.add(resultset.getString("Fname"));
                l.add(resultset.getString("Lname"));
                l.add(resultset.getString("Login"));
                l.add(resultset.getString("Password"));
                l.add(String.valueOf(resultset.getDouble("money_som")));
                l.add(String.valueOf(resultset.getDouble("money_dollar")));
                list.add(l);
            }
        }
        return list;
    }
}
