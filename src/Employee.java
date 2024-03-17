import javax.swing.JOptionPane;
import java.io.Serializable;
public class Employee implements Serializable{
    private int age;
    private String name;
    private String username;
    private  String password;
    public Employee(){
        this.age=18;
        this.name="";
        this.username="";
        this.password="";
        readEmployee();
    }
    public Employee(int age, String name, String username, String password){
        this.password=password;
        this.age=age;
        this.name=name;
        this.username=username;
    }
    private void readEmployee(){
        String input=JOptionPane.showInputDialog("What is the name");
        this.name=input;
        input=JOptionPane.showInputDialog("How old is this employee?");
        try{
            int nr=Integer.parseInt(input);
        }catch (NumberFormatException e){
            System.out.println("invalid");
        }
        input=JOptionPane.showInputDialog("Give an username for auth");
        this.username=input;
        input=JOptionPane.showInputDialog("Now set a password");
        this.password=input;
    }
    public void setUsername(String username){
        this.username=username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void clockIn(){
        System.out.println(name+" has clocked in for work");
    }
    public void clockOut(){
        System.out.println(name+" has clocked out for work");
    }
}
