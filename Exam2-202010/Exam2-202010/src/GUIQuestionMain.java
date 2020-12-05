import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIQuestionMain {

	public GUIQuestionMain() {
	}

	public void displayWindow() {

		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 300, 130);
		JPanel panel = new JPanel();
		JButton bottom = new JButton("Reset to 1");
		JLabel label = new JLabel("x");
		JLabel label2 = new JLabel(" = 1");
		int[] points = new int[3];
		points[0] = 1;
		points[1] = 1;
		points[2] = 1;
		
		//note: if you need to convert a int to a string, you can do it like this
		//String someString = Integer.toString(someInt);
		//if you need to convert a string to an int, you can do it like this
		//int x = Integer.parseInt("2");
		
		ArrayList<JButton> buttons = new ArrayList<>();
		for(int i = 0; i < 2; i++) {
			JButton button = new JButton("1");
			ButtonListener1 b = new ButtonListener1(i, points, button,label2);
			button.addActionListener(b);
			buttons.add(button);
			panel.add(button);
			panel.add(label);
		}
		
		panel.add(label2);
		ButtonListener2 c = new ButtonListener2(points, label2, buttons);
		bottom.addActionListener(c);
//		bottom.addActionListener(new ButtonListener2(points, label2, buttons));
//		frame.add(label, BorderLayout.CENTER);
//		frame.add(label2, BorderLayout.EAST);
		frame.add(bottom, BorderLayout.SOUTH);
		frame.add(panel);
		
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		GUIQuestionMain r = new GUIQuestionMain();
		r.displayWindow();
	}
}
