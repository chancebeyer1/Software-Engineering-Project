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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegisterGUI extends JPanel
{
	private JTextField emailTextfield;
	private JTextField usernameTextfield;
	private JPasswordField passwordField;
	private JPasswordField reenterPasswordField;
	private JLabel registrationLabel;
	private JLabel emailLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel reenterPasswordLabel;
	private JButton registerButton;
	private JButton disconnectButton;
	private JButton backButton;


	/**
	 * Create the frame.
	 */
	public RegisterGUI(final ClientGUI gui) //final Client client, final JFrame f
	{
		setBounds(0, 0, 550, 600);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		registrationLabel = new JLabel("Registration");
		registrationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registrationLabel.setBounds(0, 11, 534, 24);
		this.add(registrationLabel); 
		
		emailTextfield = new JTextField();
		emailTextfield.setBounds(208, 66, 267, 51);
		this.add(emailTextfield);
		emailTextfield.setColumns(10);
		
		usernameTextfield = new JTextField();
		usernameTextfield.setColumns(10);
		usernameTextfield.setBounds(208, 155, 267, 51);
		this.add(usernameTextfield);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(208, 244, 267, 51);
		this.add(passwordField);
		
		reenterPasswordField = new JPasswordField();
		reenterPasswordField.setColumns(10);
		reenterPasswordField.setBounds(208, 339, 267, 51);
		this.add(reenterPasswordField);
		
		emailLabel = new JLabel("Email");
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emailLabel.setBounds(10, 66, 180, 51);
		this.add(emailLabel);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(10, 155, 180, 51);
		this.add(usernameLabel);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setBounds(10, 244, 180, 51);
		this.add(passwordLabel);
		
		reenterPasswordLabel = new JLabel("Re-enter Password");
		reenterPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reenterPasswordLabel.setBounds(10, 339, 180, 51);
		this.add(reenterPasswordLabel);
		
		reenterPasswordField.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent e) {
		    	  if(e.getKeyChar()==KeyEvent.VK_ENTER){
		    		  registerButton.doClick();
	                }
		      }
		    });
		
		registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password = new String(passwordField.getPassword());
				String reenterPassword = new String(reenterPasswordField.getPassword());
				String commandString = "register;" + emailTextfield.getText() + ";" + usernameTextfield.getText() + ";" + password + ";" + reenterPassword;
				String replyString = gui.client.getNetworkAccess().sendString(commandString, true);
		    	if(replyString.equals("success"))
		    	{
		    		System.out.println("success recieved");
		    		String commandString2 = "login;" + usernameTextfield.getText() + ";" + password;
					String replyString2 = gui.client.getNetworkAccess().sendString(commandString2, true);
					if (replyString2.equals("success"))
					{
						gui.user = new User(usernameTextfield.getText(),password);
						gui.loggedInPanel();
						emailTextfield.setText("");
					}
					else
					{
						gui.loginPanel();
						emailTextfield.setText("INVAILD");
					}
		    	}
		    	else
				{
					emailTextfield.setText("INVAILD");
				}
				usernameTextfield.setText("");
				passwordField.setText("");
				reenterPasswordField.setText("");
			}
		});
		registerButton.setBounds(173, 430, 180, 41);
		this.add(registerButton);
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.loginPanel();
				emailTextfield.setText("");
				usernameTextfield.setText("");
				passwordField.setText("");
				reenterPasswordField.setText("");
			}
		});
		backButton.setBounds(67, 496, 155, 23);
		add(backButton);
		
		disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String commandString = "disconnect;";
				String replyString = gui.client.getNetworkAccess().sendString(commandString, false);
		    	gui.connectPanel();
			}
		});
		disconnectButton.setBounds(309, 496, 122, 23);
		this.add(disconnectButton);
	}
}
