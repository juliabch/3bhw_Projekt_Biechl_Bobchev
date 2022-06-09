import Models.*;
import Models.DB.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.HashMap;

public class School_Management_Main {
    static Scanner reader = new Scanner(System.in);
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {

        char choice;
        IRepository_School rep = null;

        //Hauptcode:
        try {
            rep = new Repository_School();
            rep.open();

            System.out.println("Schulmanagement: ");

            if(userAccount() == true){
                System.out.println("Hauptmenü: ");
                System.out.println("L ... Lehrer erstellen");
                System.out.println("S ... Schüler erstellen");
                System.out.println("F ... Schulfach hinzufügen");
                System.out.println("Z ... Lehrer ein Fach zuweisen");
                System.out.println("A ... alle Lehrer anzeigen");
                System.out.println("");
                switch (choice){}
            }





        }


        catch (ClassNotFoundException e) {
            System.out.println("DB-Treiber konnte nicht geladen werden!");
        }
        catch (SQLException e) {
            System.out.println("Datenbankfehler!");
            System.out.println(e.getMessage() + "\n");
            e.printStackTrace();
        }
        finally {
            try {
                rep.close();
            } catch (SQLException e) {
                System.out.println("Verbindung konnte nicht geschlossen werden!");
            }
        }

    }

    public static boolean userAccount() {
        Login newAccount =new Login();
        try {
            System.out.println("________________________");
            System.out.println("1) Create User-Account");
            System.out.println("2) Login User-Account");
            System.out.println("________________________");
            System.out.println("Enter choice:");
            String choice = s.nextLine();
            if (choice.equals("1")) {
                newAccount.createAccount();
                return false;
            } else if (choice.equals("2")) {
                newAccount.loginAccount();
                return true;
            } else {
                System.out.println("This option is not available right now!");
                return false;
            }
        }
        catch (Exception ex) {
        }

    }

}