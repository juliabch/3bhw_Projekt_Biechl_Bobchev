import Models.*;
import Models.DB.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class School_Management_Main {
    private static HashMap<String,String> hashmapUser = new HashMap<>();
    private static String _username;
    private static String _password;
    public  String getusername(){ return this._username; }
    public  void setusername(String username){
        this._username = username;
    }
    public  String getpassword(){ return this._password; }
    public  void setpassword(String password){
        this._password = password;
    }
    public School_Management_Main() {
        this("", "");
    }
    public School_Management_Main(String username, String password){
        this.setusername(username);
        this.setpassword(password);
    }

    static School_Management_Main sm = new School_Management_Main();

    //Scanner für KLassen
    static Scanner reader = new Scanner(System.in);
    //Für Filemanagment
    static Scanner s = new Scanner(System.in);

    //Eva: String filename = "C://Daten//SWP-Projekt//Register.txt";
    //Julia:
    static String filename = "C://Users//biech//Desktop//3bhwii_Java//3bhw_Projekt_Biechl_Bobchev//User.txt";

    static IRepository_School repo = null;

    static List<Student> foundStudent = new ArrayList<Student>();
    static List<Teacher> foundTeacher = new ArrayList<>();

    public static int teacherchoice;
    public static int studentchoice;

    public static void main(String[] args) throws IOException {


        System.out.println(Files.readAllLines(Paths.get(filename)));
        try {
            repo = new Repository_School();
            repo.open();

            if (userAccount() == 2){
                if (loginAccount() == "teacher"){
                    System.out.println("Hello teacher!");
                    do {
                        teacherfunction();
                    }while (teacherchoice > 10 && teacherchoice > 0);
                }
                else if (loginAccount() == "student"){
                    System.out.println("Hello student!");
                    do {
                        studentfunction();
                        switch (studentchoice){
                            case 1:
                                studentviewstudentbyfirstname();
                                break;
                            case 2:
                                studentviewbyclass();
                                break;
                            case 3:
                                studentviewstudenbylastname();
                            case 4:
                                System.out.println("Closing program");
                            default:
                                System.out.println("Your choice is not available");
                                break;
                        }
                    }while (studentchoice < 4 && studentchoice >0);
                }
                else {
                    System.out.println("Closing Programm");
                }
            }
            else if(userAccount() == 1){
                createAccount();
            }
            else {
                System.out.println("This option is not available right now!");
            }




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int userAccount() {
        int choice;
            System.out.println("________________________");
            System.out.println("1) Create User-Account");
            System.out.println("2) Login User-Account");
            System.out.println("________________________");
            System.out.println("Enter choice:");
            choice = s.nextInt();

        return choice;
    }
    public static void createAccount() {
        String username;
        String password;
        try {
            Path p = Path.of(filename);
            String space = s.nextLine();
            System.out.print("Enter Username: ");
            username = s.nextLine();
            System.out.print("Enter Password: ");
            password = s.nextLine();
            Files.writeString(p, username + ";" + password + "\n", StandardOpenOption.APPEND);
            System.out.println("Account has been saved");

        } catch (Exception ex) {
            System.out.println("Account can't be saved");
        }
    }
    public static String loginAccount() {
        try {
            Path p = Path.of(filename);

            String space = s.nextLine();
            System.out.print("Enter Username: ");
            String username = s.nextLine();
            System.out.print("Enter Password: ");
            String password = s.nextLine();
            convertStringListTologinHashmap(Files.readAllLines(p));
            if (userAndPasswordOk(username,password) == "teacher"){
                return "teacher";
            }
            else if (userAndPasswordOk(username,password) == "student"){
                return "student";
            }
        } catch (Exception ex) {
            System.out.println("This User/Password does not exist");
        }
        return "something went wrong!";
    }
    public static void convertStringListTologinHashmap(List<String> list) {
        // 2) Splitten
        String[] data = new String[2];
        for (String s : list) {
            data = s.split(";");
            hashmapUser.put(data[0],data[1]);
        }
    }
    public static String userAndPasswordOk(String username, String password) throws SQLException {

        for (Map.Entry<String, String> entry : hashmapUser.entrySet()) {
            if (username.endsWith(".te") == true){
                if (entry.getKey().toLowerCase().equals(username.toLowerCase()) && entry.getValue().equals(password)){
                    return "teacher";
                }
            }

            else if (entry.getKey().toLowerCase().equals(username.toLowerCase()) && entry.getValue().equals(password)) {
                return "student";
            }
        }
        return null;
    }

    private static void studentfunction() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1) Search student by firstname");
        System.out.println("2) Search student by class");
        System.out.println("3) Search student by lastname");
        System.out.println("4) Close Programm");
        System.out.println("Enter choice:");
        studentchoice = s.nextInt();
    }
    public static void teacherfunction() throws SQLException, ClassNotFoundException {
        Scanner t = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1) Create a student account");
        System.out.println("2) Create a teachers account");
        System.out.println("3) Create a subject");
        System.out.println("4) Add subject to teacher");
        System.out.println("5) Search for student by Firstname");
        System.out.println("6) Search for student by Lastname");
        System.out.println("7) Search for student by Class");
        System.out.println("8) Get all teachers in a list");
        System.out.println("9) Get all subjects in a list");
        System.out.println("10) Close Program");
        System.out.println("Enter choice:");
        teacherchoice = t.nextInt();

        switch (teacherchoice){
            case 1:
                teachercreatestudent();
                break;
            case 2:
                teachercreateteacher();
                break;
            case 3:
                teachercreatesubject();
                break;
            case 4:
                teacheraddsubjecttoteacher();
                break;
            case 5:
                studentviewstudentbyfirstname();
                break;
            case 6:
                studentviewstudenbylastname();
                break;
            case 7:
                studentviewbyclass();
                break;
            case 8:
                allteachers();
                break;
            case 9:
                allsubjects();
                break;
            default:
                System.out.println("Your choice is not available");
                break;
        }
    }


    public static void teachercreatestudent() throws SQLException, ClassNotFoundException {
        IRepository_School repo;
        repo = new Repository_School();
        if (repo.createStudent(inputstudent())){
            System.out.println("You have made a new student Account");
        } else{
            System.out.println("There has been a mistake creating this Account");
        }
    }
    public static void teachercreateteacher () throws SQLException{
        if (repo.createTeacher(inputteacher())) {
            System.out.println("You have made a new teacher Account");
        } else {
            System.out.println("There has been a mistake creating this Account");
        }
    }
    public static void teachercreatesubject() throws  SQLException{
        if (repo.createSubject(inputsubject())){
            System.out.println("You have made a new subject");
        } else{
            System.out.println("There has been a mistake creating a new subject");
        }
    }
    public static void teacheraddsubjecttoteacher() throws SQLException{
        int teachername;
        int subject;
        System.out.println("Which teacher do you want to add a subject: [id only]");
        teachername = reader.nextInt();
        if (teachername >= 0 ) {
            List<Subject> subjects = new ArrayList<>();
            subjects = allsubjects();
            System.out.println("Id starts with 1:");
            for (Subject s : subjects){

                System.out.println(s);
            }
            System.out.println("Which subject do you want to add: [id only]");
            subject = reader.nextInt();
            if (repo.addSubjectToTeacherWhereID(subject, teachername) == true) {
                System.out.println("Subject has been added!");
            }
        }
        else {
            System.out.println("There has been a mistake adding a new subject!");
        }
    }
    public static List<Teacher> allteachers() throws SQLException {
        List<Teacher> foundTeacher = new ArrayList<>();
        foundTeacher = repo.getAllTeachers();
        for (Teacher c : foundTeacher){
            System.out.println(c);
        }
        return foundTeacher;
    }
    public static List<Subject> allsubjects() throws SQLException {
        List<Subject> foundSubject = new ArrayList<>();
        foundSubject = repo.getAllSubjects();
        for (Teacher c : foundTeacher){
            System.out.println(c);
        }
        return foundSubject;
    }


    public static void studentviewbyclass() throws SQLException {
        String csearch;
        System.out.println("Classroom to search: ");
        csearch = reader.next();

        foundStudent = repo.getStudentbyclassroom(csearch);
        if (foundStudent != null){
            System.out.println(foundStudent);
        }
        else {
            System.out.println("There is no classroom!");
        }

    }
    public static void studentviewstudenbylastname() throws SQLException {
        String lnToSearch;
        System.out.println("Studentlastname to search: ");
        lnToSearch = reader.next();

        foundStudent = repo.getStudentbylastname(lnToSearch);
        if (foundStudent != null){
            System.out.println(foundStudent);
        }
        else {
            System.out.println("There is no student with that lastname!");
        }
    }
    public static void studentviewstudentbyfirstname() throws SQLException {

        String fnToSearch;
        System.out.println("Studentname to search: ");
        fnToSearch = reader.next();


        foundStudent = repo.getStudentbyfirstname(fnToSearch);
        if (foundStudent != null){
            System.out.println(foundStudent);
        }
        else {
            System.out.println("There is no student with that name!");
        }
    }




    public static Teacher inputteacher() {

        Teacher t = new Teacher();
        System.out.println("Lastname:");
        t.setLastname(reader.nextLine());
        System.out.println("Firstname:");
        t.setFirstname(reader.nextLine());
        System.out.println("Birthdate:");
        int day, month, year;
        System.out.println("Birthdate-year:");
        year = reader.nextInt();
        System.out.println("Birthdate-month:");
        month = reader.nextInt();
        System.out.println("Birthdate-day:");
        day = reader.nextInt();
        t.setBirthdate(LocalDate.of(year,month,day));
        System.out.println("Gender: Female = 1/Male = 0");
        t.setGender(convertIntToGender(reader.nextInt()));
        String space = reader.nextLine();
        System.out.println("Email:");
        t.setMailaddress(reader.nextLine());
        System.out.println("Form teacher of:");
        t.setIsFormTeacher(reader.nextLine());

        return t;

    }
    public static Student inputstudent(){
        Student st = new Student();

        System.out.println("Firstname:");
        st.setFirstname(reader.nextLine());
        System.out.println("Lastname:");
        st.setLastname(reader.nextLine());
        System.out.println("Birthdate:");
        int day, month, year;
        System.out.println("Birthdate-year:");
        year = reader.nextInt();
        System.out.println("Birthdate-month:");
        month = reader.nextInt();
        System.out.println("Birthdate-day:");
        day = reader.nextInt();
        st.setBirthdate(LocalDate.of(year,month,day));
        System.out.println("Gender: Female = 1/Male = 0");
        st.setGender(convertIntToGender(reader.nextInt()));
        String space = reader.nextLine();
        System.out.println("Email:");
        st.setMailaddress(reader.nextLine());
        System.out.println("Classroom:");
        st.setClassroom(reader.nextLine());
        System.out.println("Studentclass:");
        st.setStudentClass(reader.nextLine());

        return st;
    }
    public static Subject inputsubject(){
        Subject s = new Subject();
        System.out.println("Subject:");
        s.setSubject(reader.nextLine());
        return s;
    }
    private static Gender convertIntToGender(int valueFromDB){
        if (valueFromDB == 0) {
            return Gender.male;
        } else if (valueFromDB == 1) {
            return Gender.female;
        } else {
            return Gender.notSpecified;
        }
    }




}


