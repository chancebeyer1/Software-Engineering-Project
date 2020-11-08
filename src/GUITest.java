package GUITest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class GUITest extends JFrame {

	private Panel1 panel1 = new Panel1(this);
	private Panel2 panel2 = new Panel2(this);

	public GUITest() {
		this.setTitle("Client GI");

		// -- size of the frame: width, height
		setSize(100, 100);

		// -- center the frame on the screen
		setLocationRelativeTo(null);

		// -- shut down the entire application when the frame is closed
		// if you don't include this the application will continue to

		// run in the background and you'll have to kill it by pressing
		// the red square in eclipse
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// -- set the layout manager and add items
		// 5, 5 is the border around the edges of the areas
		setLayout(new FlowLayout());

		
		this.add(panel2);
		this.add(panel1);
		Flop();
		
		// -- show the frame on the screen
		setVisible(true);
	}
	
	public Dimension getPreferredSize() 
	{
		return new Dimension(100, 100);
	}
	
	private void Flip () {
		panel1.setVisible(false);
		panel2.setVisible(true);
	}
	
	private void Flop () {
		panel2.setVisible(false);
		panel1.setVisible(true);
	}
	
	public class Panel1 extends JPanel {
		
		private GUITest owner;
		
		public Panel1 (GUITest owner) {
			this.owner = owner;
			this.setLayout(new FlowLayout());
	        // -- set up buttons
            JButton flip = new JButton("Flip");
            flip.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent actionEvent) {
							owner.Flip();
						}
					}
				);
            this.add(flip);
		}
		
		public Dimension getPreferredSize() 
		{
			return new Dimension(100, 100);
		}

	}
	
	public class Panel2 extends JPanel {
		
		private GUITest owner;
		
		public Panel2 (GUITest owner) {
			this.owner = owner;
			this.setLayout(new FlowLayout());
	        // -- set up buttons
            JButton flop = new JButton("Flop");
            flop.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent actionEvent) {
							owner.Flop();
						}
					}
				);
            this.add(flop);
		}
		
		public Dimension getPreferredSize() 
		{
			return new Dimension(100, 100);
		}

	}
	
	public static void main (String[] args) {
		new GUITest();
	}

}
