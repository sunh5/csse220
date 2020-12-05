

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ButtonListener1 implements ActionListener {

	private int num;
	private int[] points;
	private JButton button;
	private JLabel label;
	
	public ButtonListener1(int i, int[] points, JButton button, JLabel label) {
		num = i;
		this.points = points;
		this.button = button;
		this.label = label;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		points[num]++;
		int sum = 1;
		for(int i = 0; i < 2; i++) {
			sum = sum*points[i];
		}
		points[2] = sum;
		System.out.println(points[2]);
		String text = Integer.toString(points[num]);
		String res = Integer.toString(sum);
		button.setText(text);
		label.setText("="+res);
//		if(sum < points[2]) {
//			points[num]++;
//			button.setText(Integer.toString(points[num]));
//		}

	}

}
