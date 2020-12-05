
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * The main which creates the window.
 * 
 * Modify this class as you need.
 * 
 */
public class RectMain {

	public static final int DELAY=10;
	
	public static void main(String[] args) {
		new RectMain();
	}

	public RectMain() {
		JFrame frame = new JFrame("Final Question");
		RectComponent component = new RectComponent();
		component.setPreferredSize(new Dimension(600, 600));
		
		frame.add(component, BorderLayout.CENTER);
		
		/* Part 1 Code */
		
		component.addRect(new Rect(40,40));
		component.addRect(new Rect(150,40));

		/* Part 2 Code*/
		
		component.addRect(new RedRect(40,200));
		component.addRect(new CircleRect(150,200));
		
		
		/* Part 3 Code */
		ContainerRect r = new ContainerRect(new RedRect(40,350));
		r.addChild(new Rect(150,350));
		r.addChild(new CircleRect(260,350));
//		component.addRect(r);
//
//		ContainerRect r2 = new ContainerRect(new CircleRect(400,350));
//		r2.addChild(new CircleRect(430,475));
//		component.addRect(r2);
		
		
		/* Part 4 Code
		
		r.setComponent(component);
		r2.setComponent(component);
		
		*/
		component.addRect(new Rect(140,340));
		component.addRect(new RedRect(150,350));
		component.addRect(new CircleRect(260,350));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
