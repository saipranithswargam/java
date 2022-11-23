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
        try {
            String Query = String.format("select * from usertable");
            statement = conn.createStatement();
            int count = 0;
            rs = statement.executeQuery(Query);//here this means it will execute the statement and return it to rs and result is iterable
            while (rs.next()) {
                count++;
            }
            System.out.println("Total Registered users are :"+count);
        } catch (Exception e) {
            System.out.println(e);
        }
        try{
            String Query=String.format("select * from employee");
            statement= conn.createStatement();;
            rs=statement.executeQuery(Query);
            int count = 0;
            while(rs.next()){
                count++;
            }
            System.out.println("Total Registered employees are :"+count);

        }catch (Exception e){
            System.out.println(e);
        }
    }

}
