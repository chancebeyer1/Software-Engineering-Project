package Client;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class ClientGUI1
{

	private JFrame frame;
	private Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ClientGUI1 window = new ClientGUI1();
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientGUI1()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton connectButton = new JButton("Connect");
		connectButton.setBounds(163, 169, 89, 23);
		frame.getContentPane().add(connectButton);

		final JTextField ipArea = new JTextField();
		ipArea.setBounds(174, 61, 203, 23);
		frame.getContentPane().add(ipArea);
		
		JLabel lblNewLabel = new JLabel("IP address:");
		lblNewLabel.setBounds(65, 61, 95, 23);
		frame.getContentPane().add(lblNewLabel);

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
					frame.setState(Frame.ICONIFIED);
		            //LoginGUI login = new LoginGUI(client, frame);
		            //login.setVisible(true);
				}
			}
		});
	}
}
