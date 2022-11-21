package PersonManagement;
import java.util.Scanner;

public class Employee extends Person{
    private String Username;
    private String Password;
    private double Salary;

    public Employee(String name, String phoneNumber, String address, String gender, String answerOfSecurityQuestion, String username, String password, double salary) {
        super(name, phoneNumber, address, gender, answerOfSecurityQuestion);
        Username = username;
        Password = password;
        Salary = salary;
    }

    public Employee(){
        super("","","","","");
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public void createEmployee(){
        Scanner employeeManager = new Scanner(System.in);

        System.out.println("Enter your name");
        String name = employeeManager.nextLine();
        name = name.trim();
        this.setName(name);

        System.out.println("Enter password");
        String password = employeeManager.nextLine();
        password = password.trim();
        this.setPassword(password);

        System.out.println("Enter your phone number");
        String phno = employeeManager.nextLine();
        phno = phno.trim();
        this.setPhoneNumber(phno);

        System.out.println("Enter your address");
        String address = employeeManager.nextLine();
        address = address.trim();
        this.setAddress(address);

        System.out.println("Enter your gender");
        String gender = employeeManager.nextLine();
        gender = gender.trim();
        this.setGender(gender);

        System.out.println("What was your First School Name(Security Question):");
        String answer = employeeManager.nextLine();
        answer = answer.trim();
        this.setAnswerOfSecurityQuestion(answer);

        this.setSalary(0.0);

    }
    public void username()
    {
        Scanner CreateUser = new Scanner(System.in);
        System.out.println("Enter userName");
        String username = CreateUser.nextLine();
        username = username.trim();
        this.setUsername(username);
    }
}
