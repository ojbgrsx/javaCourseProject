package sample;

import java.time.LocalDate;
import java.util.*;
import java.sql.*;

public class Clients_Menu {
    public static void clients_account(Connection connection, String login, String password) throws SQLException {
        Scanner scanCL = new Scanner(System.in);
        String client_id = "";
        double soms = 0;
        double dollars = 0;
        try {
            String sql = "SELECT * FROM client WHERE Login = '" + login + "' and Password = '" + password + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                client_id = resultSet.getString("client_id");
                soms = resultSet.getDouble("money_som");
                dollars = resultSet.getDouble("money_dollar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println();
            System.out.println("Please, dial the number to run the program, if you are finished, dial 9:");
            System.out.println("1) Show my credit records");
            System.out.println("2) Show my funds:");
            System.out.println("3) Buy currency:");
            System.out.println("4) Make a money transfer on ID:");
            System.out.println("5) Transactions with my participation:");
            System.out.println("6) Close the loan:");
            System.out.println("7) Take the loan:");
            System.out.println("8) Back to the main menu");
            System.out.println("9) Exit");
            System.out.print("Menu selection >>> ");
            String selectedNum = scanCL.nextLine();
            System.out.println();
            switch (selectedNum) {
                case "1":
                    try {
                        String sql = "SELECT * FROM credit WHERE debtor_id = " + client_id;
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql);
                        String noCredit = "";
                        while (resultSet.next()) {
                            noCredit = "yes";
                            System.out.printf("Credit: credit_id: - %s; sum - %s; currency - %s; date - %s%n",
                                    resultSet.getString("credit_id"),
                                    resultSet.getString("sum"), resultSet.getString("currency"),
                                    resultSet.getString("credit_date"));
                        }
                        if (noCredit.isEmpty()) {
                            System.out.println("    No credits.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    System.out.print("    -> Dollars or Soms('D' or 'C') >>> ");
                    String cr = scanCL.nextLine();
                    if (cr.equalsIgnoreCase("d")) {
                        System.out.printf("    Amount of dollars: %.2f%n", dollars);
                    } else if (cr.equalsIgnoreCase("c")) {
                        System.out.printf("    Amount of soms: %.2f%n", soms);
                    } else {
                        System.out.println("    Wrong input, try again.");
                    }
                    break;
                case "3":
                    System.out.print("    -> Set the currency you want to buy(dollar/som) >>> ");
                    String setCurrency = scanCL.nextLine();
                    if (!setCurrency.equalsIgnoreCase("dollar") && !setCurrency.equalsIgnoreCase("som")) {
                        System.out.println("    Wrong currency input.");
                        continue;
                    }
                    System.out.print("    -> Set the sum you want to convert >> ");
                    String setSum = scanCL.nextLine();
                    double sum;
                    double subtract;
                    try {
                        sum = Double.parseDouble(setSum);
                    } catch (NumberFormatException e) {
                        System.out.println("    Wrong input number.");
                        continue;
                    }
                    if (sum <= 0) {
                        System.out.println("    The sum must be greater than zero.");
                        continue;
                    }
                    if (setCurrency.equalsIgnoreCase("som")) {
                        subtract = sum / 84.79;
                        if (dollars < subtract) {
                            System.out.println("    You do not have enough funds for such sum.");
                            continue;
                        }
                    } else {
                        subtract = sum * 84.79;
                        if (soms < subtract) {
                            System.out.println("    You do not have enough funds for such sum.");
                            continue;
                        }
                    }
                    try {
                        String sql = "UPDATE client SET "
                                + (setCurrency.equalsIgnoreCase("som") ? "money_dollar" : "money_som") +
                                " = " + (setCurrency.equalsIgnoreCase("som") ? dollars - subtract : soms - subtract) +
                                " WHERE client_id = " + client_id;
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                        if (setCurrency.equalsIgnoreCase("som")) {
                            dollars -= subtract;
                        } else {
                            soms -= subtract;
                        }
                        sql = "UPDATE client SET "
                                + (setCurrency.equalsIgnoreCase("som") ? "money_som" : "money_dollar") +
                                " = " + (setCurrency.equalsIgnoreCase("som") ? soms + sum : dollars + sum) +
                                " WHERE client_id = " + client_id;
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                        if (setCurrency.equalsIgnoreCase("dollar")) {
                            dollars += sum;
                        } else {
                            soms += sum;
                        }
                        System.out.println("    The process was completed successfully.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    System.out.print("    -> Please enter the client's ID >>> ");
                    String setId = scanCL.nextLine();
                    if (setId.equals(client_id)) {
                        System.out.println(
                                "    Sorry, but according to our policies you are not allowed to make a transfer on your ID.");
                        continue;
                    }
                    double enter_id = 0;
                    try {
                        enter_id = Double.parseDouble(setId);
                    } catch (NumberFormatException e) {
                        System.out.println("    Wrong format input.");
                    }
                    double moneySom = 0;
                    double moneyDollar = 0;
                    String id = "";
                    try {
                        String sql = "SELECT * FROM client WHERE client_id = " + enter_id;
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            id = resultSet.getString("client_id");
                            moneySom = resultSet.getDouble("money_som");
                            moneyDollar = resultSet.getDouble("money_dollar");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (id.isEmpty()) {
                        System.out.println("    No client with such name and surname was found, try again.");
                        continue;
                    }
                    System.out.print("    Enter currency type for transfer(dollar/som) >>> ");
                    String transferCurrency = scanCL.nextLine();
                    if (!transferCurrency.equalsIgnoreCase("dollar") && !transferCurrency.equalsIgnoreCase("som")) {
                        System.out.println("    Wrong currency input.");
                        continue;
                    }
                    System.out.print("    -> Set the transfer sum >>> ");
                    String transferSum = scanCL.nextLine();
                    double s = 0;
                    try {
                        s = Double.parseDouble(transferSum);
                    } catch (NumberFormatException e) {
                        System.out.println("    Wrong number input.");
                    }
                    if (s <= 0) {
                        System.out.println("    Transfer amount must be greater than zero.");
                        continue;
                    } else if ((transferCurrency.equals("dollar") && s > dollars)
                            || (transferCurrency.equals("som") && s > soms)) {
                        System.out.println("    You have not enough money for such transfer.");
                        continue;
                    }
                    try {
                        String sql = "UPDATE client SET "
                                + (transferCurrency.equalsIgnoreCase("dollar") ? "money_dollar" : "money_som") +
                                " = " + (transferCurrency.equalsIgnoreCase("dollar") ? (dollars - s) : soms - s) +
                                " WHERE client_id = " + client_id;
                        dollars -= s;
                        soms -= s;
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                        sql = "UPDATE client SET "
                                + (transferCurrency.equalsIgnoreCase("dollar") ? "money_dollar" : "money_som") +
                                " = " + (transferCurrency.equalsIgnoreCase("dollar") ? (moneyDollar + s) : moneySom + s)
                                +
                                " WHERE client_id = " + id;
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("    The process was completed successfully.");
                    break;
                case "5":
                    System.out.print("    -> Sent to me(1) | by me(2) >>> ");
                    String answer = scanCL.nextLine();
                    if (!answer.equals("1") && !answer.equals("2")) {
                        System.out.println("    Wrong input. You can enter only (1) or (2).");
                        continue;
                    }
                    try {
                        String sql = "SELECT * FROM transaction WHERE "
                                + (answer.equals("1") ? "receiver_id" : "sender_id") +
                                " = " + client_id;
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            System.out.println("Transaction:");
                            System.out.printf(
                                    "            Sum -> %s; Currency -> %s; Country -> %s; City -> %s; Date: %s%n",
                                    resultSet.getString("sum"), resultSet.getString("currency"),
                                    resultSet.getString("country"), resultSet.getString("city"),
                                    resultSet.getString("transaction_date"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "6":
                    System.out.print("    Enter ID of the credit >>> ");
                    int loanID;
                    try {
                        loanID = Integer.parseInt(scanCL.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("    Wrong input format, you can use only numbers.");
                        continue;
                    }
                    String sql = "SELECT * FROM credit WHERE credit_id = " + loanID;
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    double creditAmount = 0;
                    String debtor_id = "";
                    String currency = "";
                    while (resultSet.next()) {
                        creditAmount = resultSet.getDouble("sum");
                        debtor_id = resultSet.getString("debtor_id");
                        currency = resultSet.getString("currency");
                    }
                    if (creditAmount == 0) {
                        System.out.println("    The credit with given ID was not found.");
                    } else if (!client_id.equals(debtor_id)) {
                        System.out.println("    Sorry, but the credit you want to close belongs to another person.");
                        System.out.println("    You can check your credits with option 1.");
                    } else if ((currency.equals("dollar") && creditAmount > dollars) ||
                            (currency.equals("som") && creditAmount > soms)) {
                        System.out.println("    Sorry, but you have not enough funds to close the loan.");
                    } else {
                        String creditSQL = "DELETE FROM credit WHERE credit_id = " + loanID;
                        PreparedStatement creditPreparedStatement = connection.prepareStatement(creditSQL);
                        creditPreparedStatement.executeUpdate();
                        creditPreparedStatement.close();
                        String updateClientSQL = "UPDATE client SET "
                                + (currency.equals("dollar") ? "money_dollar" : "money_som")
                                + " = " + (currency.equals("dollar") ? dollars - creditAmount : soms - creditAmount) +
                                " WHERE client_id = " + client_id;
                        PreparedStatement updatePStatement = connection.prepareStatement(updateClientSQL);
                        updatePStatement.executeUpdate();
                        updatePStatement.close();
                        if (currency.equals("dollar")) {
                            dollars -= creditAmount;
                        } else {
                            soms -= creditAmount;
                        }
                        System.out.println("    Congratulations, your loan was closed successfully.");
                    }
                    break;
                case "9":
                    System.exit(0);
                case "8":
                    return;
                case "7":
                    System.out.print("\tSet the currency you want to take a loan(dollar/som) >>> ");
                    String cur = scanCL.nextLine();
                    if (cur.equalsIgnoreCase("dollar") || cur.equalsIgnoreCase("som")){
                        String insertion = String.format("INSERT INTO %s (sum,currency,credit_date,debtor_id)", "credit");
                        String values = "VALUES (?,?,?,?)";
                        System.out.print("\tEnter sum >>> ");
                        int summ = scanCL.nextInt();
                        scanCL.nextLine();
                        LocalDate date = LocalDate.now();
                        PreparedStatement ps = connection.prepareStatement(insertion + values);
                        ps.setInt(1,summ);
                        ps.setString(2,cur);
                        ps.setString(3, String.valueOf(date));
                        ps.setInt(4,Integer.parseInt(client_id));
                        ps.executeUpdate();
                        System.out.println("You have taken a loan !");
                    }
                    else{
                        System.out.println("Wrong input, try again !");
                    }
                    break;
                default:
                    System.out.println("    Wrong input.");
                    break;
            }
        }
    }
}
