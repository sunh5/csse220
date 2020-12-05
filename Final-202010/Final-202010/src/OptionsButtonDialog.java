

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionsButtonDialog extends Options{

	ArrayList<String> options;
	JLabel label;
	ActionListener resultListener;
	
	public OptionsButtonDialog() {
		super();
//		options = new ArrayList<String>();
	}
	
//	public void addOption(String option) {
//		options.add(option);
//	}
//	
//	public void setActionListener(ActionListener resultListener) {
//		this.resultListener = resultListener; 
//	}
//	
//	public String getChoice() {
//		return label.getText().substring(16);
//	}
	@Override
	public void showWindow() {
		JFrame frame = new JFrame("Choose Wisely!");
		super.label = new JLabel("Current choice: " + super.options.get(0));
		JButton ok = new JButton("Ok");
		ok.addActionListener(super.resultListener);
		
		frame.add(super.label, BorderLayout.NORTH);
		frame.add(ok, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();

		for(String s : super.options) {
			JButton b = new JButton(s);
			b.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					OptionsButtonDialog.super.label.setText("Current choice: " + s);
				}
			});
			panel.add(b);
		}
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
}
