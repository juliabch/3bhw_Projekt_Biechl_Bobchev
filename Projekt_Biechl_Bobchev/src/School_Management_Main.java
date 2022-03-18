import Models.*;

import java.time.LocalDate;

public class School_Management_Main {
    public static void main(String[] args) {

        Teacher t = new Teacher(1, "Julia", "Biechl", LocalDate.of(2005,9,1), "biechl.julia@gmail.com", Gender.female, true  );

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


    }
}
