package linearLightsOut;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

import ballStrikeCounter.Tracker;

public class AddListener implements ActionListener {
	private JButton[] buttons;
	private JButton newGame;
	private JButton quit;
	private JFrame frame;
	private int nbuttons;

	public AddListener(JButton[] buttons, JButton newGame, JButton quit, JFrame myFrame, int nButtons) {
		this.buttons = buttons;
		this.newGame = newGame;
		this.quit = quit;
		this.frame = myFrame;
		this.nbuttons = nButtons;
	}

	public static String setButtons() {
		Random var = new Random();
		int n = var.nextInt(2) + 0;
		if (n == 0) {
			return "X";
		}
		return "O";
	}

	public static void changeButtons(JButton button) {
		if (button.getText().equals("X")) {
			button.setText("O");
		} else {
			button.setText("X");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(this.newGame)) {
			for (JButton but : this.buttons) {
				String str = setButtons();
				but.setText(str);
				this.frame.setTitle("Linear Light Out");
			}
		}

		if (e.getSource().equals(this.quit)) {
			this.frame.setVisible(false);
			this.frame.dispose();
		}
		for (int i = 0; i < this.buttons.length; i++) {
			if (e.getSource().equals(this.buttons[i])) {
				changeButtons(this.buttons[i]);
				if (i < this.buttons.length - 1) {
					changeButtons(this.buttons[i + 1]);
				}
				if (i != 0) {
					changeButtons(this.buttons[i - 1]);
				}
			}
		}
		int sum = 0;
		for (int i = 0; i < this.buttons.length; i++) {
			if (this.buttons[i].getText() == "O") {
				sum++;
			}
			if (sum == this.buttons.length)
				this.frame.setTitle("You Win!!");
		}
		sum = 0;
		for (int i = 0; i < this.buttons.length; i++) {
			if (this.buttons[i].getText() == "X") {
				sum++;
			}
			if (sum == this.buttons.length)
				this.frame.setTitle("You Win!!");
		}
	}
}
