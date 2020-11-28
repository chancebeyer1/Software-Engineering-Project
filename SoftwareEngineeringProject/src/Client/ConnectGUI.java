package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;

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

	public ConnectGUI(final ClientGUI gui)
	{
		setBounds(0, 0, 550, 600);
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

		ipArea.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
				{
					connectButton.doClick();
				}
			}
		});
		connectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				// String host = "127.0.0.1"
				String host = ipArea.getText();
				int port = 8000;
				// -- instantiate a Client object
				// the constructor will attempt to connect to the server

				try
				{
					gui.client = new Client(host, port);
				}
				catch(IOException e) { // this was sent up two levels to be addressed.
					// clear fields.
					//System.out.println(e.);
					System.out.println("Host " + host + " at port " + port + " is unavailable.");
					ipArea.setText("");
					return;
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					System.exit(1);
				}

				if (gui.client.getNetworkAccess() != null)
				{
					gui.loginPanel();
					ipArea.setText("");
				}
			}
		});
	}

}
