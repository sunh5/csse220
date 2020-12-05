

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class OptionsRadioButtonDialog extends Options{

	ArrayList<String> options;
	JLabel label;
	ActionListener resultListener;
	
	public OptionsRadioButtonDialog() {
		super();
//		options = new ArrayList<String>();
	}

//	@Override
//	public void setActionListener(ActionListener resultListener) {
//		this.resultListener = resultListener; 
//	}
//	
//	@Override
//	public String getChoice() {
//		return label.getText().substring(16);
//	}
	@Override
	public void showWindow(){ 
		JFrame frame = new JFrame("Choose Wisely!");
		
		super.label = new JLabel("Current choice: " + super.options.get(0));
		JButton ok = new JButton("Ok");
		ok.addActionListener(super.resultListener);		
		frame.add(super.label, BorderLayout.NORTH);
		frame.add(ok, BorderLayout.SOUTH);
		
		
		JPanel panel = new JPanel();
		ButtonGroup group = new ButtonGroup();
		for(String s : super.options) {
			JRadioButton b = new JRadioButton(s);
			b.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					OptionsRadioButtonDialog.super.label.setText("Current choice: " + s);
				}
			});
			group.add(b);
			panel.add(b);
		}
	
		
		frame.add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}
	
}
