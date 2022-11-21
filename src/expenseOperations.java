import java.sql.*;
import java.sql.Connection;

public class expenseOperations {

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


    public void insertIntoUserExpense(Connection conn,String username, int expense){
        Statement statement;
        try{
            String query=String.format("insert into userexpense values('%s','%d');",username, expense);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Expense added in userExpenses!");
        }catch (Exception e){
            System.out.println(e);
        }
    }



    public void updateUserExpense(Connection conn,String username, int total){
        Statement statement;
        try{
            String query=String.format("update userexpense set expense = expense + '%d' where username = '%s';",total,username);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Expense updated!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public boolean  checkIfPresent(Connection conn, String username){
        Statement statement;
        ResultSet rs = null;
        try{
            String Query=String.format("select * from userexpense where username = '%s'",username);
            statement= conn.createStatement();;

            rs=statement.executeQuery(Query);

            if(!rs.isBeforeFirst())
            {
                return true;
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

}