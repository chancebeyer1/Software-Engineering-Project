package Server;

import java.awt.BorderLayout;
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

public class SerGUI extends JFrame
{

	private Server server;
	private JTextField registeredUsers;
	private JTextField numLoggedInUsers;
	private JTextField lockedOutUsers;
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
					SerGUI frame = new SerGUI();
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
	public SerGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 600);
		getContentPane().setLayout(null);
		
		getQueriesButton = new JButton("Get Queries");
		getQueriesButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
	            		int p = server.getconnections();
	            		connectedUsers.setText(p + "");	    													
					}
		});
		getQueriesButton.setBounds(33, 226, 115, 23);
		getContentPane().add(getQueriesButton);
		
		
		registeredUsers = new JTextField();
		registeredUsers.setBounds(387, 51, 86, 20);
		getContentPane().add(registeredUsers);
		registeredUsers.setColumns(10);
		
		numLoggedInUsers = new JTextField();
		numLoggedInUsers.setBounds(387, 118, 86, 20);
		getContentPane().add(numLoggedInUsers);
		numLoggedInUsers.setColumns(10);
		
		loggedInUsers = new JTextArea();
		loggedInUsers.setBounds(290, 178, 234, 114);
		getContentPane().add(loggedInUsers);
		
		lockedOutUsers = new JTextField();
		lockedOutUsers.setBounds(387, 338, 86, 20);
		getContentPane().add(lockedOutUsers);
		lockedOutUsers.setColumns(10);
		
		connectedUsers = new JTextField();
		connectedUsers.setBounds(387, 420, 86, 20);
		getContentPane().add(connectedUsers);
		connectedUsers.setColumns(10);
		
		registerUserLabel = new JLabel("Registered users:");
		registerUserLabel.setBounds(245, 53, 132, 17);
		getContentPane().add(registerUserLabel);
		
		numLoggedInUsersLabel = new JLabel("Logged in users:");
		numLoggedInUsersLabel.setBounds(245, 121, 132, 17);
		getContentPane().add(numLoggedInUsersLabel);
		
		lockedOutUsersLabel = new JLabel("Locked out users:");
		lockedOutUsersLabel.setBounds(245, 341, 132, 17);
		getContentPane().add(lockedOutUsersLabel);
		
		connectedUsersLabel = new JLabel("Connected users:");
		connectedUsersLabel.setBounds(245, 423, 132, 17);
		getContentPane().add(connectedUsersLabel);
		
		loggedInUsersLabel = new JLabel("Logged in users:");
		loggedInUsersLabel.setBounds(179, 230, 132, 17);
		getContentPane().add(loggedInUsersLabel);
	}
}
