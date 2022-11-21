package PersonManagement;

import java.util.Scanner;

public class Admin extends Person {
    private String UserName;
    private String Password;
    public Admin(String name, String phoneNumber, String address, String gender, String answerOfSecurityQuestion, String userName, String password) {
        super(name, phoneNumber, address, gender, answerOfSecurityQuestion);
        UserName = userName;
        Password = password;
    }
    public Admin(){
        super("","","","","");
    }
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    public void createAdmin(){

        Scanner CreateAdmin = new Scanner(System.in);

        System.out.println("Enter password");
        String password = CreateAdmin.nextLine();
        password = password.trim();
        this.setPassword(password);

        System.out.println("Enter Your Name :");
        String Name = CreateAdmin.nextLine();
        Name = Name.trim();
        this.setName(Name);

        System.out.println("Choose Gender (M/F):");
        String Gender = CreateAdmin.nextLine();
        Gender = Gender.trim();
        this.setGender(Gender);

        System.out.println("Enter Your Mobile Number :");
        String Mobile_Number = CreateAdmin.nextLine();
        Mobile_Number = Mobile_Number.trim();
        this.setPhoneNumber(Mobile_Number);

        System.out.println("Enter Your Address :");
        String Address = CreateAdmin.nextLine();
        Address = Address.trim();
        this.setAddress(Address);

        System.out.println("What was your First School Name(Security Question):");
        String SecurityQuestion = CreateAdmin.nextLine();
        SecurityQuestion = SecurityQuestion.trim();
        this.setAnswerOfSecurityQuestion(SecurityQuestion);
    }
    public void username()
    {
        Scanner CreateUser = new Scanner(System.in);
        System.out.println("Enter userName");
        String username = CreateUser.nextLine();
        username = username.trim();
        this.setUserName(username);
    }
}
