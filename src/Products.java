import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;

public class Products {
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

    public void insertIntoProducts(Connection conn, String productName, int quantity, int price, String productDescription) {
        Statement statement;
        try {
            String query = String.format("insert into products values('%s','%d','%d','%s');",productName,quantity,price,productDescription );
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void  readFromProductsTable(Connection conn){
        Statement statement;
        ResultSet rs = null;
        try{
            String Query=String.format("select * from products");
            statement= conn.createStatement();;

            rs=statement.executeQuery(Query);//here this means it will execute the statement and return it to rs
            //and result is iterable
            int count = 1;
            while(rs.next()){//cheking if it has next value
                System.out.println("Product "+count);
                System.out.println("Product name:"+rs.getString("productname"));
                System.out.println("quantity:"+rs.getString("quantity"));
                System.out.println("Price:"+rs.getString("price"));
                System.out.println("description:"+rs.getString("description"));
                System.out.println();
                count++;
            }
            count = 0;
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void updatePrice(Connection conn,String productName, int newPrice)
    {
        Statement statement;

        try{

            String query = String.format("update products set price='%d' where productname='%s'",newPrice,productName);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Price updated!");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void updateDescription(Connection conn,String productName, String newDescription)
    {
        Statement statement;

        try{

            String query = String.format("update products set description='%s' where productname='%s'",newDescription,productName);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Description updated!");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void updateQuantity(Connection conn,String productName, int newQuantity)
    {
        Statement statement;

        try{

            String query = String.format("update products set quantity='%d' where productname='%s'",newQuantity,productName);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("newQuantity updated!");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public boolean  checkIfPresent(Connection conn, String productname){
        Statement statement;
        ResultSet rs = null;
        try{
            String Query=String.format("select * from products where productname = '%s'",productname);
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
    public void  readAProduct(Connection conn,String productName){
        Statement statement;
        ResultSet rs = null;
        try{
            String Query=String.format("select * from products where productname='%s'",productName);
            statement= conn.createStatement();;

            rs=statement.executeQuery(Query);//here this means it will execute the statement and return it to rs
            //and result is iterable
            while(rs.next()){//cheking if it has next value
                System.out.println("Product name:"+rs.getString("productname"));
                System.out.println("quantity:"+rs.getString("quantity"));
                System.out.println("Price:"+rs.getString("price"));
                System.out.println("description:"+rs.getString("description"));
                System.out.println();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public int  readProductQuantity(Connection conn,String productName){
        Statement statement;
        ResultSet rs = null;
        try{
            String Query=String.format("select * from products where productname='%s'",productName);
            statement= conn.createStatement();;

            rs=statement.executeQuery(Query);

            while(rs.next()){
                int quantity = rs.getInt("quantity");
                return quantity;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public int getPrice(Connection conn,String productName)
    {
        Statement statement;
        ResultSet rs = null;
        try{

            String query = String.format("select * from products where productname='%s'",productName);
            statement=conn.createStatement();
            rs = statement.executeQuery(query);
            rs.next();
            return rs.getInt("price");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }

    public void updateProductQuantity(Connection conn, ArrayList<String> products, ArrayList<Integer> quantity)
    {
        Statement statement;
        try{

            int len = products.size();

            for(int i = 0; i<len; i++)
            {

                String query = String.format("update products set quantity= quantity - '%d' where productname='%s'",quantity.get(i), products.get(i));
                statement=conn.createStatement();
                statement.executeUpdate(query);

            }

            System.out.println("newQuantity updated!");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}