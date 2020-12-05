

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class OptionsListDialog extends Options{

	ArrayList<String> options;
	JLabel label;
	ActionListener resultListener;
	
	public OptionsListDialog() {
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
		
		
		JList list = new JList(super.options.toArray());
		list.setBackground(Color.LIGHT_GRAY);
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				OptionsListDialog.super.label.setText("Current choice: " +  options.get(list.getSelectedIndex()));
				
			}
		});
		
		frame.add(list);
		
		frame.pack();
		frame.setVisible(true);
	}
	
}
