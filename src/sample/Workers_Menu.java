package sample;

import java.util.*;
import java.time.*;
import java.sql.*;

public class Workers_Menu {
    public static void worker_account(Connection connection) {
        try {
            String selectedNum = "";
            int currentClientID = -1;
            double conv = 0;
            String setCurrency = "";
            double soms = 0;
            double dollars = 0;
            ArrayList<ArrayList<String>> rowData = Other_Methods.getData(connection, "c");
            while (!selectedNum.equals("8")) {
                Scanner scanBW = new Scanner(System.in);
                System.out.println();
                System.out.println("Please, dial the number to run the program, if you are finished, dial 9:");
                System.out.println("1) Show the list of clients");
                System.out.println("2) Find and select the client:");
                System.out.println("3) Show the name of the client who took the maximum credit");
                System.out.println("4) Show the name of the client who took the minimum credit");
                System.out.println("5) Set a new amount for conversion:");
                System.out.println("6) Make a money transfer:");
                System.out.println("7) Calculate the amount of transfers:");
                System.out.println("8) Back to the main menu");
                System.out.println("9) Exit");
                System.out.print("Menu selection >>> ");
                selectedNum = scanBW.nextLine();
                System.out.println();
                switch (selectedNum) {
                    case "1":
                        try {
                            Formatter formatter = new Formatter();
                            System.out.println(formatter.format("%2s %20s %20s", "ID", "Name", "Surname"));
                            formatter = new Formatter();
                            System.out.println(formatter.format("%2s %20s %20s", "--", "----", "-------"));
                            for (int i = 0; i < rowData.size(); i++) {
                                for (int j = 0; j < rowData.get(i).size(); j++) {
                                    if (j == 0) {
                                        formatter = new Formatter();
                                        System.out.print(formatter.format("%2s", rowData.get(i).get(j)) + " ");
                                    }
                                    formatter = new Formatter();
                                    if (j == 1 || j == 2) {
                                        System.out.print(formatter.format("%20s", rowData.get(i).get(j)) + " ");
                                    }
                                }
                                System.out.println();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "2":
                        System.out.print("Please enter name of client >>> ");
                        String name = scanBW.nextLine();
                        System.out.println();
                        ArrayList<String> list = new ArrayList<>();
                        int co = 0;
                        for (int i = 0; i < rowData.size(); i++) {
                            for (int j = 0; j < rowData.get(i).size(); j++) {
                                if (name.equalsIgnoreCase(rowData.get(i).get(j))) {
                                    co++;
                                    list.addAll(rowData.get(i));
                                }
                            }
                        }
                        if (co == 1) {
                            Formatter formatter = new Formatter();
                            System.out
                                    .println(formatter.format("%2s %20s %20s %20s %20s", "ID", "Name", "Surname",
                                            "Soms",
                                            "Dollars"));
                            formatter = new Formatter();
                            System.out
                                    .println(formatter.format("%2s %20s %20s %20s %20s", "--", "----", "-------",
                                            "----",
                                            "-------"));
                            for (int i = 0; i < list.size(); i++) {
                                if (i == 0) {
                                    formatter = new Formatter();
                                    System.out.print(formatter.format("%2s", list.get(i)) + " ");
                                    currentClientID = Integer.parseInt(list.get(i));
                                }
                                formatter = new Formatter();
                                if (i != 0 && i != 3 && i != 4) {
                                    System.out.print(formatter.format("%20s", list.get(i)) + " ");
                                }
                            }
                        } else {
                            System.out.println("We couldn't find client wtih this name, try again !");
                            worker_account(connection);
                        }
                        System.out.println();

                        break;
                    case "3":
                        try {
                            String crn;
                            for (int i = 0; i < 2; i++) {
                                if (i == 0) {
                                    crn = "'dollar'";
                                } else {
                                    crn = "'som'";
                                }
                                String sql = "SELECT debtor_id, sum from credit WHERE currency = " + crn + " and sum = "
                                        +
                                        "(SELECT MAX(sum) from credit group by currency HAVING currency = " + crn + ")";
                                Statement statement = connection.createStatement();
                                ResultSet resultSet = statement.executeQuery(sql);
                                while (resultSet.next()) {
                                    int id = resultSet.getInt("debtor_id");
                                    int sum = resultSet.getInt("sum");
                                    String sqlN = "SELECT Fname, Lname FROM client WHERE client_id = " + id;
                                    Statement statementN = connection.createStatement();
                                    ResultSet resultSetN = statementN.executeQuery(sqlN);
                                    while (resultSetN.next()) {
                                        String fN = resultSetN.getString("Fname");
                                        String lN = resultSetN.getString("Lname");
                                        System.out.println("The biggest " + crn + " credit belongs to " + fN +
                                                " " + lN + " which is " + sum + ((crn.equals("'dollar'")) ? "$" : "C"));
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "4":
                        try {
                            String crn;
                            for (int i = 0; i < 2; i++) {
                                if (i == 0) {
                                    crn = "'dollar'";
                                } else {
                                    crn = "'som'";
                                }
                                String sql = "SELECT debtor_id, sum from credit WHERE currency = " + crn + " and sum = "
                                        +
                                        "(SELECT MIN(sum) from credit group by currency HAVING currency = " + crn + ")";
                                Statement statement = connection.createStatement();
                                ResultSet resultSet = statement.executeQuery(sql);
                                while (resultSet.next()) {
                                    int id = resultSet.getInt("debtor_id");
                                    int sum = resultSet.getInt("sum");
                                    String sqlN = "SELECT Fname, Lname FROM client WHERE client_id = " + id;
                                    Statement statementN = connection.createStatement();
                                    ResultSet resultSetN = statementN.executeQuery(sqlN);
                                    while (resultSetN.next()) {
                                        String fN = resultSetN.getString("Fname");
                                        String lN = resultSetN.getString("Lname");
                                        System.out.println("The smallest " + crn + " credit belongs to " + fN +
                                                " " + lN + " which is " + sum + ((crn.equals("'dollar'")) ? "$" : "C"));
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "5":
                        if (currentClientID == -1) {
                            System.out.println("        Sorry, but you have to choose the client to work with");
                            System.out.println("        In order to set the client, dial 2");
                        } else {
                            System.out.print("        Set for dollar or som('dollar'/'som'): ");
                            setCurrency = scanBW.nextLine();
                            if (!setCurrency.equalsIgnoreCase("dollar") && !setCurrency.equalsIgnoreCase("som")) {
                                System.out.println("Wrong currency input");
                                setCurrency = "";
                                continue;
                            }
                            System.out.print("        Enter the sum: ");
                            String strSetSum = scanBW.nextLine();
                            try {
                                double setSum = Double.parseDouble(strSetSum);
                                if (setSum <= 0) {
                                    System.out.println("        The conversion is not possible with a such sum");
                                } else {
                                    try {
                                        String sql = "SELECT money_som, money_dollar FROM client WHERE client_id = "
                                                + currentClientID;
                                        Statement statement = connection.createStatement();
                                        ResultSet resultSet = statement.executeQuery(sql);
                                        while (resultSet.next()) {
                                            soms = resultSet.getDouble("money_som");
                                            dollars = resultSet.getDouble("money_dollar");
                                            if (setCurrency.equalsIgnoreCase("som")) {
                                                if (setSum > soms) {
                                                    System.out.println(
                                                            "        You do not have enough funds for such transfer");
                                                } else {
                                                    conv = setSum;
                                                }
                                            } else if (setCurrency.equalsIgnoreCase("dollar")) {
                                                if (setSum > dollars) {
                                                    System.out.println(
                                                            "        You do not have enough funds for such transfer");
                                                } else {
                                                    conv = setSum;
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Wrong number input");
                            }
                        }
                        break;
                    case "6":
                        if (conv == 0) {
                            System.out.println("    Sorry, but you have to set money for conversion.");
                            System.out.println("    In order to do it, dial 5");
                        } else {
                            System.out.println("        Please select the direction of money transfer:");
                            System.out.println("        -> 1. International: >>> (If sent by this transfer, " +
                                    "then a commission is charged in the form of 5% of the transfer amount)");
                            System.out.println("        -> 2. Inside Kyrgyzstan: >>> (If sent by this transfer, " +
                                    "then a commission is charged in the form of 1.5% of the transfer amount)");
                            System.out.print("        Enter 'INT'(International) or 'INKG'(Inside Kyrgyzstan) >>> ");
                            String convType = scanBW.nextLine();
                            String f = "";
                            String l = "";
                            double money = 0;
                            int receiverId = -1;
                            if (convType.equals("INT")) {
                                System.out.print("      • Please enter the country for transfer: >>> ");
                                String country = scanBW.nextLine();
                                System.out.print("      • Please enter the city: >>> ");
                                String city = scanBW.nextLine();
                                System.out.print(
                                        "      • Please enter the first name of the person to whom the transfer is sent >>> ");
                                String firstName = scanBW.nextLine();
                                System.out.print(
                                        "      • Please enter the last name of the person to whom the transfer is sent >>> ");
                                String lastName = scanBW.nextLine();
                                try {
                                    String sql = "SELECT * FROM client WHERE Fname = '" + firstName + "'" +
                                            " and Lname = '" + lastName + "'";
                                    Statement statement = connection.createStatement();
                                    ResultSet resultSet = statement.executeQuery(sql);
                                    while (resultSet.next()) {
                                        receiverId = resultSet.getInt("client_id");
                                        f = resultSet.getString("Fname");
                                        l = resultSet.getString("Lname");
                                        money = resultSet.getDouble(
                                                setCurrency.equalsIgnoreCase("som") ? "money_som" : "money_dollar");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (!f.isEmpty()) {
                                    try {
                                        String transactionSQL = "INSERT INTO transaction (sum, currency, country, city, transaction_date,"
                                                +
                                                " sender_id, receiver_id, code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                                        LocalDate localDate = LocalDate.now();
                                        PreparedStatement preparedStatement = connection
                                                .prepareStatement(transactionSQL);
                                        String date = localDate.getYear() + "-" + localDate.getMonthValue() + "-"
                                                + localDate.getDayOfMonth();
                                        String code = Other_Methods.createCode();
                                        preparedStatement.setString(1, String.valueOf(conv - conv * 0.05));
                                        preparedStatement.setString(2,
                                                setCurrency.equalsIgnoreCase("som") ? "som" : "dollar");
                                        preparedStatement.setString(3, country);
                                        preparedStatement.setString(4, city);
                                        preparedStatement.setString(5, date);
                                        preparedStatement.setString(6, String.valueOf(currentClientID));
                                        preparedStatement.setString(7, String.valueOf(receiverId));
                                        preparedStatement.setString(8, code);
                                        preparedStatement.executeUpdate();
                                        preparedStatement.close();
                                        System.out.printf("Your transfer on name %s was completed successfully%n" +
                                                "(%s, %s)%n",
                                                f + " " + l, city, country);
                                        System.out.println("Code to receive money: " + code);
                                        String updateSQL = "UPDATE client SET "
                                                + (setCurrency.equalsIgnoreCase("som") ? "money_som" : "money_dollar")
                                                + " = " +
                                                ((setCurrency.equalsIgnoreCase("som") ? soms : dollars) - conv)
                                                + " WHERE client_id = " + currentClientID;
                                        PreparedStatement preparedStatement1 = connection.prepareStatement(updateSQL);
                                        preparedStatement1.executeUpdate();
                                        preparedStatement1.close();
                                        updateSQL = "UPDATE client SET "
                                                + (setCurrency.equalsIgnoreCase("som") ? "money_som" : "money_dollar")
                                                + " = " +
                                                (money + (setCurrency.equalsIgnoreCase("som") ? conv - conv * 0.05
                                                        : conv - conv * 0.05))
                                                + " WHERE client_id = " + receiverId;
                                        preparedStatement1 = connection.prepareStatement(updateSQL);
                                        preparedStatement1.executeUpdate();
                                        preparedStatement1.close();
                                        conv = 0;
                                        setCurrency = "";
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                } else {
                                    System.out.println("    No client with given name and surname was found");
                                }
                            } else if (convType.equalsIgnoreCase("INKG")) {
                                System.out.print("      • Please enter the city: >>> ");
                                String city = scanBW.nextLine();
                                System.out.print(
                                        "      • Please enter the first name of the person to whom the transfer is sent >>> ");
                                String firstName = scanBW.nextLine();
                                System.out.print(
                                        "      • Please enter the last name of the person to whom the transfer is sent >>> ");
                                String lastName = scanBW.nextLine();
                                try {
                                    String sql = "SELECT * FROM client WHERE Fname = '" + firstName + "'" +
                                            " and Lname = '" + lastName + "'";
                                    Statement statement = connection.createStatement();
                                    ResultSet resultSet = statement.executeQuery(sql);
                                    while (resultSet.next()) {
                                        receiverId = resultSet.getInt("client_id");
                                        f = resultSet.getString("Fname");
                                        l = resultSet.getString("Lname");
                                        money = resultSet.getDouble(
                                                setCurrency.equalsIgnoreCase("som") ? "money_som" : "money_dollar");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (!f.isEmpty()) {
                                    if (conv == 0) {
                                        System.out.println("    Sorry, but you have to set money for conversion.");
                                        System.out.println("    In order to do it, dial 5");
                                    } else {
                                        try {
                                            String transactionSQL = "INSERT INTO transaction (sum, currency, city, transaction_date,"
                                                    +
                                                    " sender_id, receiver_id, code) VALUES (?, ?, ?, ?, ?, ?, ?)";
                                            LocalDate localDate = LocalDate.now();
                                            PreparedStatement preparedStatement = connection
                                                    .prepareStatement(transactionSQL);
                                            String date = localDate.getYear() + "-" + localDate.getMonthValue() + "-"
                                                    + localDate.getDayOfMonth();
                                            String code = Other_Methods.createCode();
                                            preparedStatement.setString(1, String.valueOf(conv - conv * 0.015));
                                            preparedStatement.setString(2,
                                                    setCurrency.equalsIgnoreCase("som") ? "som" : "dollar");
                                            preparedStatement.setString(3, city);
                                            preparedStatement.setString(4, date);
                                            preparedStatement.setString(5, String.valueOf(currentClientID));
                                            preparedStatement.setString(6, String.valueOf(receiverId));
                                            preparedStatement.setString(7, code);
                                            preparedStatement.executeUpdate();
                                            preparedStatement.close();
                                            System.out.printf(
                                                    "Your transfer on name %s was completed successfully%n(%s, Kyrgyzstan)%n",
                                                    f + " " + l, city);
                                            System.out.println("Code to receive money: " + code);
                                            String updateSQL = "UPDATE client SET "
                                                    + (setCurrency.equalsIgnoreCase("som") ? "money_som"
                                                            : "money_dollar")
                                                    + " = " +
                                                    ((setCurrency.equalsIgnoreCase("som") ? soms : dollars) - conv)
                                                    + " WHERE client_id = " + currentClientID;
                                            PreparedStatement preparedStatement1 = connection
                                                    .prepareStatement(updateSQL);
                                            preparedStatement1.executeUpdate();
                                            preparedStatement1.close();
                                            updateSQL = "UPDATE client SET "
                                                    + (setCurrency
                                                            .equalsIgnoreCase("som") ? "money_som" : "money_dollar")
                                                    + " = " +
                                                    (money + (setCurrency.equalsIgnoreCase("som") ? conv - conv * 0.015
                                                            : conv - conv * 0.015))
                                                    + " WHERE client_id = " + receiverId;
                                            preparedStatement1 = connection.prepareStatement(updateSQL);
                                            preparedStatement1.executeUpdate();
                                            preparedStatement1.close();
                                            conv = 0;
                                            setCurrency = "";
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } else {
                                    System.out.println("    No client with given name and surname was found");
                                }
                            } else {
                                System.out.println("        Nonexistent option");
                            }
                        }
                        break;
                    case "7":
                        System.out.println("        a) For each month of the current year");
                        System.out.println("        b) For quarter");
                        System.out.println("        c) For all the time");
                        System.out.println("        d) In a certain area:");
                        System.out.print("        Choose a|b|c|d: ");
                        String opt = scanBW.nextLine();
                        switch (opt) {
                            case "a":
                                try {
                                    System.out.println("\nMonths without transactions will not be shown\n");
                                    String sq = "SELECT currency, EXTRACT(MONTH from transaction_date), SUM(sum) FROM transaction where YEAR(transaction_date) ="
                                            + LocalDate.now().getYear() + " GROUP BY currency, MONTH(transaction_date)";
                                    Statement st = connection.createStatement();
                                    ResultSet rs = st.executeQuery(sq);
                                    String m;
                                    String c1;
                                    String s1;
                                    int c = 0;
                                    while (rs.next()) {
                                        m = Other_Methods.month(rs.getInt(2));
                                        c1 = rs.getString(1);
                                        s1 = rs.getString(3);
                                        System.out.println("Month: " + m);
                                        System.out.println("Currency: " + c1);
                                        System.out.println("Total: " + s1);
                                        c++;
                                        if (c == 2) {
                                            System.out.println();
                                            c = 0;
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "b":
                                try {
                                    for (int i = 0; i < 12; i += 3) {
                                        String sql = "SELECT currency, SUM(sum) FROM transaction where YEAR(transaction_date) = '"
                                                + LocalDate.now().getYear() + "'\n" +
                                                "and MONTH(transaction_date) > '" + i
                                                + "' and MONTH(transaction_date) < '" + (i + 4) + "' GROUP BY currency";
                                        Statement statement = connection.createStatement();
                                        ResultSet resultSet = statement.executeQuery(sql);
                                        System.out.println(Other_Methods.quarter(i) + " quarter:");
                                        String print = "";
                                        while (resultSet.next()) {
                                            print = "        Currency: " + resultSet.getString(1) +
                                                    "\n        Total of transactions: " + resultSet.getString(2);
                                            System.out.println(print);
                                        }
                                        if (print.isEmpty()) {
                                            System.out.println("        No transactions were made in this quarter.");
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "c":
                                try {
                                    String sql = "SELECT currency, SUM(sum) FROM transaction GROUP BY currency";
                                    Statement statement = connection.createStatement();
                                    ResultSet resultSet = statement.executeQuery(sql);
                                    while (resultSet.next()) {
                                        System.out.println("Total of all transactions:" +
                                                "\n        Currency -> " + resultSet.getString(1) +
                                                "\n        Total -> " + resultSet.getString(2));
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "d":
                                System.out.print("        Choose: city(1) or country(2) >>> ");
                                String cityCounrty = scanBW.nextLine();
                                String sql;
                                String city;
                                String country;
                                String notFinded = "";
                                if (cityCounrty.equalsIgnoreCase("1")) {
                                    System.out.print("        Enter the city: ");
                                    city = scanBW.nextLine();
                                    sql = "SELECT city, currency, SUM(sum) FROM transaction WHERE city = '" + city
                                            + "' GROUP BY currency";
                                } else if (cityCounrty.equalsIgnoreCase("2")) {
                                    System.out.print("        Enter the country: ");
                                    country = scanBW.nextLine();
                                    sql = "SELECT country, currency, SUM(sum) FROM transaction WHERE country = '"
                                            + country + "' GROUP BY currency";
                                } else {
                                    System.out.println("        Wrong input, try again.");
                                    continue;
                                }
                                try {
                                    Statement statement = connection.createStatement();
                                    ResultSet resultSet = statement.executeQuery(sql);
                                    while (resultSet.next()) {
                                        System.out.println("        Total "
                                                + (resultSet.getString(2).equalsIgnoreCase("dollar") ? "dollar" : "som")
                                                +
                                                " sum of transactions in " + resultSet.getString(1) +
                                                ": " + resultSet.getString(3));
                                        notFinded = "YES";
                                    }
                                    if (notFinded.isEmpty()) {
                                        System.out.println("    Not found");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                System.out.println("    No such option");
                                break;
                        }
                        System.out.println();
                        break;
                    case "8":
                        return;
                    case "9":
                        System.out.println("The program is over, we look forward to your return");
                        System.exit(0);
                    default:
                        System.out.println("    Wrong input");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
