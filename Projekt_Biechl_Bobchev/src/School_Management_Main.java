import Models.*;
import Models.DB.*;

import java.nio.file.Files;
import java.nio.file.Path;
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

    //Scanner für KLassen
    static Scanner reader = new Scanner(System.in);
    //Für Filemanagment
    static Scanner s = new Scanner(System.in);

    //Eva: String filename = "C://Daten//SWP-Projekt//Register.txt";
    //Julia:
    static String filename = "C://Users//biech//Desktop//3bhwii_Java//3bhw_Projekt_Biechl_Bobchev//User.txt";

    static IRepository_School repo;

    static List<Student> foundStudent = new ArrayList<Student>();

    public static void main(String[] args) {
        IRepository_School rep = null;

        try {
            rep = new Repository_School();
            rep.open();
            userAccount();
            if (loginAccount() == "teacher"){
                teacherfunction();
            }
            else if (loginAccount() == "student"){
                studentfunction();
            }
            else {
                System.out.println("Closing Programm");
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void userAccount() {
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
            } else if (choice.equals("2")) {
                newAccount.loginAccount();
            } else {
                System.out.println("This option is not available right now!");
            }
        } catch (Exception ex) {
        }

    }
    public static void createAccount() {
        String username;
        String password;
        try {
            Path p = Path.of(filename);

            System.out.print("Enter Username:");
            username = s.nextLine();
            String newusername;
            System.out.println(username);
            newusername = username;
            System.out.println(newusername);
            if (newusername.contains("teacher")){
                newusername += ".te";
                System.out.println(newusername);
            }

            System.out.print("Enter Password:");
            password = s.nextLine();
            Files.writeString(p, newusername + ";" + password + "\n", StandardOpenOption.APPEND);
            System.out.println("Account has been saved");

        } catch (Exception ex) {
            System.out.println("Account can't be saved");
        }
    }
    public static String loginAccount() {
        try {
            Path p = Path.of(filename);
            // 1) Datei mit userdaten lesen und In einer Hashmap abspeichern
            System.out.print("Enter Username:");
            String username = s.nextLine();
            System.out.print("Enter Password:");
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
    public static String userAndPasswordOk(String username, String password) throws SQLException, ClassNotFoundException {
        String newusername;
        if (username.contains(".te")){
            newusername = username;
        }
        else{
            newusername = null;
        }
        for (Map.Entry<String, String> entry : hashmapUser.entrySet()) {
            if (entry.getKey().toLowerCase().equals(newusername.toLowerCase()) && entry.getValue().equals(password)){
                System.out.println("Hello teacher");
                teacherfunction();
                return "teacher";
            }

            if (entry.getKey().toLowerCase().equals(username.toLowerCase()) && entry.getValue().equals(password)) {
                System.out.println("Hello student");
                studentfunction();
                return "student";
            }
        }
        return null;
    }

    private static void studentfunction() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1) View student account by firstname");
        System.out.println("2) View student by class");
        System.out.println("3) View student account by lastname");
        System.out.println("Enter choice:");
        int studentchoice = s.nextInt();

        switch (studentchoice){
            case 1:
                studentviewstudentbyfirstname();
                break;
            case 2:
                studentviewbyclass();
                break;
            case 3:
                studentviewstudenbylastname();
            default:
                System.out.println("Your choice is not available");
                break;
        }
    }
    public static void teacherfunction() throws SQLException, ClassNotFoundException {
        Scanner t = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1) Create a student account");
        System.out.println("2) Create a teachers account");
        System.out.println("3) Create a subject");
        System.out.println("4) Add subject to teacher");
        System.out.println("Enter choice:");
        int teacherchoice = t.nextInt();

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
            System.out.println("Which subject do you want to add: [id only]");
            subject = reader.nextInt();
            if (repo.addSubjectToTeacherWhereID(subject, teachername) == true) {
                System.out.println("Subject has been added!");
            }
            System.out.println(subject);
        }
        else {
            System.out.println("There has been a mistake adding a new subject!");
        }
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
        t.setId(reader.nextInt());
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
        System.out.println("Gender: Female/Male");
        t.setGender(convertIntToGender(reader.nextInt()));
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
        System.out.println("Gender: Female/Male");
        st.setGender(convertIntToGender(reader.nextInt()));
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


