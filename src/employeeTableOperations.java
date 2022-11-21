import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class employeeTableOperations {
    public void insert_row(Connection conn,String table_name,int emp_id,String emp_name,String emp_address,int salary,int working_years){
        Statement statement;
        try{
            String query=String.format("insert into"+" %s(emp_id, emp_name,emp_address,salary,working_years) values('%s','%s','%s','%d','%d');",table_name,emp_id,emp_name,emp_address,salary,working_years);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void  read_data(Connection conn,String table_name){
        Statement statement;
        ResultSet rs = null;
        try{
            String Query=String.format("select * from %s",table_name);
            statement= conn.createStatement();;
            rs=statement.executeQuery(Query);//here this means it will execute the statement and return it to rs and result is iterable
            while(rs.next()){//cheking if it has next value
                System.out.println(rs.getString("emp_id"));
                System.out.println(rs.getString("emp_name"));
                System.out.println(rs.getString("emp_address"));
                System.out.println(rs.getString("product_quantity"));
                System.out.println(rs.getString("salary"));
                System.out.println(rs.getString("working_years"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
