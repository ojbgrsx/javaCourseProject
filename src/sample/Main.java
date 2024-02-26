package sample;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("\nYou are welcome Dear User !!!".toUpperCase());
        run();
    }

    public static void run() throws Exception {
        Scanner scan = new Scanner(System.in);
        ArrayList<ArrayList<String>> workers = Other_Methods.getData(Objects.requireNonNull(ConnectionDB.getConnection()), "w");
        ArrayList<ArrayList<String>> clients = Other_Methods.getData(ConnectionDB.getConnection(), "c");
        System.out.println("\n1) Sign in as WORKER\n2) Sign in as CLIENT\n3) Sign up\n4) Exit");
        System.out.print("Select >>> ");
        int account_type = scan.nextInt();
        String qwerty = scan.nextLine();
        if (account_type == 1) {
            int a, b;
            a = b = 0;
            for (int q = 3; q > 0; q--) {
                System.out.print("Enter your username >>> ");
                String username = scan.nextLine().toLowerCase();
                for (ArrayList<String> worker : workers) {
                    if (worker.get(3).equalsIgnoreCase(username)) {
                        for (int i = 3; i > 0; i--) {
                            System.out.print("Enter your password >>> ");
                            String password = scan.nextLine().toLowerCase();
                            if (password.equalsIgnoreCase(worker.get(4))) {
                                System.out.println("\n\tYou successfully signed in "
                                        + worker.get(1).toUpperCase() + " !!!");
                                Workers_Menu.worker_account(ConnectionDB.getConnection());
                                run();
                                a++;
                                break;
                            } else {
                                System.out.println("Wrong password !!!");
                                if (i != 1) {
                                    System.out.printf("%d attempts left%n", i - 1);
                                }
                                b++;
                            }
                        }
                        break;
                    }
                }
                if (a == 1 || b == 3) {
                    break;
                } else {
                    System.out.println("Wrong username !!!");
                    if (q != 1) {
                        System.out.printf("%d attempts left%n", q - 1);
                    }
                }
            }
        } else if (account_type == 2) {
            int a, b;
            a = b = 0;
            for (int q = 3; q > 0; q--) {
                System.out.print("Enter your username >>> ");
                String username = scan.nextLine().toLowerCase();
                for (ArrayList<String> client : clients) {
                    if (client.get(3).equalsIgnoreCase(username)) {
                        for (int i = 3; i > 0; i--) {
                            System.out.print("Enter your password >>> ");
                            String password = scan.nextLine();
                            if (password.equalsIgnoreCase(client.get(4))) {
                                System.out.println("\n\tYou successfully signed in "
                                        + client.get(1).toUpperCase() + " !!!");
                                Clients_Menu.clients_account(ConnectionDB.getConnection(), username, password);
                                run();
                                a++;
                                break;
                            } else {
                                System.out.println("Wrong password !!!");
                                if (i != 1) {
                                    System.out.printf("%d attempts left%n", i - 1);
                                }
                                b++;
                            }
                        }
                        break;
                    }
                }
                if (a == 1 || b == 3) {
                    break;
                } else {
                    System.out.println("Wrong username !!!");
                    if (q != 1) {
                        System.out.printf("%d attempts left%n", q - 1);
                    }
                }
            }
        } else if (account_type == 3) {
            System.out.println("\n1)As WORKER\n2)As CLIENT\n");
            System.out.print("Please choose >>> ");
            char qr = scan.next().charAt(0);
            if (qr != '1' && qr != '2') {
                System.out.println("Wrong character, please try again !!!");
                run();
            }
            Registration.registration(ConnectionDB.getConnection(), qr);
        } else if (account_type == 4) {
            System.out.println("\nThe program is over, we look forward to your return`");
        } else {
            System.out.println("\nWrong input, try again !");
            run();
        }
        scan.close();
    }
}

