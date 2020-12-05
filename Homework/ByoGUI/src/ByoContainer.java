import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public abstract class ByoContainer extends ByoComponent {
	ArrayList<ByoComponent> childs = new ArrayList<ByoComponent>();

//	public abstract void computeChildPositions(ByoComponent child);

	public abstract int getPreferredWidth();

	public abstract int getPreferredHeight();

	public abstract void computeChildPositions();

	public void addChild(ByoComponent child) {
		this.childs.add(child);
//		this.computeChildPositions();
	}

//	public void setPosition(int x, int y, int width, int height) {
//		this.x = x;
//		this.y = y;
//		this.width = width;
//		this.height = height;
//	}
	@Override
	public void acceptClick(Point p) {
		for (ByoComponent child : childs) {
			if (child.containsPoint(p)) {
				child.acceptClick(p);
			}
		}
	}

	@Override
	public void drawOn(Graphics2D g2) {
		computeChildPositions();
		for (int i = 0; i < this.childs.size(); i++) {
			childs.get(i).drawOn(g2);
		}
		g2.setColor(Color.GREEN);
		g2.drawRect(this.getX(), this.getY(), this.getPreferredWidth(), this.getPreferredHeight()+6);
		g2.setColor(Color.BLACK);
	}

}
