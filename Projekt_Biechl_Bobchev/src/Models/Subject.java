package Models;

public class Subject {

    private int _subjectId;
    private String _subject;

    public int getSubjectId() {
        return _subjectId;
    }

    public void setSubjectId(int subject_id) {
        this._subjectId = subject_id;
    }

    public String getSubject() {
        return _subject;
    }

    public void setSubject(String _subject) {
        this._subject = _subject;
    }

    public Subject(){
        this(0, " ");
    }

    public Subject(int id, String subject){
        this.setSubjectId(id);
        this.setSubject(subject);
    }
}
