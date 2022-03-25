package Models;

import java.time.LocalDate;
import java.util.*;

public class Teacher extends Person{


    private String _isFormTeacher;
    private List<Subject> _subjects = new ArrayList<>();

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

    public boolean addSubject(Subject subject){
        if(subject != null){
            return this._subjects.add(subject);
        }
        return false;
    }
    public boolean removeSubject(int subjectToDelete){
        for(Subject s : this._subjects){
            if(s.getSubjectId() == subjectToDelete){
                this._subjects.remove(s);
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return super.toString() + "\nKlassenvorstand: " + this._isFormTeacher + " Fächer:" + this._subjects;
    }


}
