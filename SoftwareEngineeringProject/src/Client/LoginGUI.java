package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JPanel
{
	private JTextField usernameTextfield;
	private JTextField passwordTextfield;
	private JLabel welcomeLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JButton registerButton;
	private JButton disconnectButton;
	private JButton recoverButton;


	public LoginGUI(final GUI gui) //final Client client, final JFrame f
	{
		setBounds(100, 100, 550, 600);

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		welcomeLabel = new JLabel("Welcome!");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(126, 11, 183, 26);
		this.add(welcomeLabel);
		
		usernameTextfield = new JTextField();
		usernameTextfield.setBounds(126, 61, 183, 32);
		this.add(usernameTextfield);
		usernameTextfield.setColumns(10);
		
		passwordTextfield = new JTextField();
		passwordTextfield.setColumns(10);
		passwordTextfield.setBounds(126, 122, 183, 32);
		this.add(passwordTextfield);
		
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(36, 70, 61, 14);
		this.add(usernameLabel);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(36, 131, 61, 14);
		this.add(passwordLabel);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String commandString = "login;" + usernameTextfield.getText() + ";" + passwordTextfield.getText();
				String replyString = gui.client.getNetworkAccess().sendString(commandString, true);
		    	gui.loggedInPanel();
			}
		});
		loginButton.setBounds(171, 165, 89, 23);
		this.add(loginButton);
		
		registerButton = new JButton("Register New User");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.registerPanel();
			}
		});
		registerButton.setBounds(244, 199, 166, 23);
		this.add(registerButton);
		
		disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String commandString = "disconnect;";
				String replyString = gui.client.getNetworkAccess().sendString(commandString, false);
		    	gui.connectPanel();
		    	//f.setState(Frame.NORMAL);
			}
		});
		disconnectButton.setBounds(31, 199, 155, 23);
		this.add(disconnectButton);
		
		recoverButton = new JButton("Recover Password");
		recoverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uName = usernameTextfield.getText();
				if(uName != null || uName != "")
				{
					String commandString = "recover;" + usernameTextfield.getText();
					String replyString = gui.client.getNetworkAccess().sendString(commandString, true);	
			    	//Message saying it was sent
				}
				else
				{
					String msg = "";
					//Message saying error
				}
				
			}
		});
		recoverButton.setBounds(137, 233, 155, 23);
		this.add(recoverButton);
	}
}
