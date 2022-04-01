package Models.DB;
import Models.*;

import java.sql.*;


public class Repository_School implements IRepository_School{

    private String url = "jdbc:mysql://localhost/db_contacts";
    private String user = "root";
    private String pwd = "3p93EuV*";


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
        PreparedStatement pStmt =this._connection.prepareStatement("insert into teacher values(null, ?, ?, ?, ?, ?);");

        pStmt.setString(1, teacher.getLastname());
        pStmt.setString(2, teacher.getFirstname());
        pStmt.setDate(3, Date.valueOf(teacher.getBirthdate()));
        pStmt.setInt(4, teacher.getGender().ordinal());
        pStmt.setString(5, teacher.getIsFormTeacher());

        return pStmt.executeUpdate() == 1;
    }

    @Override
    public boolean createStudent() throws SQLException {
        return false;
    }

    @Override
    public boolean createSubject() throws SQLException {
        return false;
    }

    @Override
    public boolean insertSubject() throws SQLException {
        return false;
    }


}