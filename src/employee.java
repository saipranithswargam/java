import java.sql.*;
import java.sql.Connection;
public class employee{
    public Connection connect_to_db(String dbname, String user, String pass) {

        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void insertIntoEmployee(Connection conn,String username, String password, String name, String phone, String address, String gender, String secQuestion,double salary){
        Statement statement;
        try{
            String query=String.format("insert into employee values('%s','%s','%s','%s','%s','%s','%s','%f');",username, password,name,phone,address,gender,secQuestion,salary);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void  readFromEmployeeTable(Connection conn){
        Statement statement;
        ResultSet rs = null;
        try{
            String Query=String.format("select * from employee");
            statement= conn.createStatement();;

            rs=statement.executeQuery(Query);//here this means it will execute the statement and return it to rs
            //and result is iterable
                int count = 1;
                while(rs.next()){//cheking if it has next value
                    System.out.println("Employee"+count);
               System.out.println("username:"+rs.getString("username"));
               System.out.println("name:"+rs.getString("name"));
              System.out.println("Phone number:"+rs.getString("phone"));
                System.out.println("address:"+rs.getString("address"));
               System.out.println("Gender:"+rs.getString("gender"));
               System.out.println("Gender:"+rs.getString("salary"));
               String res = rs.getString("username");
               System.out.println();
               count++;
                if(res==null){
                   System.out.println("this is null");
                }
          }
                count = 0;

        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void updateUserName(Connection conn,String oldUserName, String newUserName)
    {
        Statement statement;

        try{

            String query = String.format("update employee set username='%s' where username='%s'",newUserName,oldUserName);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("UserName updated!");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void updatePhone(Connection conn,String username, String newPhone)
    {
        Statement statement;

        try{

            String query = String.format("update employee set phone='%s' where username='%s'",newPhone,username);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("User PhoneNo updated!");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public void updatePassword(Connection conn,String username,String newPassword)
    {
        Statement statement;

        try{

            String query = String.format("update employee set password='%s' where username='%s'",newPassword,username);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Password updated Successfully!");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void updateAddress(Connection conn,String username,String newAddress)
    {
        Statement statement;

        try{

            String query = String.format("update employee set address='%s' where username='%s'",newAddress,username);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Address Updated Successfully!");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public void updateSalary(Connection conn,String username,double newSalary)
    {
        Statement statement;

        try{

            String query = String.format("update employee set salary='%f' where username='%s'",newSalary,username);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Salary Updated Successfully!");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public void deleteRowInEmployee(Connection conn, String username)
    {
        Statement statement;

        try{
            String query = String.format("DELETE from employee where username = '%s'",username);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Deleted Successfully!");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public boolean  checkIfPresent(Connection conn, String username){
        Statement statement;
        ResultSet rs = null;
        try{
            String Query=String.format("select * from employee where username = '%s'",username);
            statement= conn.createStatement();;

            rs=statement.executeQuery(Query);

            if(!rs.isBeforeFirst())
            {
                System.out.println("We can insert");
                return true;
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    public boolean readOneEmployee(Connection conn,String username){
        Statement statement;
        ResultSet rs = null;
        try{
            String Query=String.format("select * from employee where username = '%s'",username);
            statement= conn.createStatement();;
            rs=statement.executeQuery(Query);
            if(!rs.isBeforeFirst())
            {

                return false;
            }
            else {
                while (rs.next()) {
                    System.out.println("name:"+rs.getString("name"));
                    System.out.println("phoneNumber:"+rs.getString("phone"));
                    System.out.println("address:" +rs.getString("address"));
                    System.out.println("gender:"+rs.getString("gender"));
                    System.out.println("salary:"+rs.getString("salary"));
                }
                return true;
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean employeeLogin(Connection conn, String username, String password)

    {
        Statement statement;
        ResultSet rs;

        try{

            String Query = String.format("select password from employee where username = '%s'",username);

            statement=conn.createStatement();
            rs = statement.executeQuery(Query);
            if(!rs.isBeforeFirst())
            {
                System.out.println("invalid username");
                return false;
            }
            else{
                rs.next();
                String originalPassword = rs.getString("password");
                if(originalPassword.equals(password))
                {
                    System.out.println("Logged in Successfully!");
                    return true;

                }
                else
                {
                    System.out.println("Invalid password");
                    return false;
                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
}

