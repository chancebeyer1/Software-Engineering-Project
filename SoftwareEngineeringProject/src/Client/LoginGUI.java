package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class LoginGUI extends JPanel
{
	private JTextField usernameTextfield;
	private JPasswordField passwordField;
	private JLabel welcomeLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JButton registerButton;
	private JButton disconnectButton;
	private JButton recoverButton;


	public LoginGUI(final ClientGUI gui) //final Client client, final JFrame f
	{
		setBounds(0, 0, 550, 600);

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
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(126, 122, 183, 32);
		this.add(passwordField);
		
		passwordField.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent e) {
		    	  if(e.getKeyChar()==KeyEvent.VK_ENTER){
		    		  loginButton.doClick();
	                }
		      }
		    });
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(36, 70, 61, 14);
		this.add(usernameLabel);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(36, 131, 61, 14);
		this.add(passwordLabel);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());
				String commandString = "login;" + usernameTextfield.getText() + ";" + password;
				String replyString = gui.client.getNetworkAccess().sendString(commandString, true);
				if (replyString.equals("success"))
				{
					gui.user = new User(usernameTextfield.getText(),password);
					gui.loggedInPanel();
					usernameTextfield.setText("");
					passwordField.setText("");
				}
				else
				{
					usernameTextfield.setText("INVALID");
					passwordField.setText("");
				}
		    	
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
		disconnectButton.setBounds(139, 241, 155, 23);
		this.add(disconnectButton);
		
		recoverButton = new JButton("Recover Password");
		recoverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uName = usernameTextfield.getText();
				if(uName != null && !uName.equals(""))
				{
					String commandString = "recover;" + usernameTextfield.getText();
					String replyString = gui.client.getNetworkAccess().sendString(commandString, true);	
			    	//Message saying it was sent
					if(replyString.equals("success"))
			    	{
			    		System.out.println("success recieved");
			    		usernameTextfield.setText("Successfully Sent");
			    	}
					else
					{
						usernameTextfield.setText("INVALID");
						passwordField.setText("");
					}
				}
				
				
			}
		});
		recoverButton.setBounds(36, 199, 155, 23);
		this.add(recoverButton);
	}
}
