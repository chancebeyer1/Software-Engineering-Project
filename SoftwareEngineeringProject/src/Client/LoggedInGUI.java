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

public class LoggedInGUI extends JPanel
{
    private Client client;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JButton btnNewButton_1;

	/**
	 * Create the frame.
	 */
	public LoggedInGUI(GUI gui) //final Client client, final JFrame f
	{
		this.client = client;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome Chance!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(126, 11, 183, 26);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("Change Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(36, 183, 141, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_2 = new JButton("Logout");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String commandString;
		    	commandString = "disconnect";
		    	client.getNetworkAccess().sendString(commandString, false);
			}
		});
		btnNewButton_2.setBounds(228, 183, 155, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Content from Application Database");
		lblNewLabel_2_1.setBounds(126, 95, 244, 14);
		contentPane.add(lblNewLabel_2_1);
		
		btnNewButton_1 = new JButton("Disconnect");
		btnNewButton_1.setBounds(126, 227, 155, 23);
		contentPane.add(btnNewButton_1);
	}
}
