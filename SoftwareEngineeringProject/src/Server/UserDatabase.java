package Server;

import java.sql.*;
import java.util.*;

public class UserDatabase {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    // private final String dbURL = "jdbc:mysql://127.0.0.1:3306/swe_database";
    private final String dbURL = "jdbc:mysql://csc.czmxorkhfbcs.us-west-1.rds.amazonaws.com:3306/CSC?useSSL=false";
    // private final String DBAuser = "root";
    private final String DBAuser = "admin";
    private final String DBApasssword = "adminpass";

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
                String userID = rs.getString("userID");
                String email = rs.getString("email");
                String password = rs.getString("password");
                returnString += (userID + " " + email + " " + password + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    // Register New User
    public String registerNewUser(String username, String password, String email, int lockCount)
    {
        // Create the SQL Query
        String registerUserQuery = "INSERT INTO CSC.user_database\n" + "VALUES(\'" + username + "\', \'"
                + password + "\', \'" + email +"\', " + lockCount;
        try {
                rs = stmt.executeQuery(registerUserQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Successfully Registered New User!";
    }

    // Get User's Email through Username
    public String getEmail(String username)
    {
        String returnString = "";
        String getEmailQuery = "SELECT email\n" +
                "FROM CSC.user_database\n" +
                "WHERE username = \'" + username + "\';";
        try {
            rs = stmt.executeQuery(getEmailQuery);

            while(rs.next()) {
                String email = rs.getString("email");
                returnString += email;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    // Get User Name Given a Username
    public String getUserName(String username)
    {
        String returnString = "";
        String getNameQuery = "SELECT username\n" +
                "FROM CSC.user_database\n" +
                "WHERE username = \'" + username + "\';";
        try {
            rs = stmt.executeQuery(getNameQuery);

            while(rs.next()) {
                String getusername = rs.getString("username");
                returnString += getusername;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    // Get Password Given User Name
    public String getUserPassword(String username)
    {
        String returnString = "";
        String getPasswordQuery = "SELECT password\n" +
                "FROM CSC.user_database\n" +
                "WHERE username = \'" + username + "\';";
        try {
            rs = stmt.executeQuery(getPasswordQuery);

            while(rs.next()) {
                String getUserPW = rs.getString("password");
                returnString += getUserPW;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    // Change User Password
    public String changeUserPassword(String newPW, String username)
    {
        String changePasswordQuery =
                "UPDATE CSC.user_database\n" +
                "SET password = \'" + newPW + "\'\n" +
                "WHERE username = \'" + username + "\';\n";
        try {
            rs = stmt.executeQuery(changePasswordQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Successfully Changed " + username + "'s Password!";
    }

    // Get Lockout Count
    public String getLockCount(String username)
    {
        String returnString = "";
        String getLockCountQuery = "SELECT lockCount\n" +
                "FROM CSC.user_database\n" +
                "WHERE username = \'" + username + "\';";
        try {
            rs = stmt.executeQuery(getLockCountQuery);

            while(rs.next()) {
                String lockCount = rs.getString("lockCount");
                returnString += lockCount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    // Update Lock Count
    public String updateLockCount(String username, String newLockCount)
    {
        String updateLockCountQuery = "UPDATE CSC.user_database\n" +
                "SET lockCount = " + newLockCount + "\n" +
                "WHERE username = \'" + username + "\';";
        try {
            rs = stmt.executeQuery(updateLockCountQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Successfully Updated " + username + "'s LockCount to " + newLockCount + "!";
    }

    // Return All Users that are Currently Locked Out
    public String getAllLockedUser()
    {
        String returnString = "";
        String getLockedUsersQuery = "SELECT username\n" +
                "FROM CSC.user_database\n" +
                "WHERE user_database.lockCount >= 3;";
        try {
            rs = stmt.executeQuery(getLockedUsersQuery);

            while(rs.next()) {
                String getusername = rs.getString("username");
                returnString += getusername;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    // Return the Total Number of Registered Users
    public String getTotalUsers()
    {
        String returnString = "";
        String getTotalUsersQuery = "SELECT COUNT(*)\n" +
                "FROM CSC.user_database;\n";
        try {
            rs = stmt.executeQuery(getTotalUsersQuery);

            while(rs.next()) {
                String totalUsers = rs.getString("count");
                returnString += totalUsers;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnString;
    }


}
