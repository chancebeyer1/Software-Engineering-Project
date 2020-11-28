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
		String response = "Invalid"; // the response to be sent back to network access.
		System.out.println(parse.length);
		if (parse[0].equals("disconnect"))
		{

			// -- no response to the client is necessary
			na.close();
			ch.getServer().removeID(ch.getID());
			ch.Stop();
			return;
		}
		// Register New User
		else if (parse[0].equals("register") && parse.length == 5)
		{
			String email = parse[1], username = parse[2], password = parse[3], reenterPassword = parse[4];
			String sysUsername = userDB.getUserName(username);
			int lockCount = 0;
			if (!password.equals(reenterPassword))
			{
				response = "passwordsDoNotMatch";
			}
			else if (!sysUsername.equals(""))
			{
				response = "userExists";
				System.out.println(sysUsername);
			}
			else if (!RegexValidation.validSimplePassword(password))
			{
				response = "passwordInvalid";
			}
			else if (!RegexValidation.validEmailAddress(email))
			{
				response = "emailInvalid";
			}
			else
			{
				userDB.registerNewUser(username, password, email, lockCount);
				response = "success";
			}

			//System.out.println("Successfully Registered New User!");
		}
		// User logs in
		else if (parse[0].equals("login") && parse.length == 3)
		{
			
			String username = parse[1];
			String sysPassword = userDB.getUserPassword(username);
			boolean validUser = !sysPassword.equals("");
			int lockCount = validUser ? Integer.parseInt(userDB.getLockCount(username)) : 0;
			if (++lockCount >= 3)
			{
				System.out.println("lockCount == 3");

				userDB.updateLockCount(username, 3);
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
		else if (parse[0].equals("recover") && parse.length == 2)
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
		else if (parse[0].equals("changePassword") && parse.length == 5)
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
		else if (parse[0].equals("logout") && parse.length == 2)
		{
			ch.getServer().removeLoggedInUsers(parse[1]);
			response = "success";
		}
		else if (parse[0].equals("application"))
		{
			String app = appDB.getAllData();
			response = app;
		}
		

		// send response back to the network access.
		System.out.println(response);
		na.sendString(response + "\n",false);

	}

	public static void main(String[] args)
	{
	}
}
