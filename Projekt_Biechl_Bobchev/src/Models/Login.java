package Models;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Login {
    private HashMap<String,String> hashmapUser = new HashMap<>();
    private String _username;
    private String _password;
    public String getusername(){ return this._username; }
    public void setusername(String username){
        this._username = username;
    }
    public String getpassword(){ return this._password; }
    public void setpassword(String password){
        this._password = password;
    }
    public Login() {
        this("", "");
    }
    public Login(String username, String password){
        this.setusername(username);
        this.setpassword(password);
    }


    Scanner s = new Scanner(System.in);
    String filename = "C://Daten//SWP-Projekt//Register.txt";

    public void createAccount() {
        try {
            Path p = Path.of(filename);

            System.out.print("Enter Username:");
            String username = s.nextLine();
            System.out.print("Enter Password:");
            String password = s.nextLine();
            Files.writeString(p, username + ";" + password + "\n", StandardOpenOption.APPEND);
            System.out.println("Account has been saved");
            // new UserAccount();
        } catch (Exception ex) {
            System.out.println("Account cant be saved");
        }
    }

    public boolean loginAccount() {
        try {
            Path p = Path.of(filename);
            // 1) Datei mit userdaten lesen und In einer Hashmap abspeichern
            System.out.print("Enter Username:");
            String username = s.nextLine();
            System.out.print("Enter Password:");
            String password = s.nextLine();
             convertStringListTologinHashmap(Files.readAllLines(p));
            userAndPasswordOk(username,password);
        } catch (Exception ex) {
            System.out.println("This User/Password does not exist");
        }
        return false;
    }

    public void convertStringListTologinHashmap(List<String> list) {

        // 2) Splitten
        String[] data = new String[2];
        for (String s : list) {
            data = s.split(";");

            hashmapUser.put(data[0],data[1]);

        }
    }
    private void userAndPasswordOk(String username, String password) {
        for (Map.Entry<String, String> entry : hashmapUser.entrySet()) {
            if (entry.getKey().toLowerCase().equals(username.toLowerCase()) && entry.getValue().equals(password)) {
                System.out.println("You have logged in");
            }

        }
    }

    }


