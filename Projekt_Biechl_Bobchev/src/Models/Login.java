package Models;

import Models.DB.IRepository_School;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Login {
    List<Student> foundStudent = new ArrayList<Student>();

    //Scanner für KLassen
    public Scanner reader = new Scanner(System.in);

    // Für Datenbank
    IRepository_School rep = null;

    //Für Filemanagment
    Scanner s = new Scanner(System.in);
    String filename = "C://Daten//SWP-Projekt//Register.txt";


    private HashMap<String,String> hashmapUser = new HashMap<>();
    private String _username;
    private String _password;
    public String getusername(){ return this._username; }
    public void setusername(String username){
        this._username = username;
    }
    public String getpassword(){ return this._password; }
    public void setpassword(String password){
        this._password = password;
    }
    public Login() {
        this("", "");
    }
    public Login(String username, String password){
        this.setusername(username);
        this.setpassword(password);
    }

    public void createAccount() {
        try {
            Path p = Path.of(filename);

            System.out.print("Enter Username:");
            String username = s.nextLine();
            System.out.print("Enter Password:");
            String password = s.nextLine();
            Files.writeString(p, username + ";" + password + "\n", StandardOpenOption.APPEND);
            System.out.println("Account has been saved");
            // new UserAccount();
        } catch (Exception ex) {
            System.out.println("Account cant be saved");
        }
    }

    public boolean loginAccount() {
        try {
            Path p = Path.of(filename);
            // 1) Datei mit userdaten lesen und In einer Hashmap abspeichern
            System.out.print("Enter Username:");
            String username = s.nextLine();
            System.out.print("Enter Password:");
            String password = s.nextLine();
             convertStringListTologinHashmap(Files.readAllLines(p));
            userAndPasswordOk(username,password);
        } catch (Exception ex) {
            System.out.println("This User/Password does not exist");
        }
        return false;
    }

    public void convertStringListTologinHashmap(List<String> list) {
        // 2) Splitten
        String[] data = new String[2];
        for (String s : list) {
            data = s.split(";");
            hashmapUser.put(data[0],data[1]);
        }
    }
    public void userAndPasswordOk(String username, String password) throws SQLException {
        for (Map.Entry<String, String> entry : hashmapUser.entrySet()) {
            if (entry.getKey().toLowerCase().equals(username+".te".toLowerCase()) && entry.getValue().equals(password)){
                System.out.println("Hello teacher");
                teacherfunction();
                return;
            }

            if (entry.getKey().toLowerCase().equals(username.toLowerCase()) && entry.getValue().equals(password)) {
                System.out.println("Hello student");
                studentfunction();
                return;
            }
        }
    }

    private void studentfunction() throws SQLException {
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

    public void teacherfunction() throws SQLException {
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

    public void teachercreatestudent()throws SQLException{
        if (rep.createStudent(inputstudent())){
            System.out.println("You have made a new student Account");
        } else{
            System.out.println("There has been a mistake creating this Account");
        }
    }
    public void teachercreateteacher () throws SQLException{
        if (rep.createTeacher(inputteacher())) {
            System.out.println("You have made a new teacher Account");
        } else {
            System.out.println("There has been a mistake creating this Account");
        }
    }
    public void teachercreatesubject() throws  SQLException{
        if (rep.createSubject(inputsubject())){
            System.out.println("You have made a new subject");
        } else{
            System.out.println("There has been a mistake creating a new subject");
        }
    }
    public void teacheraddsubjecttoteacher() throws SQLException{
        String teachername;
        Subject newsubject;
        System.out.println("Which teacher do you want to add a subject: ");
        teachername = reader.nextLine();
        if (teachername != null ) {
            System.out.println("Subject to add: ");
            newsubject = inputsubject();
            if (rep.addSubject(newsubject)) {
                System.out.println("Subject has been added!");
            }
            System.out.println(newsubject);
        }
        else {
            System.out.println("There has been a mistake adding a new subject!");
        }
    }


    public void studentviewbyclass() throws SQLException {
        String csearch;
        System.out.println("Classroom to search: ");
        csearch = reader.next();

        foundStudent = rep.getStudentbyclassroom(csearch);
        if (foundStudent != null){
            System.out.println(foundStudent);
        }
        else {
            System.out.println("There is no classroom!");
        }

    }
    public void studentviewstudenbylastname() throws SQLException {
        String lnToSearch;
        System.out.println("Studentlastname to search: ");
        lnToSearch = reader.next();

        foundStudent = rep.getStudentbylastname(lnToSearch);
        if (foundStudent != null){
            System.out.println(foundStudent);
        }
        else {
            System.out.println("There is no student with that lastname!");
        }
    }
    public void studentviewstudentbyfirstname() throws SQLException {

        String fnToSearch;
        System.out.println("Studentname to search: ");
        fnToSearch = reader.next();

        foundStudent = rep.getStudentbyfirstname(fnToSearch);
        if (foundStudent != null){
            System.out.println(foundStudent);
        }
        else {
            System.out.println("There is no student with that name!");
        }
    }




    public Teacher inputteacher() {

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
        t.setGender(convertGender(reader.nextInt()));
        System.out.println("Email:");
        t.setMailaddress(reader.nextLine());
        System.out.println("Form teacher of:");
        t.setIsFormTeacher(reader.nextLine());

        return t;

    }
    public Student inputstudent(){
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
        st.setGender(convertGender(reader.nextInt()));
        System.out.println("Email:");
        st.setMailaddress(reader.nextLine());
        System.out.println("Classroom:");
        st.setClassroom(reader.nextLine());
        System.out.println("Studentclass:");
        st.setStudentClass(reader.nextLine());

        return st;

    }
    public Subject inputsubject(){
        Subject s = new Subject();
        System.out.println("Subject:");
        s.setSubject(reader.nextLine());
        return s;
    }



    private Gender convertGender(int value){
        if(value == 0){
            return Gender.male;
        }
        else if(value == 1 ){
            return Gender.female;
        }
        else{
            return Gender.notSpecified;
        }
    }





}


