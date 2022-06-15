package Models.DB;
import Models.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class Repository_School implements IRepository_School{

    private String url = "jdbc:mysql://localhost/school_management";
    private String user = "root";
    private String pwd = "3p93EuV*";

    Scanner reader = new Scanner(System.in);

    private Connection _connection;


    public Repository_School() throws ClassNotFoundException {
        Class<?> c = Class.forName("com.mysql.cj.jdbc.Driver");
        if (c != null){
            System.out.println("Der MySql-Treiber wurde geladen!");
        }
    }

    @Override
    public void open() throws SQLException {
        this._connection = DriverManager.getConnection(url, user, pwd);
    }

    @Override
    public void close() throws SQLException {
        if ((this._connection != null) && (!this._connection.isClosed())){
            this._connection.close();
            System.out.println("Verbindung wurde geschlossen");
        }
    }

    @Override
    public boolean createTeacher(Teacher teacher) throws SQLException {
        PreparedStatement pStmt = this._connection.prepareStatement("insert into teacher values(null, ?, ?, ?, ?, ?, ?);");

        pStmt.setString(1, teacher.getLastname());
        pStmt.setString(2, teacher.getFirstname());
        pStmt.setDate(3, Date.valueOf(teacher.getBirthdate()));
        pStmt.setInt(4, teacher.getGender().ordinal());
        pStmt.setString(5, teacher.getMailAddress());
        pStmt.setString(6, teacher.getIsFormTeacher());

        boolean insertOk = pStmt.executeUpdate() == 1;

        boolean idOK = false;

        if(insertOk == true){
            if(getTeacherId(teacher)){
                idOK = true;
            }
        }

        return idOK;
    }

    @Override
    public boolean createStudent(Student student) throws SQLException {
        PreparedStatement pStmt = this._connection.prepareStatement("insert into student value(null, ?, ?, ?, ?, ?, ?, ?);");

        pStmt.setString(1, student.getLastname());
        pStmt.setString(2, student.getFirstname());
        pStmt.setDate(3, Date.valueOf(student.getBirthdate()));
        pStmt.setInt(4, student.getGender().ordinal());
        pStmt.setString(5, student.getMailAddress());
        pStmt.setString(6, student.getClassroom());
        pStmt.setString(7, student.getStudentClass());

        return pStmt.executeUpdate() == 1;
    }

    @Override
    public boolean createSubject(Subject subject) throws SQLException {
        PreparedStatement pStmt =this._connection.prepareStatement("insert into subjects values(null, ?);");

        pStmt.setString(1, subject.getSubject());

        boolean insertOk = pStmt.executeUpdate() == 1;

        boolean idOK = false;

        if(insertOk == true){
            if(getSubjectId(subject)){
                idOK = true;
            }
        }

        return idOK;
    }

    @Override
    public boolean addSubjectToTeacherWhereID(int subject, int teacher) throws SQLException {
        PreparedStatement pStmt =this._connection.prepareStatement("insert into teacher_subjects values(null, ?, ?);");

        pStmt.setInt(1, subject);
        pStmt.setInt(2, teacher);

        return pStmt.executeUpdate() == 1;
    }


    @Override
    public List<Student> getStudentbyfirstname(String firstname) throws SQLException {
        List<Student> foundStudent = new ArrayList<>();
        PreparedStatement pStmt = this._connection.prepareStatement("select * from student where firstname LIKE firstname = ?");

        pStmt.setString(1, firstname);

        ResultSet result = pStmt.executeQuery();

        Student s = new Student();

        while (result.next()) {
            s = new Student();
            s.setFirstname(result.getString("firstname"));
            s.setLastname(result.getString("lastname"));
            s.setBirthdate(result.getDate("birthdate").toLocalDate());
            s.setGender(convertIntToGender(result.getInt("gender")));
            s.setClassroom(result.getString("classroom"));
            s.setStudentClass(result.getString("studentclass"));
            s.setMailaddress(result.getString("mail_Address"));

            foundStudent.add(s);
        }
        if (foundStudent.size() >= 1) {
            return foundStudent;
        } else {
            return null;
        }
    }
    public List<Teacher> getAllTeachers () throws SQLException {
                List<Teacher> foundTeacher = new ArrayList<Teacher>();
                PreparedStatement pStmt = this._connection.prepareStatement("select * from teacher;");

                ResultSet result = pStmt.executeQuery();

                Teacher t;
                while (result.next()) {
                    t = new Teacher();
                    t.setId(result.getInt("teacherId"));
                    t.setLastname(result.getString("l_name"));
                    t.setFirstname(result.getString("f_name"));
                    t.setBirthdate(result.getDate("bdate").toLocalDate());
                    t.setGender(convertIntToGender(result.getInt("gender")));
                    t.setIsFormTeacher(result.getString("formTeacher"));

                    foundTeacher.add(t);
                }

                if (foundTeacher.size() >= 1) {
                    return foundTeacher;
                } else {
                    return null;
                }
            }

            @Override
            public List<Student> getStudentbyclassroom (String classroom) throws SQLException {
                List<Student> foundStudent = new ArrayList<>();
                PreparedStatement pStmt = this._connection.prepareStatement("select * from student where classroom LIKE classroom = ?");

                pStmt.setString(1, classroom);

                ResultSet result = pStmt.executeQuery();

                Student s = new Student();

                while (result.next()) {
                    s = new Student();
                    s.setFirstname(result.getString("firstname"));
                    s.setLastname(result.getString("lastname"));
                    s.setBirthdate(result.getDate("birthdate").toLocalDate());
                    s.setGender(convertIntToGender(result.getInt("gender")));
                    s.setClassroom(result.getString("classroom"));
                    s.setStudentClass(result.getString("studentclass"));
                    s.setMailaddress(result.getString("mail_Address"));

                    foundStudent.add(s);
                }
                if (foundStudent.size() >= 1) {
                    return foundStudent;
                } else {
                    return null;
                }

            }

            @Override
            public List<Student> getStudentbylastname (String lastname) throws SQLException {
                List<Student> foundStudent = new ArrayList<>();
                PreparedStatement pStmt = this._connection.prepareStatement("select * from student where lastname LIKE lastname = ?");

                pStmt.setString(1, lastname);

                ResultSet result = pStmt.executeQuery();

                Student s = new Student();

                while (result.next()) {
                    s = new Student();
                    s.setFirstname(result.getString("firstname"));
                    s.setLastname(result.getString("lastname"));
                    s.setBirthdate(result.getDate("birthdate").toLocalDate());
                    s.setGender(convertIntToGender(result.getInt("gender")));
                    s.setClassroom(result.getString("classroom"));
                    s.setStudentClass(result.getString("studentclass"));
                    s.setMailaddress(result.getString("mail_Address"));

                    foundStudent.add(s);
                }
                if (foundStudent.size() >= 1) {
                    return foundStudent;
                } else {
                    return null;
                }
            }

            @Override
            public List<Subject> getAllSubjects () throws SQLException {
                List<Subject> foundSubject = new ArrayList<Subject>();
                PreparedStatement pStmt = this._connection.prepareStatement("select * from subjects;");

                ResultSet result = pStmt.executeQuery();

                Subject s;
                while (result.next()) {
                    s = new Subject();
                    s.setSubjectId(result.getInt("subjectId"));
                    s.setSubject(result.getString("subject_name"));

                    foundSubject.add(s);
                }

                if (foundSubject.size() >= 1) {
                    return foundSubject;
                } else {
                    return null;
                }
            }

            @Override
            public boolean getTeacherId (Teacher teacher1) throws SQLException {
                PreparedStatement pStmt = this._connection.prepareStatement("select teacherId from teacher where l_name like ? " +
                        "and f_name like ? and mailAddress like ?;");
                pStmt.setString(1, teacher1.getLastname());
                pStmt.setString(2, teacher1.getFirstname());
                pStmt.setString(3, teacher1.getMailAddress());

                ResultSet result = pStmt.executeQuery();

                if (result.next()) {
                    teacher1.setId(result.getInt("teacherId"));
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public boolean getSubjectId (Subject subject) throws SQLException {
                PreparedStatement pStmt = this._connection.prepareStatement("select subjectId from subjects where subject_name like ?");
                pStmt.setString(1, subject.getSubject());

                ResultSet result = pStmt.executeQuery();

                if (result.next()) {
                    subject.setSubjectId(result.getInt("subjectId"));
                    return true;
                } else {
                    return false;
                }
            }


            private Gender convertIntToGender( int valueFromDB){
                if (valueFromDB == 0) {
                    return Gender.male;
                } else if (valueFromDB == 1) {
                    return Gender.female;
                } else {
                    return Gender.notSpecified;
                }
            }

}