package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientGUI extends JFrame {

	private Client client;
	
    private int WIDTH = 700;
    private int HEIGHT = 1000;
		
	private ControlPanelInner controlPanel;
	
	public ClientGUI ()
	{
		
		

		setTitle("Client GI");
				
		// -- size of the frame: width, height
		setSize(WIDTH, HEIGHT);
		
		// -- center the frame on the screen
		setLocationRelativeTo(null);
		
		// -- shut down the entire application when the frame is closed
		//    if you don't include this the application will continue to
		
		//    run in the background and you'll have to kill it by pressing
		//    the red square in eclipse
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// -- set the layout manager and add items
		//    5, 5 is the border around the edges of the areas
		setLayout(new BorderLayout(5, 5));

		controlPanel = new ControlPanelInner();
		this.add(controlPanel, BorderLayout.CENTER);

		
		// -- show the frame on the screen
		setVisible(true);
			
	}
	

	
	// -- Inner class for control panel
	public class ControlPanelInner extends JPanel {

        private JButton messagebutton = new JButton("Message");
        private JButton connectbutton = new JButton("Connect");
        private JButton disconnectbutton = new JButton("Disconnect");
        
        private JTextField messagefield = new JTextField(25);        
        private JTextField connectfield = new JTextField(25);      
        private JTextArea responsearea = new JTextArea(50, 200);
        
		
		public ControlPanelInner ()
		{
			// -- add buttons to panel layout manager
			setLayout(new FlowLayout());
						
	        this.add(connectbutton);
	        this.add(messagebutton);
	        this.add(disconnectbutton);
	        this.add(new JLabel("Message"));
	        this.add(messagefield);
	        this.add(connectfield);
	        this.add(new JLabel("Responses"));
	 
			// Create Scrolling Text Area in Swing
	        responsearea = new JTextArea("", 5, 25);
	        responsearea.setLineWrap(true);
			JScrollPane sbrText = new JScrollPane(responsearea);
			sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       			
			this.add(sbrText);
			
	        // -- set up buttons
            connectbutton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent actionEvent) {
		            		String host = "127.0.0.1";
		            		int port = 8000;
		            		// -- instantiate a Client object
		            		//    the constructor will attempt to connect to the server
		            		client = new Client(host, port);
						}
					}
				);
            
            messagebutton.addActionListener(
 					new ActionListener() {
 						public void actionPerformed(ActionEvent actionEvent) {
 				       		// -- send message to server and receive reply.
 				    		String commandString = messagefield.getText();
 				    		String replyString;
 				    		
 				    		// -- send a String to the server and wait for the response
 			    			replyString = client.getNetworkAccess().sendString(commandString, true);
 				    		responsearea.append(replyString + "\n");
 						}
 					}
 				);

            disconnectbutton.addActionListener(
  					new ActionListener() {
  						public void actionPerformed(ActionEvent actionEvent) {
  				       		// -- send message to server and receive reply.
  				    		String commandString;
  				    		String replyString;
  				    		  				    			
  				    		// -- send a String to the server and wait for the response
  				    		commandString = "disconnect";
  				    		replyString = client.getNetworkAccess().sendString(commandString, false);
  				    		
  						}
  					}
  				);

 			
		}

		
		public Dimension getPreferredSize() 
		{
			return new Dimension(100, 500);
		}

	}
	
	public static void main (String[] args)
	{
		new ClientGUI();
	}

	
}
