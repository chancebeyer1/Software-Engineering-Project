package BasicClientServer;



import java.io.IOException;
import java.net.Socket;


public class ClientHandler extends Thread {
	
	/**
	 * provides a peer-to-peer connection to the client
	 */
	NetworkAccess networkaccess;
	
	/**
	 * controls the run thread
	 */
	private boolean go;
	
	/**
	 * the name of this client
	 */
	private String name;
	
	/**
	 * the unique id of this client
	 */
	private int id;
		
	/**
	 * a reference to the server that "has" this ClientHandler
	 */
	private Server server;
	
	/**
	 * Constructor saves the ID, socket, and reference to the server
	 * then construct the NetworkAccess object
	 * 
	 * @param id: the unique ID for this ClientHandler
	 * @param socket: the peer-to-peer socket for connection to the client
	 * @param server: reference to the server that "has" this ClientHandler
	 */
	public ClientHandler (int id, Socket socket, Server server) 
	{
		this.server = server;
		this.id = id;
		this.name = Integer.toString(id);
		go = true;
		
		networkaccess = new NetworkAccess(socket);		
	}
	

	public String toString ()
	{
		return name;
	}
	
	/**
	 * getter function for the private name field
	 * 
	 * @return name
	 */
	public String getname ()
	{
		return name;
	}


	// -- similar to a main() function in that it is the entry point of
	//    the thread
	public void run () {
		
		// -- server thread runs until the client terminates the connection
		while (go) {
			try {
				// -- always receives a String object with a newline (\n)
				//    on the end due to how BufferedReader readLine() works.
				//    The client adds it to the user's string but the BufferedReader
				//    readLine() call strips it off
				String txt = networkaccess.readString();
				System.out.println("SERVER receive: " + txt);
				
				// -- if it is not the termination message, send it back adding the
				//    required (by readLine) "\n"

				// -- if the disconnect string is received then 
				//    close the socket, remove this thread object from the
				//    server's active client thread list, and terminate the thread
				//    this is the server side "command processor"
				//    you will need to define a communication protocol (language) to be used
				//    between the client and the server
				//    e.g. client sends "LOGIN;<username>;<password>\n"
				//         server parses it to "LOGIN", "<username>", "<password>" and performs login function
				//         server responds with "SUCCESS\n"
				//    this is where all the server side Use Cases will be handled
				//    you may want to create a separate class called e.g. "CommandProcessor" or "CommunicationProtocol"
				if (txt.equals("disconnect")) {
					
					// -- no response to the client is necessary
					networkaccess.close();
					server.removeID(id);
					go = false; // terminate the thread
				}
				else if (txt.equals("hello")) {
						
					// -- client is expecting a response
					networkaccess.sendString("world!" + "\n", false);
					
				}
				else {
					
					System.out.println("unrecognized command >>" + txt + "<<");
					networkaccess.sendString(txt + "\n", false);
					
				}
			} 
			catch (IOException e) {
				
				e.printStackTrace();
				go = false;
				
			}
			
		}
	}
}
