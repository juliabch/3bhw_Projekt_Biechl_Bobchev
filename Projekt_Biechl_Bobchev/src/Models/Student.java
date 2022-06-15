package Models;

import java.time.LocalDate;

public class Student extends Person {

    private String _studentClass;
    private String _classroom;

    public String getStudentClass() {
        return _studentClass;
    }

    public void setStudentClass(String _studentClass) {
        this._studentClass = _studentClass;
    }

    public String getClassroom() {
        return _classroom;
    }

    public void setClassroom(String _classroom) {
        this._classroom = _classroom;
    }


    public Student(){
        this(0, " ", " ", LocalDate.MIN, " ", Gender.notSpecified, " ", " ");
    }

    public Student(int id, String fn, String ln, LocalDate bd, String mA, Gender gr, String studentClass, String classroom){
        super(id, fn, ln, bd, mA, gr);
        this.setStudentClass(studentClass);
        this.setClassroom(classroom);

    }

    @Override
    public String toString(){
        return super.toString() + "\nClass: " + this._studentClass + " Classroom: " + this._classroom;
    }


}
