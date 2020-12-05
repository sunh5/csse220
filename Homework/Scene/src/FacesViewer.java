import java.awt.Dimension;
import javax.swing.JFrame;


public class FacesViewer {
	public static final Dimension Face_VIEWER_SIZE = new Dimension(750, 600);

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		frame.setSize(Face_VIEWER_SIZE);
		frame.setTitle("I see Faces!");

		frame.add(new FacesComponent());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
