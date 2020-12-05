import java.awt.Dimension;

import javax.swing.JFrame;

public class SunViewer {
	public static final Dimension Sun_VIEWER_SIZE = new Dimension(750, 600);

	public static void main(String[] args) {
				JFrame frame = new JFrame();

				frame.setSize(Sun_VIEWER_SIZE);
				frame.setTitle("I see Suns!");

				frame.add(new SunComponent());

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
	}

}
