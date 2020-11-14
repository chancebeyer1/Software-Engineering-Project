

import java.awt.EventQueue;
import java.sql.*;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.sql.Statement;
import Client.GUI;
import Server.ServerGUI;

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
            System.out.println("Connection To the Database is Successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // User Database SQL Querying Methods
    public String user_getAllInfo() {
        String returnString = "";
        String sqlQuery = "Select * FROM CSC.user";

        try {
            rs = stmt.executeQuery(sqlQuery);

            while(rs.next()) {
                String userID = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                returnString += (userID + " " + email + " " + password + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    public String user_getUserName(String userEmail) {

        String username = "";

        String sqlQuery = "Select username From CSC.user WHERE email = \"" + userEmail + "\"";

        try {
            rs = stmt.executeQuery(sqlQuery);

            while(rs.next()) {
            	username = rs.getString("username");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (username);
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
    
    public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UserDatabase db = new UserDatabase();
					System.out.println(db.user_getUserName("chance@gmail.com"));
					System.out.println(db.user_getAllInfo());
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
    
}
