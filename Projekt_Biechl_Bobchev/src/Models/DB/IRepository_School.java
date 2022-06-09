package Models.DB;
import Models.*;

import java.sql.SQLException;
import java.util.List;

public interface IRepository_School {

    void open() throws SQLException;

    void close() throws SQLException;

    boolean createTeacher(Teacher teacher) throws SQLException;

    boolean createStudent(Student student) throws SQLException;

    boolean createSubject(Subject subject) throws SQLException;

    boolean addSubjectToTeacherWhereID(int subject, int teacher) throws SQLException;

    List<Teacher> getAllTeachers() throws SQLException;

    List<Student> getStudentbyfirstname(String firstname) throws SQLException;

    List<Student> getStudentbyclassroom(String classroom) throws SQLException;

    List<Student> getStudentbylastname(String lastname) throws SQLException;




    List<Subject> getAllSubjects() throws SQLException;

    boolean getTeacherId(Teacher teacher) throws SQLException;

    boolean getSubjectId(Subject subject) throws SQLException;

}
