package sample;

import java.sql.*;
import java.util.*;

public class Registration {
    public static void registration(Connection connection, Character ac) throws Exception {
        Scanner scanREG = new Scanner(System.in);
        System.out.print("\nEnter your name >>> ");
        String name = scanREG.nextLine();
        System.out.print("Enter your surname >>> ");
        String surname = scanREG.nextLine();
        System.out.print("Enter username >>> ");
        String username = scanREG.nextLine().toLowerCase();
        System.out.print("Enter password >>> ");
        String password = scanREG.nextLine();
        String insertion, values;
        int money_som, money_dollar;
        if (ac.equals('1')) {
            insertion = String.format("INSERT INTO %s (Fname,Lname,Login,Password)", "bankworker");
            values = "VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insertion + values);
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, username);
            ps.setString(4, password);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("\n\tThis username already exists, try another username !!! \n");
                registration(connection, ac);
            }
            System.out.println("\nYou've successfully signed up as worker !!!");
        } else {
            System.out.print("Enter your fund in soms >>> ");
            money_som = scanREG.nextInt();
            System.out.print("Enter your fund in dollars >>> ");
            money_dollar = scanREG.nextInt();
            insertion = String.format("INSERT INTO %s (Fname,Lname,Login,Password,money_som,money_dollar)",
                    "client");
            values = "VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insertion + values);
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setDouble(5, money_som);
            ps.setDouble(6, money_dollar);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("\n\tThis username already exists, try another username !!! \n");
                registration(connection, ac);
            }

            System.out.println("\nYou've successfully signed up as client !!!");
        }
        Main.run();
        scanREG.close();
    }
}
