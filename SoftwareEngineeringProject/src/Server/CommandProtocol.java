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
				na.sendString("passwordsDoNotMatch" + "\n", false);
				System.out.println("passwordsDoNotMatch");
			}
			else if (sysUsername != "")
			{
				na.sendString("userExists" + "\n", false);
				System.out.println("userExists = " + sysUsername);
			}
			else if (!RegexValidation.validSimplePassword(password))
			{
				na.sendString("passwordInvalid" + "\n", false);
				System.out.println("passwordInvalid");
			}
			else if (!RegexValidation.validEmailAddress(email))
			{
				na.sendString("emailInvalid" + "\n", false);
				System.out.println("emailInvalid");
			}
			else
			{
				userDB.registerNewUser(username, password, email, lockCount);
				na.sendString("success" + "\n", false);
				System.out.println("success");
			}

			System.out.println("Successfully Registered New User!");
		}
		// User logs in
		else if (parse[0].equals("login"))
		{
			String username = parse[1];
			String sysPassword = userDB.getUserPassword(username);
			int lockCount = Integer.parseInt(userDB.getLockCount(username));
			if (lockCount == 3)
			{
				System.out.println("lockCount == 3");
				na.sendString("accountLocked" + "\n", false);
			}
			else
			{

				if (sysPassword.equals(parse[2]))
				{
					
					ch.getServer().addLoggedInUsers(username); 
					userDB.updateLockCount(username, 0);
					na.sendString("success" + "\n", false);
				}
				else
				{
					na.sendString("invalid" + "\n", false);
					userDB.updateLockCount(username, lockCount++);
				}
			}

		}
		else if (parse[0].equals("recover"))
		{
			String username = parse[1];
			String sysPassword = userDB.getUserPassword(username);
			if (sysPassword == "")
			{
				na.sendString("invalidUsername" + "\n", false);
			}
			else
			{
				String sysEmail = userDB.getEmail(username);
				GmailSMTP.sendMail(sysEmail, sysPassword, username);
				na.sendString("success" + "\n", false);
			}
		}
		else if (parse[0].equals("changePassword"))
		{
			String username = parse[1], currentPassword = parse[2], newPassword = parse[3], reenterPassword = parse[4];
			String sysPassword = userDB.getUserPassword(username);
			if (!sysPassword.equals(currentPassword))
			{
				na.sendString("incorrectCurrentPass" + "\n", false);
			}
			else if (!newPassword.equals(reenterPassword))
			{
				na.sendString("passwordsDoNotMatch" + "\n", false);
			}
			else if (!RegexValidation.validSimplePassword(newPassword))
			{
				na.sendString("passwordInvalid" + "\n", false);
			}
			else
			{
				userDB.changeUserPassword(username, newPassword);
				na.sendString("success" + "\n", false);
			}
		}
		else if (parse[0].equals("logout"))
		{
			ch.getServer().removeLoggedInUsers(parse[1]);
			na.sendString("success" + "\n", false);
		}
		else if (parse[0].equals("application"))
		{
			String app = appDB.getAllData();
			na.sendString(app + "\n", false);
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
		
		
	}

	public static void main(String[] args)
	{
	}
}
