

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class OptionsMain {

	public static void main(String[] args) {
		
		String[] options = {"Groucho", "Chico", "Harpo", "Zeppo"};
		Options[] abOption;
		
		
		String sResult = JOptionPane.showInputDialog("What type of dialog would you like (enter a number 1-3)");
		
		//let's not worry about error handling and assume we alway get a valid number
		int result = Integer.parseInt(sResult);
		
//		OptionsMain main = new OptionsMain{new option[] {new OptionsButtonDialog(), new OptionsListDialog(),new OptionsRadioButtonDialog()};
		 abOption = new Options[] {new OptionsButtonDialog(), new OptionsListDialog(),new OptionsRadioButtonDialog()};
		 
		 
			for(String option : options) {
				abOption[result].addOption(option);
			}
		 	abOption[result].setActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("The user chose " + abOption[result].getChoice());
					System.exit(0);
					
				}
			});
		 	abOption[result].showWindow();
		
		
		
		
//		if(result == 1) {
//			OptionsButtonDialog dialog = new OptionsButtonDialog();
//			for(String option : options) {
//				dialog.addOption(option);
//			}
//			dialog.setActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					System.out.println("The user chose " + dialog.getChoice());
//					System.exit(0);
//					
//				}
//			});
//			dialog.showWindow();			
//		}
//		
//		if(result == 2) {
//			OptionsListDialog dialog = new OptionsListDialog();
//			for(String option : options) {
//				dialog.addOption(option);
//			}
//			dialog.setAction3Listener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					System.out.println("The user chose " + dialog.getChoice());
//					System.exit(0);
//					
//				}
//			});
//			dialog.showWindow();			
//		}
//		
//		if(result == 3) {
//			OptionsRadioButtonDialog dialog = new OptionsRadioButtonDialog();
//			for(String option : options) {
//				dialog.addOption(option);
//			}
//			dialog.setActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					System.out.println("The user chose " + dialog.getChoice());
//					System.exit(0);
//					
//				}
//			});
//			dialog.showWindow();			
//		}
		
	}
	
}
