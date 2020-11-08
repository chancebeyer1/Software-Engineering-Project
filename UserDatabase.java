package BasicClientServer;

import java.sql.*;
import java.util.*;

public class UserDatabase {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private final String dbURL = "jdbc:mysql://127.0.0.1:3306/swe_database";
    private final String DBAuser = "root";
    private final String DBApasssword = "986024";

    public UserDatabase() {
        try {
            this.conn = DriverManager.getConnection(dbURL, DBAuser, DBApasssword);
            stmt = conn.createStatement();
            System.out.println("Connection To the Database is Successful!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // User Database SQL Querying Methods
    public String user_getAllInfo() {
        String returnString = "";
        String sqlQuery = "Select * FROM swe_database.user_database";

        try {
            rs = stmt.executeQuery(sqlQuery);

            while(rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                returnString += (firstName + " " + lastName + " " + email + " " + password + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    public String user_getUserName(String userEmail) {

        String firstName = "", lastName = "";

        String sqlQuery = "Select first_name, last_name From swe_database.user_database WHERE email = \"" + userEmail + "\"";

        try {
            rs = stmt.executeQuery(sqlQuery);

            while(rs.next()) {
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (firstName + " " + lastName);
    }

    public String user_getUserPassword(String userEmail) {
        String password = "";

        String sqlQuery = "Select password From swe_database.user_database WHERE email = \"" + userEmail + "\"";

        try {
            rs = stmt.executeQuery(sqlQuery);

            while(rs.next()) {
                password = rs.getString("password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    public void user_changePassword(String userEmail, String newPassword) throws SQLException {

        String sqlUpdate = "UPDATE swe_database.user_database "
                + "SET password = ? "
                + "WHERE email = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, userEmail);
            pstmt.executeUpdate();
            System.out.println("Password Successfully Changed!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
