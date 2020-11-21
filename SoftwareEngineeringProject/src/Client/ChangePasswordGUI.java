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

public class ChangePasswordGUI extends JPanel
{
	private JPasswordField currentPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField reenterPasswordField;
	private JLabel currentPassLabel;
	private JLabel newPassLabel;
	private JLabel changePassLabel;
	private JLabel reenterPassLabel;
	private JButton changePassButton;
	private JButton backButton;
	

	/**
	 * Create the frame.
	 */
	public ChangePasswordGUI(final ClientGUI gui) //final Client client, final JFrame f
	{
		setBounds(0, 0, 550, 600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		changePassLabel = new JLabel("Change Password (You will be redirected if correct entries entered)");
		changePassLabel.setHorizontalAlignment(SwingConstants.CENTER);
		changePassLabel.setBounds(10, 11, 446, 26);
		add(changePassLabel);
		
		currentPasswordField = new JPasswordField();
		currentPasswordField.setBounds(252, 61, 183, 32);
		add(currentPasswordField);
		currentPasswordField.setColumns(10);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setColumns(10);
		newPasswordField.setBounds(252, 104, 183, 32);
		add(newPasswordField);
		
		
		currentPassLabel = new JLabel("Current Password");
		currentPassLabel.setBounds(49, 70, 129, 14);
		add(currentPassLabel);
		
		newPassLabel = new JLabel("New Password");
		newPassLabel.setBounds(49, 113, 111, 14);
		add(newPassLabel);
		
//		reenterPasswordField.addKeyListener(new KeyAdapter() {
//		      public void keyReleased(KeyEvent e) {
//		    	  if(e.getKeyChar()==KeyEvent.VK_ENTER){
//		    		  changePassButton.doClick();
//	                }
//		      }
//		    });
		
		changePassButton = new JButton("Change Password");
		changePassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentPassword = new String(currentPasswordField.getPassword());
				String newPassword = new String(newPasswordField.getPassword());
				String reenterPassword = new String(reenterPasswordField.getPassword());
				String commandString = "changePassword;" + gui.user.getUsername() + ";" + currentPassword + ";" + newPassword + ";" + reenterPassword;
				String replyString = gui.client.getNetworkAccess().sendString(commandString, true);
		    	if(replyString.equals("success"))
		    	{
		    		gui.loggedInPanel();
		    		currentPasswordField.setText("");
		    		newPasswordField.setText("");
		    		reenterPasswordField.setText("");
		    	}
		    	else
		    	{
		    		currentPasswordField.setText("");
		    		newPasswordField.setText("");
		    		reenterPasswordField.setText("");
		    	}
			}
		});
		changePassButton.setBounds(294, 204, 155, 23);
		add(changePassButton);
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.loggedInPanel();
				currentPasswordField.setText("");
	    		newPasswordField.setText("");
	    		reenterPasswordField.setText("");
			}
		});
		backButton.setBounds(49, 204, 155, 23);
		add(backButton);
		
		reenterPasswordField = new JPasswordField();
		reenterPasswordField.setColumns(10);
		reenterPasswordField.setBounds(252, 147, 183, 32);
		add(reenterPasswordField);
		
		reenterPassLabel = new JLabel("Re-enter New Password");
		reenterPassLabel.setBounds(49, 156, 142, 14);
		add(reenterPassLabel);
	}
}
