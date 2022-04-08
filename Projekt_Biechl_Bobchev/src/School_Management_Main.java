import Models.*;
import Models.DB.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class School_Management_Main {
    static Scanner reader = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {

        /*Teacher t = new Teacher(1, "Julia", "Biechl", LocalDate.of(2005,9,1), "biechl.julia@gmail.com", Gender.female, "3bhwii"  );

        Subject s1 = new Subject(1, "Mathematik");
        Subject s2 = new Subject(2, "Deutsch");
        Subject s3 = new Subject(3, "Englisch");
        Subject s4 = new Subject(4, "Softwareprogrammierung");
        Subject s5 = new Subject(5, "Geschichte");
        Subject s6 = new Subject(6, "Informatik");

        t.addSubject(s1);
        t.addSubject(s2);
        t.addSubject(s3);

        System.out.println(t);

        Student s = new Student(1, "Eva", "Bobchev", LocalDate.of(2005,06,04), "bobchev.eva@gmail.com", Gender.female, "3bhwii", "S101");

        System.out.println(s);
        userAccount();
*/
        IRepository_School rep = null;
        Student s = new Student(1, "Eva", "Bobchev", LocalDate.of(2005,06,04), "bobchev.eva@gmail.com", Gender.female, "3bhwii", "S101");
        Teacher t = new Teacher(1, "Julia", "Biechl", LocalDate.of(2005,9,1), "biechl.julia@gmail.com", Gender.female, "3bhwii"  );
        Subject s1 = new Subject(1, "Mathematik");
        Subject s2 = new Subject(2, "Deutsch");
        Subject s3 = new Subject(3, "Englisch");
        Subject s4 = new Subject(4, "Softwareprogrammierung");
        Subject s5 = new Subject(5, "Geschichte");
        Subject s6 = new Subject(6, "Informatik");

        try{
            rep = new Repository_School();
            rep.open();

            /*already created in db
            rep.createTeacher(t);

            rep.createStudent(s);

            rep.createSubject(s1);
            rep.createSubject(s2);
            rep.createSubject(s3);
            rep.createSubject(s4);
            rep.createSubject(s5);
            rep.createSubject(s6);*/


        }
        catch (ClassNotFoundException e){
            System.out.println("DB-Treiber konnte nicht geladen werden!");
        }
        catch (SQLException e){
            System.out.println("Datenbankfehler!");
            System.out.println(e.getMessage() +"\n");
            e.printStackTrace();
        }
        finally{
            try {
                rep.close();
            }
            catch (SQLException e){
                System.out.println("Verbindung konnte nicht geschlossen werden!");
            }
        }

    }
    public  static void userAccount() {
        Login newAccount =new Login();
        try {
            System.out.println("1) Create User-Account");
            System.out.println("2) Login User-Account");
            System.out.println("Enter choice:");
            String choice = reader.nextLine();
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
