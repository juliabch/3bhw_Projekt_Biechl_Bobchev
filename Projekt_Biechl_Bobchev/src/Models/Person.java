package Models;

import java.time.LocalDate;

public abstract class Person {

    private int _id;
    private String _firstname;
    private String _lastname;
    private LocalDate _birthdate;
    private Gender _gender;
    private String _mailAddress;

    public int getId(){
        return this._id;
    }
    public void setId(int id){
        if (id > 0 ){
            this._id = id;
        }
    }

    public String getFirstname(){
        return this._firstname;
    }
    public void setFirstname(String fn){
        this._firstname = fn;
    }

    public String getLastname(){
        return this._lastname;
    }
    public void setLastname(String ln){
        this._lastname = ln;
    }

    public LocalDate getBirthdate(){
        return this._birthdate;
    }
    public void setBirthdate(LocalDate bd){
        this._birthdate = bd;
    }

    public Gender getGender(){
        return this._gender;
    }
    public void setGender(Gender gr){
        this._gender = gr;
    }

    public String getMailAddress(){
        return this._mailAddress;
    }
    public void setMailaddress(String mA){
        this._mailAddress = mA;
    }

    public Person(){
        this(0, " ", " ", LocalDate.MIN, " ", Gender.notSpecified);
    }

    public Person(int id, String fn, String ln, LocalDate bd, String mA, Gender gr){
        this.setId(id);
        this.setFirstname(fn);
        this.setLastname(ln);
        this.setBirthdate(bd);
        this.setMailaddress(mA);
        this.setGender(gr);
    }

    @Override
    public String toString(){
        return this._id + " " + this._firstname + " " + this._lastname + "\n" +
                this._birthdate + " " + this._mailAddress + "\n" +
                this._gender;
    }


}
