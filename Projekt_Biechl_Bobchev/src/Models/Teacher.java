package Models;

import java.time.LocalDate;
import java.util.*;

public class Teacher extends Person{


    private String _isFormTeacher;
    private String _subjects;

    public String getIsFormTeacher() {
        return _isFormTeacher;
    }

    public void setIsFormTeacher(String formTeacher) {
        _isFormTeacher = formTeacher;
    }
    public Teacher(){
        this(0, " ", " ", LocalDate.MIN, " ", Gender.notSpecified, " ");
    }

    public Teacher(int id, String fn, String ln, LocalDate bd, String mA, Gender gr, String ft){
        super(id, fn, ln, bd, mA, gr);
        this.setIsFormTeacher(ft);

    }

    public String getSubjects() {
        return _subjects;
    }

    public void setSubjects(String _subjects) {
        this._subjects = _subjects;
    }

    @Override
    public String toString(){
        return super.toString() + "\n Klassenvorstand: " + this._isFormTeacher + " Fächer:" + this._subjects;
    }


}
