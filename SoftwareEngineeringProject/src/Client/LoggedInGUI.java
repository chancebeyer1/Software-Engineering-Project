package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

public class LoggedInGUI extends JPanel
{
	private JLabel welcomeLabel;
	private JButton changePassButton;
	private JButton logoutButton;
	private JButton disconnectButton;
	private JButton btnNewButton;

	private static final String[] COL_NAMES =
			{"Rank", "Country", "Score", "GDP", "Social support", "Life Expectancy"};

	/**
	 * Create the frame.
	 */
	public LoggedInGUI(final ClientGUI gui) // final Client client, final JFrame f
	{

		setBounds(0, 0, 550, 600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		welcomeLabel = new JLabel("Welcome!");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(178, 11, 183, 26);
		add(welcomeLabel);

		changePassButton = new JButton("Change Password");
		changePassButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gui.changePasswordPanel();
			}
		});
		changePassButton.setBounds(74, 377, 141, 23);
		add(changePassButton);

		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String commandString = "logout;" + gui.user.getUsername();
				String replyString = gui.client.getNetworkAccess().sendString(commandString, true);
				if (replyString.equals("success"))
				{
					gui.loginPanel();
				}
			}
		});
		logoutButton.setBounds(296, 377, 155, 23);
		add(logoutButton);

		// create table with data
		final JTable table = new JTable(new Object[1][6],COL_NAMES);

		JScrollPane pane = new JScrollPane(table);
		pane.setSize(493, 259);
		pane.setLocation(27, 48);
		// add the table to the frame
		this.add(pane);

		disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String commandString = "logout;" + gui.user.getUsername();
				String replyString = gui.client.getNetworkAccess().sendString(commandString, true);
				if (replyString.equals("success"))
				{
					commandString = "disconnect;";
					gui.client.getNetworkAccess().sendString(commandString, false);
					gui.connectPanel();
				}
			}
		});
		disconnectButton.setBounds(178, 430, 155, 23);
		add(disconnectButton);

		btnNewButton = new JButton("Populate Table");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String commandString = "application;";
				String[] row = gui.client
						.getNetworkAccess()
						.sendString(commandString,true) // reply string from command
						.split(";");

				Object[][] data = new Object[row.length][6];
				// map the split cells to the table.
				for (int i = 0; i < row.length; i++)
				{
					String[] cells = row[i].split(",");
					System.arraycopy(cells,0,data[i],0,cells.length);
				}

				table.setModel(new DefaultTableModel(data, COL_NAMES));

				table.getColumnModel().getColumn(0).setPreferredWidth(64);
				table.getColumnModel().getColumn(2).setPreferredWidth(45);
				table.getColumnModel().getColumn(3).setPreferredWidth(65);
				table.getColumnModel().getColumn(4).setPreferredWidth(84);
				table.getColumnModel().getColumn(5).setPreferredWidth(93);
				table.setFont(new Font("Tahoma", Font.PLAIN, 9));

			}
		});
		btnNewButton.setBounds(178, 324, 149, 26);
		add(btnNewButton);
	}
}
