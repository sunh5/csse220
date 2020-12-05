import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;

public abstract class Options {
	ArrayList<String> options = new ArrayList<String>();;
	ActionListener resultListener;
	JLabel label;
	
	public void addOption(String option) {
		options.add(option);
	}
	public void setActionListener(ActionListener resultListener) {
		this.resultListener = resultListener; 
	}
	public String getChoice() {
		return label.getText().substring(16);
	}
	public abstract void showWindow();
//	protected abstract void addOption(String option);
	
	
}
