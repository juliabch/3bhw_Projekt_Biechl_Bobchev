package Models.DB;
import Models.*;

import java.sql.SQLException;
import java.util.List;

public interface IRepository_School {

    void open() throws SQLException;

    void close() throws SQLException;

    boolean createTeacher() throws SQLException;

    boolean createStudent() throws SQLException;

    boolean createSubject() throws SQLException;

    boolean insertSubject() throws SQLException;

}
