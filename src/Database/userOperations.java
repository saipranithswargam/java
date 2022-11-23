package Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class userOperations {


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

    public void insertIntoUser(Connection conn, String name, String phoneNumber, String address, String gender, String answerToSecurityQuestion, String username, String password) {
        Statement statement;
        try {
            String query = String.format("insert into usertable(name, phonenumber, address, gender, answertosecurityquestion, username, password) values('%s','%s','%s','%s','%s','%s','%s');", name, phoneNumber, address, gender, answerToSecurityQuestion, username, password);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readFromUser(Connection conn) {
        Statement statement;
        ResultSet rs = null;
        int count = 1;
        try {
            String Query = String.format("select * from usertable");
            statement = conn.createStatement();
            ;
            rs = statement.executeQuery(Query);//here this means it will execute the statement and return it to rs and result is iterable
            while (rs.next()) {
                System.out.println("user " + count + " details:");
                System.out.println("Username : "+rs.getString("username"));
                System.out.println("Name : "+rs.getString("name"));
                System.out.println("Mob No : " +rs.getString("phonenumber"));
                System.out.println("Address : "+rs.getString("address"));
                System.out.println("Gender : "+rs.getString("gender"));

                count++;
            }
            count = 0;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteRowInUser(Connection conn, String username) {
        Statement statement;

        try {
            String query = String.format("DELETE from usertable where username = '%s'", username);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Deleted Successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void updateUserName(Connection conn, String oldUserName, String newUserName) {
        Statement statement;

        try {

            String query = String.format("update usertable set username='%s' where username='%s'", newUserName, oldUserName);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("\n*********************UserName has been updated successfully!**********************");

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void updatePhone(Connection conn, String username, String newPhone) {
        Statement statement;

        try {

            String query = String.format("update usertable set phonenumber='%s' where username='%s'", newPhone, username);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("User PhoneNo updated!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void updatePassword(Connection conn, String username, String newPassword) {
        Statement statement;

        try {

            String query = String.format("update usertable set password='%s' where username='%s'", newPassword, username);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("**********************************Password has been updated Successfully!*******************************");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateAddress(Connection conn, String username, String newAddress) {
        Statement statement;

        try {

            String query = String.format("update usertable set address='%s' where username='%s'", newAddress, username);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Address Updated Successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateSecurityAnswer(Connection conn, String username, String newAnswer) {
        Statement statement;

        try {

            String query = String.format("update usertable set answertosecurityquestion='%s' where username='%s'", newAnswer, username);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Security Answer Updated Successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean userLogin(Connection conn, String username, String password) {
        Statement statement;
        ResultSet rs;

        try {
            String Query = String.format("select password from usertable where username = '%s'", username);
            statement = conn.createStatement();
            rs = statement.executeQuery(Query);
            if (!rs.isBeforeFirst()) {
                System.out.println("invalid username");
            } else {
                rs.next();
                String originalPassword = rs.getString("password");
                if (originalPassword.equals(password)) {
                    System.out.println("***************************Logged in Successfully!*************************");
                    return true;

                } else {
                    System.out.println("Invalid password");

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkIfPresent(Connection conn, String username) {
        Statement statement;
        ResultSet rs = null;
        try {
            String Query = String.format("select * from usertable where username = '%s'", username);
            statement = conn.createStatement();
            rs = statement.executeQuery(Query);

            if (!rs.isBeforeFirst()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean readOneUser(Connection conn, String username) {
        Statement statement;
        ResultSet rs = null;
        try {
            String Query = String.format("select * from usertable where username = '%s'", username);
            statement = conn.createStatement();
            rs = statement.executeQuery(Query);
            if (!rs.isBeforeFirst()) {
                return false;
            } else {
                while (rs.next()) {
                    System.out.println("Username :" + rs.getString("username"));
                    System.out.println("Name :" + rs.getString("name"));
                    System.out.println("Mob No :" + rs.getString("phonenumber"));
                    System.out.println("Address :" + rs.getString("address"));
                    System.out.println("Gender :" + rs.getString("gender"));
                }
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean forgotPassword(Connection conn, String username, String answer) {
        Statement statement;
        ResultSet rs = null;
        try {
            String Query = String.format("select * from usertable where username = '%s'", username);
            statement = conn.createStatement();
            ;
            rs = statement.executeQuery(Query);
            if (!rs.isBeforeFirst()) {
                return false;
            } else {
                if (rs.next()) {
                    String answerToSecurityQuestion = rs.getString("answertosecurityquestion");
                    if (answer.equals(answerToSecurityQuestion)) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public void updateUserProducts(Connection conn, String username, ArrayList<String> incomingList) { //username,input array
        Statement statement;
        ResultSet rs = null;

        int arrSize = incomingList.size();

        String[] userarray = new String[arrSize];

        for (int i = 0; i < arrSize; i++) {
            userarray[i] = incomingList.get(i);
        }
        try {
            String Query = String.format("select * from usertable where username='%s'", username);

            statement = conn.createStatement();
            rs = statement.executeQuery(Query);//here this means it will execute the statement and return it to rs and result is iterable
            while (rs.next()) {
                if (rs.getArray("products") == null) {
                    Array array = conn.createArrayOf("text", userarray);
                    PreparedStatement st;
                    st = conn.prepareStatement("update usertable set products = ? where username = ?");
                    st.setArray(1, array);
                    st.setString(2, username);
                    st.executeUpdate();
                    System.out.println("products updated");
                } else {
                    Array a = rs.getArray("products");
                    String[] products = (String[]) a.getArray();

                    int len1 = products.length;
                    int len2 = userarray.length;

                    String[] thirdarray = new String[len1 + len2];

                    for (int i = 0; i < len1; i++) {
                        thirdarray[i] = products[i];
                    }

                    int j = 0;

                    for (int i = len1; i < len2 + len1; i++) {
                        thirdarray[i] = userarray[j];
                        j++;
                    }
                    PreparedStatement st;
                    st = conn.prepareStatement("update usertable set products = ? where username = ?");
                    Array array = conn.createArrayOf("text", thirdarray);
                    st.setArray(1, array);
                    st.setString(2, username);
                    st.executeUpdate();

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public boolean updateInsertUser(Connection connection) {
        boolean res = false;
        String line = "";
        String splitBy = ",";
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Enter the .csv file name");
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
                String[] user = line.split(splitBy);

                Statement statement = connection.createStatement();
                String query = "select * from usertable where username='" + user[5] + "'";
                ResultSet resultSet = statement.executeQuery(query);
                if (!resultSet.isBeforeFirst()) {
                    String q = "insert into usertable values(?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = connection.prepareStatement(q);

                    pstmt.setString(1, user[0]);
                    pstmt.setString(2, user[1]);
                    pstmt.setString(3, user[2]);
                    pstmt.setString(4, user[3]);
                    pstmt.setString(5, user[4]);
                    pstmt.setString(6, user[5]);
                    pstmt.setString(7, user[6]);

                    pstmt.executeUpdate();

                }
                else {
                    String q = "update usertable set name = '" + user[0] + "',phonenumber = '" + user[1] + "',address = '" + user[2] + "',gender='" + user[3] +"',answertosecurityquestion='" + user[4] +"',password='" + user[6] + "' where username='" + user[5] + "'";
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
}
