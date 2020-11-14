package Server;

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

public class ServerGUI extends JFrame {

    private Server server;
	
    private int WIDTH = 350;
    private int HEIGHT = 256;

	private Timer animationTimer = null;	
	
	private ControlPanelInner controlPanel;
	
	public ServerGUI ()
	{

		setTitle("Server GUI");
				
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

		
		// -- Timer will generate an event every 10mSec once it is started
		//    First parameter is the delay in mSec, second is the ActionListener
		animationTimer = new Timer(10,
				// -- ActionListener for the timer event
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("tic");
					}
				}
		);


        
		// -- show the frame on the screen
		setVisible(true);
	

		
	}
	

	// -- Inner class for control panel
	public class ControlPanelInner extends JPanel {

        private JButton startserverbutton = new JButton("Start server");
        private JButton getconnectionsbutton = new JButton("Get Connections");
        private JTextField connectionsfield = new JTextField(5);        
		
		public ControlPanelInner ()
		{
			// -- add buttons to panel layout manager
			setLayout(new FlowLayout());
						
	        this.add(startserverbutton);
	        this.add(getconnectionsbutton);
	        this.add(connectionsfield);
	 
	        // -- set up buttons
            startserverbutton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent actionEvent) {
		                	server = new Server();
		                	Thread serverthread = new Thread(server);
		                	serverthread.start();
						}
					}
				);
            
            getconnectionsbutton.addActionListener(
 					new ActionListener() {
 						public void actionPerformed(ActionEvent actionEvent) {
 		            		int p = server.getconnections();
 		            		connectionsfield.setText(p + "");	    													
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
		new ServerGUI();
	}
	
}
