package Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

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
            System.out.println("Product has been added!");
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


        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public boolean updateInsertProducts(Connection connection) {
        boolean res = false;
        String line = "";
        String splitBy = ",";
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Enter the .csv file name to be updated");
            String fileName = in.nextLine();
            if (fileName.contains(".csv")) {
                fileName = fileName.replace(".csv", "");
            }
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\sai pranith\\Desktop\\csvFiles\\"+fileName+".csv"));
            int flag = 0;
            while ((line = br.readLine()) != null) {
                if (flag == 0) {
                    flag = 1;
                    continue;
                }
                String[] products = line.split(splitBy);

                Statement statement = connection.createStatement();
                String query = "select * from products where productname='" + products[0] + "'";
                ResultSet resultSet = statement.executeQuery(query);
                if (!resultSet.isBeforeFirst()) {
                    String q = "insert into products values(?,?,?,?)";
                    PreparedStatement pstmt = connection.prepareStatement(q);
                    int Quantity = Integer.parseInt(products[1]);
                    int Price = Integer.parseInt(products[2]);
                    pstmt.setString(1, products[0]);
                    pstmt.setInt(2, Quantity);
                    pstmt.setInt(3, Price);
                    pstmt.setString(4, products[3]);
                    pstmt.executeUpdate();
                }
                else {
                    String q = "update products set quantity = " + Integer.parseInt(products[1]) + ",price = " + Integer.parseInt(products[2]) + ",description='" + products[3] +"' where productname='" + products[0] + "'";
                    PreparedStatement pstmt = connection.prepareStatement(q);
                    pstmt.executeUpdate();
                }
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public void deleteRowInProduct(Connection conn, String username)
    {
        Statement statement;

        try{
            String query = String.format("DELETE from products where productname = '%s'",username);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Deleted Successfully!");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public boolean searchProductInRange(Connection conn, int lowerRange, int higherRange)
    {
        Statement statement;
        ResultSet rs = null;
        boolean res = false;
        try{
            String query = String.format("select * from products where price between '%d' and '%d';",lowerRange, higherRange);
            statement= conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next())
            {

                System.out.println("Product name:"+rs.getString("productname"));
                System.out.println("quantity:"+rs.getString("quantity"));
                System.out.println("Price:"+rs.getString("price"));
                System.out.println("description:"+rs.getString("description"));
                System.out.println();
                res = true;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return res;
    }

}