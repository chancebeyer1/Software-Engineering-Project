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

	private JTextField ipArea;
	private JButton connectButton;
	private JLabel ipLabel;

	public ConnectGUI(final GUI gui)
	{
		setBounds(100, 100, 550, 600);
		this.setLayout(null);
		
		connectButton = new JButton("Connect");
		connectButton.setBounds(163, 169, 89, 23);
		this.add(connectButton);

		ipArea = new JTextField();
		ipArea.setBounds(174, 61, 203, 23);
		this.add(ipArea);
		
		ipLabel = new JLabel("IP address:");
		ipLabel.setBounds(65, 61, 95, 23);
		this.add(ipLabel);

		connectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				//String host = "127.0.0.1"
				String host = ipArea.getText();
				int port = 8000;
				// -- instantiate a Client object
				// the constructor will attempt to connect to the server
				
				gui.client = new Client(host, port);
				if (gui.client.getNetworkAccess() != null)
				{
		            gui.loginPanel();
				}
			}
		});
	}
	

}
