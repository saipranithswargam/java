import java.sql.Connection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SortedMap;

import PersonManagement.*;
import ProductManagement.product;

public class Main {
    public static <UserOperations> void main(String args[]) {
        employee db = new employee();
        Connection conn = db.connect_to_db("tutdb", "postgres", "1234");
        Scanner OptionScanner = new Scanner(System.in);
        while (true) {
            System.out.println("The following are operations available:-");
            System.out.println("1.User Operations");
            System.out.println("2.Admin Operations");
            System.out.println("3.Employee Operations");
            System.out.println("4.Exit From operation");
            String MainOptions = OptionScanner.nextLine();
            MainOptions = MainOptions.trim();
            if (MainOptions.equals("4")) {
                return;
            } else if (MainOptions.equals("1")) {
                boolean isLoggedIn = false;
                ArrayList<String> products = new ArrayList<String>();
                ArrayList<Integer> quantity = new ArrayList<Integer>();
                while (true) {
                    userOperations userdb = new userOperations();
                    Products productsdb = new Products();
                    System.out.println("The Following options are available for User:");
                    System.out.println("1.Create a User account");
                    System.out.println("2.Login into User account");
                    System.out.println("3.Update UserName");
                    System.out.println("4.Update Password for your account");
                    System.out.println("5.Forgot Password");
                    System.out.println("6.Add products to cart");
                    System.out.println("7.Checkout");
                    System.out.println("8.Exit From operation");
                    Scanner UserScanner = new Scanner(System.in);
                    String UserOptions = UserScanner.nextLine();
                    UserOptions = UserOptions.trim();
                    if (UserOptions.equals("8")) {
                        System.out.println("*********************Logged Out Automatically********************\n");
                        break;
                    } else if (UserOptions.equals("1")) {
                        User user = new User();
                        boolean checkFlag = true;
                        while(checkFlag){
                            user.username();
                            if(userdb.checkIfPresent(conn,user.getUserName()))
                            {

                                checkFlag = false;
                            }
                            else
                            {
                                System.out.println("user name already exists ...Try Again");
                            }
                        }
                        user.createUser();
                        userdb.insertIntoUser(conn,user.getName(),user.getPhoneNumber(),user.getAddress(),user.getGender(),user.getAnswerOfSecurityQuestion(),user.getUserName(),user.getPassword());
                    }  else if (UserOptions.equals("2")) {
                        Scanner userLoginScanner = new Scanner(System.in);
                        System.out.println("Enter Your username");
                        String Username = userLoginScanner.nextLine();
                        Username = Username.trim();
                        System.out.println("Enter Password");
                        String Password = userLoginScanner.nextLine();
                        Password = Password.trim();
                        userOperations.userLogin(conn,Username,Password);
                        isLoggedIn = true;
                    } else if (UserOptions.equals("3") && isLoggedIn) {
                        Scanner userUpdateScanner = new Scanner(System.in);
                        System.out.println("Enter your Old username");
                        String oldUserName = userUpdateScanner.nextLine();
                        oldUserName = oldUserName.trim();
                        System.out.println("Enter the new Username");
                        String newUserName = userUpdateScanner.nextLine();
                        newUserName = newUserName.trim();
                        boolean checkFlag = true;
                        while(checkFlag){
                            if(userdb.checkIfPresent(conn,newUserName))
                            {

                                checkFlag = false;
                                isLoggedIn = false;
                                userdb.updateUserName(conn,oldUserName,newUserName);
                                System.out.println("*********************Logged Out Automatically********************\n");
                            }
                            else
                            {
                                System.out.println("user name already exists ...Try Again");
                                System.out.println("Enter the new Username");
                                newUserName = userUpdateScanner.nextLine();
                                newUserName.trim();
                            }
                        }
                    } else if (UserOptions.equals("4")  && isLoggedIn) {
                        Scanner userUpdatePassScanner = new Scanner(System.in);
                        System.out.println("Enter your username");
                        String userName = userUpdatePassScanner.nextLine();
                        userName = userName.trim();
                        System.out.println("Enter the new Password");
                        String password = userUpdatePassScanner.nextLine();
                        password = password.trim();
                        userdb.updatePassword(conn,userName,password);
                        System.out.println("\n*********************Logged Out Automatically********************\n");
                        isLoggedIn = false;
                    } else if (UserOptions.equals("5")) {
                        Scanner forgotPasswordScanner = new Scanner(System.in);
                        System.out.println("Enter Your username");
                        String username = forgotPasswordScanner.nextLine();
                        username = username.trim();
                        while(true){
                        System.out.println("Enter answer for the security question (First school you studied):");
                        String answer = forgotPasswordScanner.nextLine();
                        answer = answer.trim();
                        if(userdb.forgotPassword(conn,username,answer)){
                            System.out.println("Enter new password for your account");
                            String newPassword = forgotPasswordScanner.nextLine();
                            newPassword = newPassword.trim();
                            userdb.updatePassword(conn,username,newPassword);
                            break;
                          }
                          else{
                              System.out.println("security answer provided is not correct try again !");
                          }
                        }

                    } else if (UserOptions.equals("6") && isLoggedIn) {

                        System.out.println("Options available are as follows:");
                        while (true) {
                            Scanner checkOutScanner = new Scanner(System.in);
                            System.out.println("1.search a product using name");
                            System.out.println("2.view all products available");
                            System.out.println("3.exit");
                            String userCheckoutOption = checkOutScanner.nextLine();
                            userCheckoutOption.trim();
                            if(userCheckoutOption.equals("3")) {
                                break;
                            } else if (userCheckoutOption.equals("1")) {
                                System.out.println("Give name of the product to be searched:");
                                Scanner productScanner = new Scanner(System.in);
                                String productname = productScanner.nextLine();
                                if(productsdb.checkIfPresent(conn,productname)){
                                    System.out.println("out of stock :(");
                                }else{
                                    productsdb.readAProduct(conn,productname);
                                    System.out.println("Enter 1 to add the product to cart");
                                    System.out.println("Enter 2 to get back to main menu");
                                    Scanner productBuyScanner = new Scanner(System.in);
                                    String productBuyStatus = productBuyScanner.nextLine();
                                    if(productBuyStatus.equals("1")){
                                        Scanner quantityScanner = new Scanner(System.in);
                                        System.out.println("Enter Product Quantity:");
                                        int productQuantity = quantityScanner.nextInt();
                                        System.out.println("Product has been added to your cart");
                                        products.add(productname);
                                        quantity.add(productQuantity);
                                    } else if (productBuyStatus.equals("2")) {
                                        continue;
                                    }else{
                                        System.out.println("Entered value is invalid!");
                                    }
                                }
                            } else if (userCheckoutOption.equals("2")) {
                                productsdb.readFromProductsTable(conn);
                            }
                            else{
                                System.out.println("Invalid option please enter one of the available options");
                            }
                        }

                    } else if (UserOptions.equals("7")) {
                        System.out.println("here are the products in your cart:");
                        int Price  = 0;
                        int total = 0;
                        for(int i=0;i<products.size();i++){
                            Price = productsdb.getPrice(conn,products.get(i));
                            Price*=quantity.get(i);
                            total+=Price;
                            System.out.println(products.get(i)+"\tQuantity:"+quantity.get(i)+"\tPrice:"+Price);
                            Price = 0;
                        }
                        System.out.println("Total Price to be paid:"+total);
                    }
                    if(!(isLoggedIn)){
                        System.out.println("\n************* Need Login to access these options *********\n");
                    }
                }
            } else if (MainOptions.equals("2")) {
                boolean  isLoggedIn =  false;
                adminTableOperations admindb = new adminTableOperations();
                userOperations userdb = new userOperations();
                while (true) {
                    System.out.println("The following are operations available:-");
                    System.out.println("1.Admin Login");
                    System.out.println("2.Employee Management");
                    System.out.println("3.View Supermarket Analysis");
                    System.out.println("4.Salary Management of Employee");
                    System.out.println("5.Customer/User Management");
                    System.out.println("6.Add new collaborator");
                    System.out.println("7.Exit From operation");
                    Scanner AdminScanner = new Scanner(System.in);
                    String AdminOption = AdminScanner.nextLine();
                    AdminOption = AdminOption.trim();
                    employee employeedb = new employee();
                    if (AdminOption.equals("7")) {
                        break;
                    } else if (AdminOption.equals("1")) {
                        Scanner adminLoginScanner = new Scanner(System.in);
                        System.out.println("Enter Your username");
                        String Username = adminLoginScanner.nextLine();
                        Username = Username.trim();
                        System.out.println("Enter Password");
                        String Password = adminLoginScanner.nextLine();
                        Password = Password.trim();
                        adminTableOperations.adminLogin(conn,Username,Password);
                        isLoggedIn = true;
                    } else if (AdminOption.equals("2") && isLoggedIn) {
                        while(true){
                            System.out.println("The following operations are available:-");
                            System.out.println("1.Add new Employee");
                            System.out.println("2.See Employee details based on username");
                            System.out.println("3.Get All Employees details");
                            System.out.println("4.Exit");
                            Scanner employeeManagement = new Scanner(System.in);
                            String employeeeManagementOption  = employeeManagement.nextLine();
                            employeeeManagementOption = employeeeManagementOption.trim();
                            if(employeeeManagementOption.equals("4")){
                                break;
                            } else if (employeeeManagementOption.equals("1")) {
//                                System.out.println("this is  to add a new employee");
                                Employee e1 = new Employee();
                                boolean checkFlag = true;
                                while(checkFlag){
                                    e1.username();
                                    if(employeedb.checkIfPresent(conn,e1.getUsername()))
                                    {

                                        checkFlag = false;
                                    }
                                    else
                                    {
                                        System.out.println("user name already exists ...Try Again");
                                    }
                                }
                                e1.createEmployee();
                                employeedb.insertIntoEmployee(conn,e1.getUsername(),e1.getPassword(),e1.getName(),e1.getPhoneNumber(),e1.getAddress(),e1.getGender(),e1.getAnswerOfSecurityQuestion(),e1.getSalary());
                            } else if (employeeeManagementOption.equals("2")) {
//                                System.out.println("this is to see employee details based on their username");
                                System.out.println("Enter username of employee whole details to be read:");
                                Scanner readSingleUser = new Scanner(System.in);
                                String username = readSingleUser.nextLine();
                                username = username.trim();
                                boolean checkFlag = true;
                                while(checkFlag){
                                    if(employeedb.readOneEmployee(conn,username))
                                    {

                                        checkFlag = false;
                                    }
                                    else
                                    {
                                        System.out.println("username entered doesnt exits");
                                    }
                                }

                            } else if (employeeeManagementOption.equals("3")) {
                                employeedb.readFromEmployeeTable(conn);
                            }else{
                                System.out.println("select a valid option");
                            }
                        }
                    } else if (AdminOption.equals("3") && isLoggedIn) {
                        System.out.println("Operation on Supermarket Management");
                    } else if (AdminOption.equals("4") && isLoggedIn) {
//                        System.out.println("Operation on salary Management of Employee");
                        System.out.println("Enter username of employee salary is to be managed:");
                        Scanner manageSalary = new Scanner(System.in);
                        String username = manageSalary.nextLine();
                        username = username.trim();
                        boolean checkFlag = true;
                        while(checkFlag){
                            if(employeedb.checkIfPresent(conn,username))
                            {

                                System.out.println("username entered doesn't exists");
                            }
                            else
                            {
                                System.out.println("Enter amount that salary is to be changed:");
                                Scanner salaryScanner = new Scanner(System.in);
                                double salary = 0;
                                try{
                                    salary = salaryScanner.nextDouble();
                                }catch (InputMismatchException e){
                                    System.out.println("the given input is invalid!");
                                    continue;
                                }
                                employeedb.updateSalary(conn,username,salary);
                                break;
                            }

                        }

                    }
                    else if (AdminOption.equals("5") && isLoggedIn) {
                        while(true) {
                            System.out.println("The following operations are available:-");
                            System.out.println("1.Read single user details");
                            System.out.println("2.Read all user details");
                            System.out.println("3.Exit");
                            Scanner userManagement = new Scanner(System.in);
                            String userManagementOption = userManagement.nextLine();
                            userManagementOption  = userManagementOption.trim();
                            if (userManagementOption.equals("3")) {
                                break;
                            } else if (userManagementOption.equals("1")) {

                                System.out.println("Enter username of user whose details to be read:");
                                Scanner readSingleUser = new Scanner(System.in);
                                String username = readSingleUser.nextLine();
                                username = username.trim();
                                boolean checkFlag = true;
                                while(checkFlag){
                                    if(userdb.readOneUser(conn,username))
                                    {
                                        checkFlag = false;
                                    }
                                    else
                                    {
                                        System.out.println("username entered doesnt exits");
                                    }
                                }

                            }
                            else if (userManagementOption.equals("2")) {
                                userdb.readFromUser(conn);
                            }else {
                                System.out.println("The entered value is invalid");
                            }
                        }
                    }
                    else if (AdminOption.equals("6")) {
                        System.out.println("Add new collaborator");
                        Admin admin = new Admin();
                        boolean checkFlag = true;
                        while(checkFlag){
                            admin.username();
                            if(admindb.checkIfPresent(conn,admin.getUserName()))
                            {
                                checkFlag = false;
                            }
                            else
                            {
                                System.out.println("user name already exists ...Try Again");
                            }
                        }
                        admin.createAdmin();
                        admindb.insertIntoAdmin(conn,admin.getName(),admin.getPhoneNumber(),admin.getAddress(),admin.getGender(),admin.getAnswerOfSecurityQuestion(),admin.getUserName(),admin.getPassword());
                    } else if (!isLoggedIn) {
                        System.out.println("*************************Login required to access these feilds*********************\n");
                    } else {
                        System.out.println("Please choose valid option");
                    }
                }
            } else if (MainOptions.equals("3")) {
                System.out.println("Here goes Employee operations");
                boolean isLoggedIn = false;
                Products productsdb = new Products();
                employee employeedb = new employee();
                while (true) {
                    Employee employee = new Employee();
                    System.out.println("The following are operations available:-");
                    System.out.println("1.Employee Login");
                    System.out.println("2.Bill Management");
                    System.out.println("3.Stock Management");
                    System.out.println("4.Exit From operation");
                    Scanner EmployeeScanner = new Scanner(System.in);
                    String EmployeeOption = EmployeeScanner.next();
                    EmployeeOption = EmployeeOption.trim();
                    if (EmployeeOption.equals("4")) {
                        break;
                    } else if (EmployeeOption.equals("2")&&isLoggedIn){
                        System.out.println("Operation on Bill management");
                    } else if (EmployeeOption.equals("3")&&isLoggedIn) {
                        System.out.println("Operation on Stock management");
                        while(true){
                            System.out.println("Options available area s follows:");
                            System.out.println("1.Add new Product");
                            System.out.println("2.Check details of all products");
                            System.out.println("3:Change price of a product");
                            System.out.println("4.Change product description of a product");
                            System.out.println("5:Add/change quantity of a product");
                            System.out.println("6:Exit");
                            Scanner StockManagement = new Scanner(System.in);
                            String StockManagenmentOption = StockManagement.nextLine();
                            StockManagenmentOption = StockManagenmentOption.trim();
                            if(StockManagenmentOption.equals("1")){
                                product NewProduct = new product();
                                boolean checkFlag = true;
                                while(checkFlag){
                                    NewProduct.checkProduct();
                                    if(productsdb.checkIfPresent(conn,NewProduct.getProductName()))
                                    {
                                        checkFlag = false;
                                        NewProduct.createProduct();
                                        productsdb.insertIntoProducts(conn,NewProduct.getProductName(),NewProduct.getQuantity(), NewProduct.getPrice(),NewProduct.getDescription());
                                    }
                                    else
                                    {
                                        System.out.println("Product already exist...You may increase the quantity instead!");
                                        break;
                                    }
                                }
                            } else if (StockManagenmentOption.equals("2")) {
                                productsdb.readFromProductsTable(conn);
                            } else if (StockManagenmentOption.equals("3")){
                                Scanner changePrice = new Scanner(System.in);
                                System.out.println("Enter the name of the product whose price is to be changed:");
                                String name = changePrice.nextLine();
                                if(productsdb.checkIfPresent(conn,name)){
                                    System.out.println("Entered product doesn't Exist...You may add a new product!");
                                }
                                else{
                                    System.out.println("Enter the new price of the product:");
                                    int newPrice = changePrice.nextInt();
                                    productsdb.updatePrice(conn,name,newPrice);
                                }
                            }
                            else if (StockManagenmentOption.equals("4")){
                                Scanner changeDescription = new Scanner(System.in);
                                System.out.println("Enter the name of the product whose description is to be changed:");
                                String name = changeDescription.nextLine();
                                if(productsdb.checkIfPresent(conn,name)){
                                    System.out.println("Entered product doesn't Exist...You may add a new product!");
                                }
                                else{
                                    System.out.println("Enter the new price of the product:");
                                    String newDescription = changeDescription.nextLine();
                                    productsdb.updateDescription(conn,name,newDescription);
                                }
                            }else if (StockManagenmentOption.equals("5")){
                                Scanner changeQuantity = new Scanner(System.in);
                                System.out.println("Enter the name of the product whose quantity is to be changed:");
                                String name = changeQuantity.nextLine();
                                if(productsdb.checkIfPresent(conn,name)){
                                    System.out.println("Entered product doesn't Exist...You may add a new product!");
                                }
                                else{
                                    System.out.println("Enter the new Quantity of the product:");
                                    int newQuantity = changeQuantity.nextInt();
                                    productsdb.updatePrice(conn,name,newQuantity);
                                }
                            } else if (StockManagenmentOption.equals("6")) {
                                break;
                            }
                        }
                    } else if (EmployeeOption.equals("1")) {
                        while(true) {
                            Scanner EmployeeLogin = new Scanner(System.in);
                            System.out.println("Enter username:");
                            String username = EmployeeLogin.nextLine();
                            username = username.trim();
                            System.out.println("Enter password:");
                            String password = EmployeeLogin.nextLine();
                            password = password.trim();
                            if (employeedb.employeeLogin(conn, username, password)) {
                                isLoggedIn = true;
                                break;
                            }
                        }
                    } else if (!isLoggedIn) {
                        System.out.println("Please login first to get access to this option");
                    } else {
                        System.out.println("Please choose valid option");
                    }
                }
            } else {
                System.out.println("*****************Please choose the valid Option**********************\n");
            }
        }

    }

}
