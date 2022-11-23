package ProductManagement;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class marketAnalysis {

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

    public static void market_analysis(Connection conn)
    {
        Statement statement;
        ResultSet rs = null;
        try{

            {
                String Query = String.format("select count(username) as totalUsers from usertable group by username");
                statement = conn.createStatement();
                rs = statement.executeQuery(Query);
                if (rs.next()) {
                    System.out.println("Total Registered users are : " + rs.getInt("totalUsers"));
                } else {
                    System.out.println("Total Registered users are : 0");
                }
            }

            {
                String Query = String.format("select count(username) as totalEmp from employee group by username");
                statement = conn.createStatement();
                rs = statement.executeQuery(Query);
                if(rs.next()) {
                    System.out.println("Total Registered employees are : " + rs.getInt("totalEmp"));
                }
                else{
                    System.out.println("Total Registered employees are : 0");
                }
            }



        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
