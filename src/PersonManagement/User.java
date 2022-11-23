package PersonManagement;

import java.util.Scanner;

interface createAccount{
    void createUser();
    void username();
}

public class User extends Person implements createAccount{
    private String UserName;
    private String Password;
    public User(String Name, String PhoneNumber, String Address, String Gender, String AnswerToSecurityQuestion, String UserName, String Password){
        super(Name,PhoneNumber,Address,Gender,AnswerToSecurityQuestion);
        this.UserName = UserName;
        this.Password = Password;
    }
    public User(){
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

    public void createUser(){

        Scanner CreateUser = new Scanner(System.in);


        System.out.println("Enter password");
        String password = CreateUser.nextLine();
        password = password.trim();
        this.setPassword(password);

        System.out.println("Enter Your Name :");
        String Name = CreateUser.nextLine();
        Name = Name.trim();
        this.setName(Name);

        System.out.println("Choose Gender (M/F):");

        String Gender = CreateUser.nextLine();

        Gender  = Gender.trim();

        this.setGender(Gender);

        System.out.println("Enter Your Mobile Number :");
        String Mobile_Number = CreateUser.nextLine();
        Mobile_Number = Mobile_Number.trim();
        this.setPhoneNumber(Mobile_Number);

        System.out.println("Enter Your Address :");
        String Address = CreateUser.nextLine();
        Address = Address.trim();
        this.setAddress(Address);

        System.out.println("What was your First School Name(Security Question):");
        String SecurityQuestion = CreateUser.nextLine();
        SecurityQuestion = SecurityQuestion.trim();
        this.setAnswerOfSecurityQuestion(SecurityQuestion);

//        System.out.println("Enter userName");
//        String username = CreateUser.nextLine();
//        this.setUserName(username);
//

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
