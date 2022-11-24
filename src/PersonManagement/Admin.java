package PersonManagement;

import java.util.Scanner;

public class Admin extends Person{
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
    public static Admin createAccount(Admin admin){

        Scanner CreateAdmin = new Scanner(System.in);

        System.out.println("Enter password");
        String password = CreateAdmin.nextLine();
        password = password.trim();
        admin.setPassword(password);

        System.out.println("Enter Your Name :");
        String Name = CreateAdmin.nextLine();
        Name = Name.trim();
        admin.setName(Name);

        System.out.println("Choose Gender (M/F):");
        String Gender = CreateAdmin.nextLine();
        Gender = Gender.trim();
        admin.setGender(Gender);

        System.out.println("Enter Your Mobile Number :");
        String Mobile_Number = CreateAdmin.nextLine();
        Mobile_Number = Mobile_Number.trim();
        admin.setPhoneNumber(Mobile_Number);

        System.out.println("Enter Your Address :");
        String Address = CreateAdmin.nextLine();
        Address = Address.trim();
        admin.setAddress(Address);

        System.out.println("What was your First School Name(Security Question):");
        String SecurityQuestion = CreateAdmin.nextLine();
        SecurityQuestion = SecurityQuestion.trim();
        admin.setAnswerOfSecurityQuestion(SecurityQuestion);

        return admin;
    }
    public static Admin username(Admin admin)
    {
        Scanner CreateUser = new Scanner(System.in);
        System.out.println("Enter userName");
        String username = CreateUser.nextLine();
        username = username.trim();
        admin.setUserName(username);
        return admin;
    }
    public static Employee createAccount(Employee e1){
        Scanner employeeManager = new Scanner(System.in);

        System.out.println("Enter name");
        String name = employeeManager.nextLine();
        name = name.trim();
        e1.setName(name);

        System.out.println("Enter password");
        String password = employeeManager.nextLine();
        password = password.trim();
        e1.setPassword(password);

        System.out.println("Enter phone number");
        String phno = employeeManager.nextLine();
        phno = phno.trim();
        e1.setPhoneNumber(phno);

        System.out.println("Enter address");
        String address = employeeManager.nextLine();
        address = address.trim();
        e1.setAddress(address);

        System.out.println("Enter gender");
        String gender = employeeManager.nextLine();
        gender = gender.trim();
        e1.setGender(gender);

        System.out.println("What was your First School Name(Security Question):");
        String answer = employeeManager.nextLine();
        answer = answer.trim();
        e1.setAnswerOfSecurityQuestion(answer);

        e1.setSalary(0.0);

        return e1;
    }
    public static Employee username(Employee e1)
    {
        Scanner CreateUser = new Scanner(System.in);
        System.out.println("Enter userName");
        String username = CreateUser.nextLine();
        username = username.trim();
        e1.setUsername(username);
        return e1;
    }
}
