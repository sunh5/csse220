
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 * A component that contains balls.
 * 
 * Modify this class as you need
 * 
 * 
 */
public class RectComponent extends JComponent {

	private ArrayList<Rect> rects;
	private ArrayList<ContainerRect> containerRects;

	// This constructor initializes a single box
	public RectComponent() {
		rects = new ArrayList<>();
		containerRects = new ArrayList<ContainerRect>();

		Handlerclass handler = new Handlerclass();
		this.addMouseListener(handler);
	}

	// This method should draw anything considered to be part of the Component
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawString("Part 1", 30, 30);
		g2.drawString("Part 2", 30, 190);
		g2.drawString("Part 3 & 4", 30, 330);

		for (Rect b : rects) {
			b.draw(g2);
		}
		for (ContainerRect b : containerRects) {
			b.draw(g2);
		}
	}

	public void addRect(Rect b) {
		rects.add(b);

	}

	public class Handlerclass implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
//			System.out.println("enterrrrr");
			for (Rect rect : RectComponent.this.rects) {
				Point startPoint = rect.getPoint();	
				Point me = e.getPoint();
				Rectangle bounds = new Rectangle(startPoint,
						new Dimension(600, 600));
				if (bounds.contains(me)) {
					rect.setColor(Color.BLACK);
//					System.out.println("enterrrrr");
				}
				System.out.println();
				repaint();
			}
			
				Point startPoint = RectComponent.this.rects.get(2).getPoint();	
				Point me = e.getPoint();
				Rectangle bounds = new Rectangle(startPoint,
						new Dimension(600, 600));
				if (bounds.contains(me)) {
					RectComponent.this.rects.get(2).setColor(Color.PINK);
				}
				System.out.println();
				repaint();

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

}
