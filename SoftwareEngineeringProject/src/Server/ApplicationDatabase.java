package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplicationDatabase
{

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private final String dbURL = "jdbc:mysql://csc.czmxorkhfbcs.us-west-1.rds.amazonaws.com:3306/CSC?useSSL=false";
	private final String DBAuser = "admin";
	private final String DBApasssword = "adminpass";

	public ApplicationDatabase()
	{
		try
		{
			this.conn = DriverManager.getConnection(dbURL, DBAuser, DBApasssword);
			stmt = conn.createStatement();
			System.out.println("Connection To the Database is Successful");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public String getAllData()
	{
		String returnString = "";
		String sqlQuery = "Select * FROM CSC.application";

		try
		{
			rs = stmt.executeQuery(sqlQuery);

			while (rs.next())
			{
				String rank = rs.getString("Overall rank");
				String country = rs.getString("Country or region");
				String score = rs.getString("Score");
				String gdp = rs.getString("GDP per capita");
				String socialSupport = rs.getString("Social support");
				String lifeExpectancy = rs.getString("Healthy life expectancy");
				returnString += returnString == ""
						? (rank + "," + country + "," + score + "," + gdp + "," + socialSupport + "," + lifeExpectancy)
						: (";" + rank + "," + country + "," + score + "," + gdp + "," + socialSupport + ","
								+ lifeExpectancy);

			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return returnString;
	}
}
