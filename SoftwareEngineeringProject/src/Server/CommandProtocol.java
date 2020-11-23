package Server;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import Common.NetworkAccess;

public class CommandProtocol
{

	// Create the Server.UserDatabase Class
	private static UserDatabase userDB = new UserDatabase();
	private static ApplicationDatabase appDB = new ApplicationDatabase();

	public static void processCommand(String cmd, NetworkAccess na, ClientHandler ch)
	{
		System.out.println("SERVER receive: " + cmd);

		String[] parse = cmd.split(";");
		String response = ""; // the response to be sent back to network access.
		if (parse[0].equals("disconnect"))
		{

			// -- no response to the client is necessary
			na.close();
			ch.getServer().removeID(ch.getID());
			ch.Stop();
		}
		// Register New User
		else if (parse[0].equals("register"))
		{
			String email = parse[1], username = parse[2], password = parse[3], reenterPassword = parse[4];
			String sysUsername = userDB.getUserName(username);
			int lockCount = 0;
			if (!password.equals(reenterPassword))
			{
				System.out.println(response = "passwordsDoNotMatch");
			}
			else if (!sysUsername.equals(""))
			{
				response = "userExists";
				System.out.println(response + " = " + sysUsername);
			}
			else if (!RegexValidation.validSimplePassword(password))
			{
				System.out.println(response = "passwordInvalid");
			}
			else if (!RegexValidation.validEmailAddress(email))
			{
				System.out.println(response = "emailInvalid");
			}
			else
			{
				userDB.registerNewUser(username, password, email, lockCount);
				System.out.println(response = "success");
			}

			System.out.println("Successfully Registered New User!");
		}
		// User logs in
		else if (parse[0].equals("login"))
		{
			String username = parse[1];
			String sysPassword = userDB.getUserPassword(username);
			boolean validUser = !sysPassword.equals("");
			int lockCount = validUser ? Integer.parseInt(userDB.getLockCount(username)) : 0;
			if (++lockCount == 3)
			{
				System.out.println("lockCount == 3");
				response = "accountLocked";
			}
			else if (sysPassword.equals(parse[2]))
			{
				ch.getServer().addLoggedInUsers(username);
				userDB.updateLockCount(username, 0);
				response = "success";
			}
			else {
				userDB.updateLockCount(username, lockCount);
				response = "invalid";
			}
		}
		else if (parse[0].equals("recover"))
		{
			String username = parse[1];
			String sysPassword = userDB.getUserPassword(username);
			if (sysPassword.equals(""))
			{
				response = "invalidUsername";
			}
			else
			{
				String sysEmail = userDB.getEmail(username);
				GmailSMTP.sendMail(sysEmail, sysPassword, username);
				response = "success";
			}
		}
		else if (parse[0].equals("changePassword"))
		{
			String username = parse[1], currentPassword = parse[2], newPassword = parse[3], reenterPassword = parse[4];
			String sysPassword = userDB.getUserPassword(username);
			if (!sysPassword.equals(currentPassword))
			{
				response = "incorrectCurrentPass";
			}
			else if (!newPassword.equals(reenterPassword))
			{
				response = "passwordsDoNotMatch";
			}
			else if (!RegexValidation.validSimplePassword(newPassword))
			{
				response = "passwordInvalid";
			}
			else
			{
				userDB.changeUserPassword(username, newPassword);
				response = "success";
			}
		}
		else if (parse[0].equals("logout"))
		{
			ch.getServer().removeLoggedInUsers(parse[1]);
			response = "success";
		}
		else if (parse[0].equals("application"))
		{
			String app = appDB.getAllData();
			response = app;
		}
		
		
//		else if (parse[0].equals("get"))
//		{
//			// Retrieve All User Information
//			if (parse[1].equals("allRows"))
//			{
//				na.sendString(userDB.user_getAllInfo() + "\n", false);
//			}
//			// Retrieve User Email given a Username
//			else if (parse[1].equals("email"))
//			{
//				String username = parse[2];
//				na.sendString(userDB.getEmail(username) + "\n", false);
//				System.out.println("Successfully Retrieved " + username + "'s Email!");
//			}
//			// Retrieve Username given a Username
//			else if (parse[1].equals("username"))
//			{
//				String username = parse[2];
//				na.sendString(userDB.getUserName(username) + "\n", false);
//				System.out.println("Successfully Retrieved " + username + "'s Username!");
//			}
//			// Retrieve User's Password given a Username
//			else if (parse[1].equals("password"))
//			{
//				String username = parse[2];
//				na.sendString(userDB.getUserPassword(username) + "\n", false);
//				System.out.println("Successfully retrieved " + username + "'s Password!");
//			}
//			// Retrieve User's Lock Count given a Username
//			else if (parse[1].equals("lockCount"))
//			{
//				String username = parse[2];
//				na.sendString(userDB.getLockCount(username) + "\n", false);
//				System.out.println("Successfully retrieved " + username + "'s Lock Count!");
//			}
//			// Get All Locked Users
//			else if (parse[1].equals("allLockedUsers"))
//			{
//				na.sendString(userDB.getAllLockedUser() + "\n", false);
//			}
//			// Get the Total Number of Registered Users on the User Database
//			else if (parse[1].equals("rowCount"))
//			{
//				na.sendString(userDB.getRegisteredUserCount() + "\n", false);
//			}
//
//		}
//		else if (parse[0].equals("update"))
//		{
//			// Update User's Lock Count Value given a User Name & New Lock Count Value
//			if (parse[1].equals("lockCount"))
//			{
//				String username = parse[2];
//				int newLockCount = Integer.parseInt(parse[3]);
//				na.sendString(userDB.updateLockCount(username, newLockCount) + "\n", false);
//				System.out.println("Successfully Changed " + username + "'s lockCount = " + newLockCount);
//			}
//			// Update an Existing Password given a User Name & New Password
//			else if (parse[1].equals("password"))
//			{
//				String username = parse[2];
//				String newPW = parse[3];
//				na.sendString(userDB.changeUserPassword(username, newPW) + "\n", false);
//				System.out.println("Successfully Changed " + username + "'s PW to " + newPW + "!");
//			}
//
//		}
//		else
//		{
//			na.sendString(cmd + "\n", false);
//		}

		// send response back to the network access.
		na.sendString(response + "\n",false);
	}

	public static void main(String[] args)
	{
	}
}
