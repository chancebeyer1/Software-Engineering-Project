package BasicClientServer;

import java.util.HashMap;
import java.util.Set;

public class CommandProtocol {


	private static HashMap<String, String> commands;
	static {
	    commands = new HashMap<>();
		commands.put("disconnect", "");
		commands.put("hello", "world!");
		commands.put("showmethemoney", "");
	}

	// Create the BasicClientServer.UserDatabase Class
	private static UserDatabase db_obj = new UserDatabase();

	/**
	 * process commands sent to the server
	 * @param cmd: command to be processed
	 * @param na: NetworkAccess object for communication
	 * @param ch: ClientHandler object requesting the processing
	 * @return
	 */
	public static void processCommand(String cmd, NetworkAccess na, ClientHandler ch)
	{
		System.out.println("SERVER receive: " + cmd);
		
		if (cmd.equals("disconnect")) {

			// -- no response to the client is necessary
			na.close();
			ch.getServer().removeID(ch.getID());
			ch.Stop();
		}
		else if (cmd.equals("hello")) {
				
			// -- client is expecting a response
			na.sendString("world!" + "\n", false);
			
		}
		else if (cmd.equals("showmethemoney")) {
			na.sendString(db_obj.user_getAllInfo() + "\n", false);
		}
		else {
			na.sendString(cmd + "\n", false);
		}		
	}

	public static void main (String[] args) {
//		System.out.println(commands.get("disconnect"));
//		System.out.println(commands.get("hello"));
//
//		Set<String>keys = commands.keySet();
//		for (String key : keys) {
//			System.out.println(key);
//		}
//
//		System.out.println(commands.get("goodbye"));
	}
}
