package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ConnectGUI extends JPanel
{

	private Client client;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ConnectGUI(final GUI gui)
	{
		setBounds(100, 100, 550, 600);
		this.setLayout(null);
		
		JButton connectButton = new JButton("Connect");
		connectButton.setBounds(163, 169, 89, 23);
		this.add(connectButton);

		final JTextField ipArea = new JTextField();
		ipArea.setBounds(174, 61, 203, 23);
		this.add(ipArea);
		
		JLabel lblNewLabel = new JLabel("IP address:");
		lblNewLabel.setBounds(65, 61, 95, 23);
		this.add(lblNewLabel);

		connectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				//String host = "127.0.0.1"
				String host = ipArea.getText();
				int port = 8000;
				// -- instantiate a Client object
				// the constructor will attempt to connect to the server
				client = new Client(host, port);
				if (client.getNetworkAccess() != null)
				{
		            gui.loginPanel();
				}
			}
		});
	}
	

}
