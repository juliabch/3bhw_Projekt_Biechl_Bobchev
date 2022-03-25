import Models.*;

import java.util.Scanner;

public class Login_Hauptprogramm {
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {

        userAccount();

    }
    public  static void userAccount() {
        Login newAccount =new Login();
        try {
            System.out.println("1) Create User-Account");
            System.out.println("2) Login User-Account");
            System.out.println("Enter choice:");
            String choice = s.nextLine();
            if (choice.equals("1")) {
                newAccount.createAccount();
            } else if (choice.equals("2")) {
                newAccount.loginAccount();
            } else {
                System.out.println("This option is not available right now!");
            }
        } catch (Exception ex) {
        }

    }
}
