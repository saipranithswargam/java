import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class adminTableOperations {
    public void insertIntoAdmin(Connection conn,String name, String phoneNumber, String address, String gender, String answerToSecurityQuestion, String username, String password){
        Statement statement;
        try{
            String query=String.format("insert into admin(name, phonenumber, address, gender, answertosecurityquestion, username, password) values('%s','%s','%s','%s','%s','%s','%s');",name,phoneNumber,address,gender,answerToSecurityQuestion,username,password);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void  readFromUser(Connection conn){
        Statement statement;
        ResultSet rs = null;
        try{
            String Query=String.format("select * from admin");
            statement= conn.createStatement();;
            rs=statement.executeQuery(Query);//here this means it will execute the statement and return it to rs and result is iterable
            while(rs.next()){
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("phonenumber"));
                System.out.println(rs.getString("address"));
                System.out.println(rs.getString("gender"));
                System.out.println(rs.getString("username"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteRowInUser(Connection conn, String username)
    {
        Statement statement;

        try{
            String query = String.format("DELETE from admin where username = '%s'",username);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Deleted Successfully!");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
    public void updateUserName(Connection conn,String oldUserName, String newUserName)
    {
        Statement statement;

        try{

            String query = String.format("update admin set username='%s' where username='%s'",newUserName,oldUserName);
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

            String query = String.format("update admin set phonenumber='%s' where username='%s'",newPhone,username);
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

            String query = String.format("update admin set password='%s' where username='%s'",newPassword,username);
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

            String query = String.format("update admin set address='%s' where username='%s'",newAddress,username);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Address Updated Successfully!");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void updateSecurityAnswer(Connection conn,String username,String newAnswer)
    {
        Statement statement;

        try{

            String query = String.format("update admin set answertosecurityquestion='%s' where username='%s'",newAnswer,username);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Security Answer Updated Successfully!");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public static void adminLogin(Connection conn, String username, String password)

    {
        Statement statement;
        ResultSet rs;

        try{

            String Query = String.format("select password from admin where username = '%s'",username);

            statement=conn.createStatement();
            rs = statement.executeQuery(Query);
            if(!rs.isBeforeFirst())
            {
                System.out.println("invalid username");
                return;
            }
            else{
                rs.next();
                String originalPassword = rs.getString("password");
                if(originalPassword.equals(password))
                {
                    System.out.println("Logged in Successfully!");

                }
                else
                {
                    System.out.println("Invalid password");
                }
            }



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
}