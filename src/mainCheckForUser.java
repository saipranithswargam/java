import java.sql.Connection;

public class mainCheckForUser {

    public static void main(String args[]) {

        userOperations db = new userOperations();
        Connection conn = db.connect_to_db("postgres", "postgres", "220755");

//        db.insertIntoUser(conn,"Zeeshan","123456","Lucknow","Male","Hello","Zeeshan251","Zeeshan@123");

//        db.readFromUser(conn);

//        db.updateAddress(conn,"Zeeshan251","Hyderabad");

//        db.updatePhone(conn,"Zeeshan251","7651878616");

//        db.updatePassword(conn,"Zeeshan251","Zeeshan@220755");

//        db.updateSecurityAnswer(conn,"Zeeshan251","newSecurityAnswer is updated");

//        db.updateUserName(conn,"Zeeshan251","Pranith251");

        db.userLogin(conn,"Pranith2025","Pranith@123");



    }


}
