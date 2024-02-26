package sample;

import java.util.Scanner;

public class testing {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("1)Worker\n2)Client");
        if (scan.nextInt() == 1) {
            Workers_Menu.worker_account(ConnectionDB.getConnection());
        } else {
            Clients_Menu.clients_account(ConnectionDB.getConnection(), "ojbgrsx", "ojbgrsx");
        }

        // public static void worker_account() throws Exception {
        // Scanner scanWOR = new Scanner(System.in);
        // ArrayList<ArrayList<String>> rowData =
        // Main.getData(ConnectionDB.getConnection(), "c");
        // String selectedNum = "";
        // while (!selectedNum.equals("9")) {
        // System.out.println();
        // System.out.println("Please, dial the number to run the program, if you are
        // finished, dial 9:");
        // System.out.println("\n1) Show the list of clients");
        // System.out.println("2) Find and select the client:");
        // System.out.println("3) Show the name of the client who took the
        // maximumcredit");
        // System.out.println("4) Show the name of the client who took the
        // minimumcredit");
        // System.out.println("5) Set a new amount for conversion:");
        // System.out.println("6) Make a money transfer:");
        // System.out.println("7) Calculate the amount of transfers:");
        // System.out.println("8) Back to the main menu");
        // System.out.println("9) Exit");
        // System.out.print("Menu selection >>> ");
        // selectedNum = scanWOR.nextLine();
        // System.out.println();
        // switch (selectedNum) {
        // case "1":
        // try {
        // Formatter formatter = new Formatter();
        // System.out
        // .println(formatter.format("%17s %17s", "Name", "Surname"));
        // formatter = new Formatter();
        // System.out
        // .println(formatter.format("%17s %17s", "----", "-------"));
        // for (int i = 0; i < rowData.size(); i++) {
        // for (int j = 0; j < rowData.get(i).size(); j++) {
        // formatter = new Formatter();
        // if (j == 0 || j == 1) {
        // System.out.print(formatter.format("%17s", rowData.get(i).get(j)) + " ");
        // }
        // }
        // System.out.println();
        // }
        // } catch (Exception e) {
        // System.out.println(e);
        // }
        // break;
        // case "2":

        // System.out.print("Please enter name of client >>> ");
        // String name = scanWOR.nextLine();
        // System.out.println();
        // ArrayList<String> list = new ArrayList<>();
        // int c = 0;
        // for (int i = 0; i < rowData.size(); i++) {
        // for (int j = 0; j < rowData.get(i).size(); j++) {
        // if (name.equalsIgnoreCase(rowData.get(i).get(j))) {
        // c++;
        // list.addAll(rowData.get(i));
        // }
        // }
        // }
        // if (c == 1) {
        // Formatter formatter = new Formatter();
        // System.out
        // .println(formatter.format("%2s %17s %17s %17s %17s", "ID", "Name", "Surname",
        // "Soms",
        // "Dollars"));
        // formatter = new Formatter();
        // System.out
        // .println(formatter.format("%2s %17s %17s %17s %17s", "--", "----", "-------",
        // "----",
        // "-------"));
        // for (int i = 0; i < list.size(); i++) {
        // if (i == 0) {
        // formatter = new Formatter();
        // System.out.print(formatter.format("%2s", list.get(i)) + " ");
        // }
        // formatter = new Formatter();
        // if (i != 0 && i != 2 && i != 3) {
        // System.out.print(formatter.format("%17s", list.get(i)) + " ");
        // }
        // }
        // }
        // System.out.println();
        // break;
        // case "3":
        // break;
        // case "4":
        // break;
        // case "5":
        // break;
        // case "6":
        // break;
        // case "7":
        // break;
        // case "8":
        // return;
        // case "9":
        // System.out.println("\nThe program is over, we look forward to your
        // return\n");
        // System.exit(0);
        // }
        // }
        // scanWOR.close();
        // }
    }

}
