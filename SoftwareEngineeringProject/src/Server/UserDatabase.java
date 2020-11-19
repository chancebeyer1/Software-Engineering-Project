package Server;

import java.sql.*;
import java.util.*;

public class UserDatabase {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private final String dbURL = "jdbc:mysql://csc.czmxorkhfbcs.us-west-1.rds.amazonaws.com:3306/CSC?useSSL=false";
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
        String sqlQuery = "Select * FROM CSC.user_database";

        try {
            rs = stmt.executeQuery(sqlQuery);

            while(rs.next()) {
                String userID = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String lockCount = rs.getString("lockCount");
                returnString += (userID + " " + email + " " + password + " " + lockCount + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    // Register New User
    public String registerNewUser(String pass_username, String pass_password, String pass_email, int pass_lockCount)
    {
        // Create the SQL Query
        String registerUserQuery = "INSERT INTO CSC.user_database (username, password, email, lockCount)\n"
                + "VALUES(\"" + pass_username + "\", \""
                + pass_password + "\", \""
                + pass_email + "\", "
                + pass_lockCount + ");";
        System.out.println(registerUserQuery);
        try {
                stmt.executeUpdate(registerUserQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
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
    public String changeUserPassword(String username, String newPW)
    {
        String changePasswordQuery =
                "UPDATE CSC.user_database\n" +
                "SET password = \'" + newPW + "\'\n" +
                "WHERE username = \'" + username + "\';\n";
        try {
            stmt.executeUpdate(changePasswordQuery);
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
        System.out.println("Successfully Retrieved " + username + "'s lock count = " + returnString + "!");
        return returnString;
    }

    // Update Lock Count
    public String updateLockCount(String username, int newLockCount)
    {
        String updateLockCountQuery = "UPDATE CSC.user_database\n" +
                "SET lockCount = " + newLockCount + "\n" +
                "WHERE username = \'" + username + "\';";
        try {
            stmt.executeUpdate(updateLockCountQuery);
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
                returnString += getusername + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    // Return the Total Number of Registered Users
    public int getRegisteredUserCount()
    {
        int count = 0;
        String returnString = "";
        String getTotalUsersQuery = "SELECT COUNT(*)\n" +
                "FROM CSC.user_database\n;";
        try {
            rs = stmt.executeQuery(getTotalUsersQuery);
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Total Registered Users: " + count);
        return count;
    }
    
    
    
//    public String getAllRegisteredUsers()
//    {
//        String returnString = "";
//        String getAllRegisteredUsersQuery = "SELECT username\n" +
//                "FROM CSC.user_database\n;";
//        try {
//            rs = stmt.executeQuery(getAllRegisteredUsersQuery);
//
//            while(rs.next()) {
//                String getusername = rs.getString("username");
//                returnString += getusername + "\n";
//                //returnString += returnString == "" ? getusername + "\n" : (";" + getusername + "\n");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return returnString;
//    }
    
}
