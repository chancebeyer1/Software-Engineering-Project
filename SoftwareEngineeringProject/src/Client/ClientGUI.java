package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ClientGUI extends JFrame
{

	//private User user;
	public Client client;
	public User user;
	private ConnectGUI connectGUI = new ConnectGUI(this);
	private LoginGUI loginGUI = new LoginGUI(this);
	private RegisterGUI registerGUI = new RegisterGUI(this);
	private ChangePasswordGUI changePasswordGUI = new ChangePasswordGUI(this);
	private LoggedInGUI loggedInGUI = new LoggedInGUI(this);

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
					final ClientGUI frame = new ClientGUI();
					frame.setVisible(true);
					
					frame.addWindowListener(new WindowAdapter() {
						  public void windowClosing(WindowEvent we) {
							  
							  String commandString;
							  if (frame.isLoggedIn())
							  {
								  commandString = "logout;" + frame.user.getUsername();
								  String replyString = frame.client.getNetworkAccess().sendString(commandString, true);
								  if (replyString.equals("success"))
								  {
									  commandString = "disconnect;";
									  frame.client.getNetworkAccess().sendString(commandString, false);
								  }
							  }
							  else
							  {
								  commandString = "disconnect;";
								  frame.client.getNetworkAccess().sendString(commandString, false);
							  }
							  
							  System.exit(0);
						  }
						});
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean isLoggedIn()
	{
		return changePasswordGUI.isVisible() || loggedInGUI.isVisible();
	}

	public void connectPanel()
	{
		connectGUI.setVisible(true);
		loginGUI.setVisible(false);
		registerGUI.setVisible(false);
		changePasswordGUI.setVisible(false);
		loggedInGUI.setVisible(false);
		
	}
	
	public void loginPanel()
	{
		connectGUI.setVisible(false);
		loginGUI.setVisible(true);
		registerGUI.setVisible(false);
		changePasswordGUI.setVisible(false);
		loggedInGUI.setVisible(false);
	}
	
	public void registerPanel()
	{
		connectGUI.setVisible(false);
		loginGUI.setVisible(false);
		registerGUI.setVisible(true);
		changePasswordGUI.setVisible(false);
		loggedInGUI.setVisible(false);
	}
	
	public void changePasswordPanel()
	{
		connectGUI.setVisible(false);
		loginGUI.setVisible(false);
		registerGUI.setVisible(false);
		changePasswordGUI.setVisible(true);
		loggedInGUI.setVisible(false);
	}
	
	public void loggedInPanel()
	{
		connectGUI.setVisible(false);
		loginGUI.setVisible(false);
		registerGUI.setVisible(false);
		changePasswordGUI.setVisible(false);
		loggedInGUI.setVisible(true);
	}
	
	/**
	 * Create the frame.
	 */
	public ClientGUI()
	{
		this.setTitle("Client ClientGUI");
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		setBounds(100, 100, 550, 600);
		
		setLocationRelativeTo(null);
		
		//setLayout(null);
		
		this.add(connectGUI);
		this.add(loginGUI);
		this.add(registerGUI);
		this.add(changePasswordGUI);
		this.add(loggedInGUI);
		
		connectPanel();
		
		
		
	}

}
