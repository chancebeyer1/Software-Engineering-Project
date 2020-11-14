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

public class RegisterGUI extends JPanel
{
	private JTextField emailTestfield;
	private JTextField usernameTestfield;
	private JTextField passwordTestfield;
	private JTextField reenterPassTestfield;
	private JLabel registrationLabel;
	private JLabel emailLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel reenterPasswordLabel;
	private JButton registerButton;
	private JButton disconnectButton;


	/**
	 * Create the frame.
	 */
	public RegisterGUI(final GUI gui) //final Client client, final JFrame f
	{
		setBounds(100, 100, 550, 600);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		registrationLabel = new JLabel("Registration");
		registrationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registrationLabel.setBounds(0, 11, 534, 24);
		this.add(registrationLabel);
		
		emailTestfield = new JTextField();
		emailTestfield.setBounds(208, 66, 267, 51);
		this.add(emailTestfield);
		emailTestfield.setColumns(10);
		
		usernameTestfield = new JTextField();
		usernameTestfield.setColumns(10);
		usernameTestfield.setBounds(208, 155, 267, 51);
		this.add(usernameTestfield);
		
		passwordTestfield = new JTextField();
		passwordTestfield.setColumns(10);
		passwordTestfield.setBounds(208, 244, 267, 51);
		this.add(passwordTestfield);
		
		reenterPassTestfield = new JTextField();
		reenterPassTestfield.setColumns(10);
		reenterPassTestfield.setBounds(208, 339, 267, 51);
		this.add(reenterPassTestfield);
		
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
		
		registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String commandString = "register;" + emailTestfield.getText() + ";" + usernameTestfield.getText() + ";" + passwordTestfield.getText() + ";" + reenterPassTestfield.getText();
				String replyString = gui.client.getNetworkAccess().sendString(commandString, true);
		    	if(replyString == "success")
		    	{
		    		commandString = "login;" + usernameTestfield.getText() + ";" + passwordTestfield.getText();
		    		replyString = gui.client.getNetworkAccess().sendString(commandString, true);
		    		gui.loggedInPanel();
		    	}
		    	
			}
		});
		registerButton.setBounds(155, 430, 180, 41);
		this.add(registerButton);
		
		disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String commandString = "disconnect;";
				String replyString = gui.client.getNetworkAccess().sendString(commandString, false);
		    	gui.connectPanel();
			}
		});
		disconnectButton.setBounds(185, 507, 122, 23);
		this.add(disconnectButton);
	}

}
