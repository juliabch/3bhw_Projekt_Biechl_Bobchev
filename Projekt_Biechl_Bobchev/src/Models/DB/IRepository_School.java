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

    boolean addSubject(Subject subject) throws SQLException;

}
