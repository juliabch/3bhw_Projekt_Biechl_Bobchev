package Models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Login {
    Scanner s = new Scanner(System.in);
    String filename = "C://Daten//SWP-Projekt//Register.txt";

    public void createAccount() {
        try {
            Path p = Path.of(filename);

            System.out.println("Enter Username");
            String username = s.nextLine();
            System.out.println("Enter Password");
            String password = s.nextLine();
            Files.writeString(p, username + ";" + password + "\n", StandardOpenOption.APPEND);
            System.out.println("Account has been saved");
            // new UserAccount();
        } catch (Exception ex) {
            System.out.println("Account cant be saved");
        }
    }

    public void loginAccount() {
        try {
            Path p = Path.of(filename);
            // 1) Datei lesen und In Liste abspeichern
            Files.readAllLines(p);
            System.out.println();
            // 2) Splitten
            // 3) User/password eingeben
            // 4) Schauen ob sie zusammen passen



        } catch (Exception ex) {
            System.out.println("This User/Password does not exist");

        }
    }
    public void convertpersonTologin(List<String> lines){

    }
}