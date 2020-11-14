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

public class ChangePasswordGUI extends JPanel
{
	private JTextField currentPassTextField;
	private JTextField newPassTextField;
	private JTextField reenterPassTextField;
	private JLabel currentPassLabel;
	private JLabel newPassLabel;
	private JLabel changePassLabel;
	private JLabel reenterPassLabel;
	private JButton changePassButton;
	private JButton backButton;
	

	/**
	 * Create the frame.
	 */
	public ChangePasswordGUI(final GUI gui) //final Client client, final JFrame f
	{
		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		changePassLabel = new JLabel("Change Password");
		changePassLabel.setHorizontalAlignment(SwingConstants.CENTER);
		changePassLabel.setBounds(126, 11, 183, 26);
		add(changePassLabel);
		
		currentPassTextField = new JTextField();
		currentPassTextField.setBounds(167, 61, 183, 32);
		add(currentPassTextField);
		currentPassTextField.setColumns(10);
		
		newPassTextField = new JTextField();
		newPassTextField.setColumns(10);
		newPassTextField.setBounds(167, 104, 183, 32);
		add(newPassTextField);
		
		
		currentPassLabel = new JLabel("Current Password");
		currentPassLabel.setBounds(36, 70, 129, 14);
		add(currentPassLabel);
		
		newPassLabel = new JLabel("New Password");
		newPassLabel.setBounds(36, 113, 111, 14);
		add(newPassLabel);
		
		changePassButton = new JButton("Change Password");
		changePassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String commandString = "changePassword;" + currentPassTextField.getText() + ";" + newPassTextField.getText() + ";" + reenterPassTextField.getText();
				String replyString = gui.client.getNetworkAccess().sendString(commandString, true);
		    	if(replyString == "success")
		    	{
		    		gui.loggedInPanel();
		    	}
		    	//else statement
			}
		});
		changePassButton.setBounds(145, 206, 141, 23);
		add(changePassButton);
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.loggedInPanel();
			}
		});
		backButton.setBounds(10, 33, 155, 23);
		add(backButton);
		
		reenterPassTextField = new JTextField();
		reenterPassTextField.setColumns(10);
		reenterPassTextField.setBounds(167, 147, 183, 32);
		add(reenterPassTextField);
		
		reenterPassLabel = new JLabel("Re-enter New Password");
		reenterPassLabel.setBounds(36, 156, 142, 14);
		add(reenterPassLabel);
	}
}
