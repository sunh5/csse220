import java.awt.Dimension;

import javax.swing.JFrame;

public class PineTreesViewer {
	public static final Dimension PineTrees_VIEWER_SIZE = new Dimension(500, 400);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();

		frame.setSize(PineTrees_VIEWER_SIZE);
		frame.setTitle("I see PineTrees!");

		frame.add(new PineTreesComponent());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	

}
