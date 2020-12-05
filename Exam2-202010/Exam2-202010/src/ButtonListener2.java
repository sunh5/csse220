
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ButtonListener2 implements ActionListener {

//	private int num;
	private int[] points;
	private ArrayList<JButton> button;
	private JLabel label;
	
	public  ButtonListener2(int[] points, JLabel label, ArrayList<JButton> buttons) {

		this.points = points;
		this.button = buttons;
		this.label = label;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		for (int i = 0; i < 2; i++) {
			points[i] =1;
			String text = Integer.toString(1);
			button.get(i).setText(text);
		}
		label.setText(" = 1");
		
//		int sum = 0;
//		for(int i = 0; i < 2; i++) {
//			sum *= points[i];
//		}
//		System.out.println(points[2]);
//		String text = Integer.toString(sum);
	
//		if(sum < points[2]) {
//			points[num]++;
//			button.setText(Integer.toString(points[num]));
//		}

	}

}