package Client;

import Common.NetworkAccess;

public class Client {
	
	/*
	 
	 For use on a single computer with loopback, use 127.0.0.1 or localhost
	 
	 Windows From ipconfig:
	 
	 Wireless LAN adapter Wireless Network Connection:

     Connection-specific DNS Suffix  . : clunet.edu
     Link-local IPv6 Address . . . . . : fe80::1083:3e22:f5a1:a3ec%11
     IPv4 Address. . . . . . . . . . . : 199.107.222.115 <======= This address works
     Subnet Mask . . . . . . . . . . . : 255.255.240.0
     Default Gateway . . . . . . . . . : 199.107.210.2
     
     MacOS From System preferences     
     Network category, read the IP address directly
     
	 */
	
	/**
	 * provides a peer-to-peer connection to the server
	 */
	private NetworkAccess networkaccess;
  	
	public NetworkAccess getNetworkAccess() 
	{
		return networkaccess;
	}
	
	/**
	 * Creates a peer-to-peer connection to the server
	 * 
	 * @param ip: the IP address of the server
	 * @param port: the port on which the server is listening
	 */
	public Client (String ip, int port)
	{
		try
		{
			networkaccess = new NetworkAccess(ip, port);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Disconnects the client from the server
	 */
	public void disconnect ()
	{
    	String text = "disconnect";
		networkaccess.sendString(text,  false);
		networkaccess.close();		
	}
	
	
	/**
	 * start up the client
	 * 
	 * @param args: command line arguments (unused)
	 */
	public static void main(String[] args) {

		String host = "127.0.0.1";
		int port = 8000;
		// -- instantiate a Client object
		//    the constructor will attempt to connect to the server
		Client client = new Client(host, port);
		
		// -- send message to server and receive reply.
		String commandString;
		String replyString;
		
		for (int i = 0; i < 10; ++i) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			};
			
			// -- send a String to the server and wait for the response
			commandString = "hello";
			System.out.println("CLIENT send:  " + commandString);
			replyString = client.networkaccess.sendString(commandString, true);
			System.out.println("CLIENT receive: " + replyString);
			
		}
		
		// -- send an unrecognized command String to the server and wait for the response
		commandString = "huh?";
		System.out.println("CLIENT send:  " + commandString);
		replyString = client.networkaccess.sendString(commandString, true);
		System.out.println("CLIENT receive: " + replyString);
		
		// -- disconnect from the server
		client.disconnect();
	}

}
