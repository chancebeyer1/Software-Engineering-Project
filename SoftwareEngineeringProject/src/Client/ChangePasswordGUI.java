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
    private Client client;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JTextField textField_2;

	/**
	 * Create the frame.
	 */
	public ChangePasswordGUI(GUI gui) //final Client client, final JFrame f
	{
		this.client = client;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Change Password");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(126, 11, 183, 26);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(167, 61, 183, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(167, 104, 183, 32);
		contentPane.add(textField_1);
		
		
		lblNewLabel_1 = new JLabel("Current Password");
		lblNewLabel_1.setBounds(36, 70, 129, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("New Password");
		lblNewLabel_2.setBounds(36, 113, 111, 14);
		contentPane.add(lblNewLabel_2);
		
		btnNewButton = new JButton("Change Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(145, 206, 141, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String commandString;
		    	commandString = "disconnect";
		    	client.getNetworkAccess().sendString(commandString, false);
			}
		});
		btnNewButton_2.setBounds(10, 33, 155, 23);
		contentPane.add(btnNewButton_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(167, 147, 183, 32);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Re-enter New Password");
		lblNewLabel_2_1.setBounds(36, 156, 142, 14);
		contentPane.add(lblNewLabel_2_1);
	}
}
