package Models;

public class Subject {

    private int subject_id;
    private String subject;

    public int getSubjectId() {
        return subject_id;
    }

    public void setSubjectId(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Subject(){
        this(0, " ");
    }

    public Subject(int id, String subject){
        this.setSubjectId(id);
        this.setSubject(subject);
    }
}
