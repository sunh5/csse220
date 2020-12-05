import java.awt.Dimension;

import javax.swing.JFrame;

public class SceneViewer {
	public static final Dimension Scene_VIEWER_SIZE = new Dimension(750, 600);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();

		frame.setSize(Scene_VIEWER_SIZE);
		frame.setTitle("I see Scene!");

		frame.add(new SceneComponent());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
