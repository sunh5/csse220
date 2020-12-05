package ballStrikeCounter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AddListener implements ActionListener {
	private JButton ballButton, strikeButton;
	private Tracker tracker;
	private int score, strike;

	public AddListener(JButton ballButton, JButton strikeButton, Tracker tracker) {
		this.ballButton = ballButton;
		this.strikeButton = strikeButton;
		this.tracker = tracker;
//		labelToAddTo = textOutput;
//		textToAdd = string;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == ballButton) {
			this.score++;
			if (score == 4) {
				score = 0;
				strike = 0;
			}
		}

		if (e.getSource() == strikeButton) {
			this.strike++;
			if (strike == 3) {
				score = 0;
				strike = 0;
			}
		}

		tracker.updateLabel(score, strike);
	}

}
