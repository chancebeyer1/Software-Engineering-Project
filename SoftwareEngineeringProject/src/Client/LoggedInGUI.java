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
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.SystemColor;

public class LoggedInGUI extends JPanel
{
	private JLabel welcomeLabel;
	private JButton changePassButton;
	private JButton logoutButton;
	private JButton disconnectButton;

	/**
	 * Create the frame.
	 */
	public LoggedInGUI(final GUI gui) //final Client client, final JFrame f
	{

		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		welcomeLabel = new JLabel("Welcome Chance!");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(108, 11, 183, 26);
		add(welcomeLabel);
		
		changePassButton = new JButton("Change Password");
		changePassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.changePasswordPanel();
			}
		});
		changePassButton.setBounds(36, 183, 141, 23);
		add(changePassButton);
		
		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String commandString = "logout;";
				String replyString = gui.client.getNetworkAccess().sendString(commandString, true);
		    	if(replyString == "success")
		    	{
		    		gui.loginPanel();
		    	}
			}
		});
		logoutButton.setBounds(228, 183, 155, 23);
		add(logoutButton);
		
		JLabel lblNewLabel_2_1 = new JLabel("Content from Application Database");
		lblNewLabel_2_1.setBounds(119, 95, 244, 14);
		add(lblNewLabel_2_1);
		
		disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String commandString = "logout;";
				String replyString = gui.client.getNetworkAccess().sendString(commandString, true);
		    	if(replyString == "success")
		    	{
		    		commandString = "disconnect;";
			    	gui.client.getNetworkAccess().sendString(commandString, false);
			    	gui.connectPanel();
		    	}
			}
		});
		disconnectButton.setBounds(126, 227, 155, 23);
		add(disconnectButton);
	}
}
