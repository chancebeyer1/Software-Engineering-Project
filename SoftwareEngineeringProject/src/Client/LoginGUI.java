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
    private Client client;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;


	public LoginGUI(GUI gui) //final Client client, final JFrame f
	{
		this.client = client;
		setBounds(100, 100, 550, 600);

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(126, 11, 183, 26);
		this.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(126, 61, 183, 32);
		this.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(126, 122, 183, 32);
		this.add(textField_1);
		
		
		lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(36, 70, 61, 14);
		this.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(36, 131, 61, 14);
		this.add(lblNewLabel_2);
		
		btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(171, 165, 89, 23);
		this.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Register New User");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            //RegisterGUI reg = new RegisterGUI(client, f);
	            //reg.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(244, 199, 166, 23);
		this.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Disconnect");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String commandString;
		    	commandString = "disconnect";
		    	client.getNetworkAccess().sendString(commandString, false);
		    	//f.setState(Frame.NORMAL);
			}
		});
		btnNewButton_2.setBounds(31, 199, 155, 23);
		this.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Recover Password");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_3.setBounds(137, 233, 155, 23);
		this.add(btnNewButton_3);
	}
}
