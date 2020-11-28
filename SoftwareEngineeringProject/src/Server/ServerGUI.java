package Server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerGUI extends JFrame
{

	private Server server;
	private static UserDatabase db_obj = new UserDatabase();
	private JTextField registeredUsers;
	private JTextField numLoggedInUsers;
	private JTextArea lockedOutUsers;
	private JTextField connectedUsers;
	private JTextArea loggedInUsers;
	private JLabel registerUserLabel;
	private JLabel numLoggedInUsersLabel;
	private JLabel lockedOutUsersLabel;
	private JLabel connectedUsersLabel;
	private JLabel loggedInUsersLabel;
	private JButton getQueriesButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ServerGUI frame = new ServerGUI();
					frame.setVisible(true);
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 600);
		getContentPane().setLayout(null);
		
		getQueriesButton = new JButton("Get Queries");
		getQueriesButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					
						//Query number of connected users
	            		int connected = server.getconnections();
	            		connectedUsers.setText(connected + "");
	            		
	            		//Query number of logged in users
	            		int loggedIn = server.getLoggedInUsers();
	            		numLoggedInUsers.setText(loggedIn + "");	
	            		
	            		//Query number of registered users
	            		int registered = db_obj.getRegisteredUserCount();
	            		registeredUsers.setText(registered + "");
	            		
	            		//Query which users are logged in
	    				String loggedInUsernames = server.getAllLoggedInUsers();
	    				loggedInUsers.setText(loggedInUsernames + "");
	            		
	            		//Query which users are locked out
	    				String allLocked = db_obj.getAllLockedUser();
	    				lockedOutUsers.setText(allLocked + "");
	            		
					}
		});
		getQueriesButton.setBounds(46, 254, 115, 23);
		getContentPane().add(getQueriesButton);
		
		
		registeredUsers = new JTextField();
		registeredUsers.setBounds(387, 51, 86, 20);
		getContentPane().add(registeredUsers);
		registeredUsers.setColumns(10);
		
		numLoggedInUsers = new JTextField();
		numLoggedInUsers.setBounds(387, 133, 86, 20);
		getContentPane().add(numLoggedInUsers);
		numLoggedInUsers.setColumns(10);
		
		loggedInUsers = new JTextArea();
		loggedInUsers.setBounds(290, 308, 234, 86);
		getContentPane().add(loggedInUsers);
		
		lockedOutUsers = new JTextArea();
		lockedOutUsers.setBounds(290, 427, 234, 86);
		getContentPane().add(lockedOutUsers);
		lockedOutUsers.setColumns(10);
		
		connectedUsers = new JTextField();
		connectedUsers.setBounds(387, 217, 86, 20);
		getContentPane().add(connectedUsers);
		connectedUsers.setColumns(10);
		
		registerUserLabel = new JLabel("Registered users:");
		registerUserLabel.setBounds(245, 53, 132, 17);
		getContentPane().add(registerUserLabel);
		
		numLoggedInUsersLabel = new JLabel("Logged in users:");
		numLoggedInUsersLabel.setBounds(245, 135, 132, 17);
		getContentPane().add(numLoggedInUsersLabel);
		
		lockedOutUsersLabel = new JLabel("Locked out users:");
		lockedOutUsersLabel.setBounds(166, 464, 132, 17);
		getContentPane().add(lockedOutUsersLabel);
		
		connectedUsersLabel = new JLabel("Connected users:");
		connectedUsersLabel.setBounds(245, 219, 132, 17);
		getContentPane().add(connectedUsersLabel);
		
		loggedInUsersLabel = new JLabel("Logged in users:");
		loggedInUsersLabel.setBounds(166, 344, 132, 17);
		getContentPane().add(loggedInUsersLabel);
		
		final JButton serverStart = new JButton("START SERVER");
		serverStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server = new Server();
            	Thread serverthread = new Thread(server);
            	serverthread.start();
            	serverStart.setEnabled(false);
            	serverStart.setBackground(Color.GRAY);
			}
		});
		serverStart.setBounds(25, 47, 162, 60);
		getContentPane().add(serverStart);
	}
}
