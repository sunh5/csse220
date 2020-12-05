import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class ByoButton extends ByoLabel {

	private String content;
	private Color color;
	private ArrayList<ByoClickListener> listeners;

	public ByoButton(String str, Color color) {
		super(str);

		this.color = color;
		this.listeners = new ArrayList<ByoClickListener>();
	}

	public void drawOn(Graphics2D g2) {
		g2.setColor(color);
		g2.fillRect(getX(), getY(), getWidth(), getHeight()+5);
		g2.setColor(Color.black);
		super.drawOn(g2);
		g2.drawRect(getX(), getY(), getWidth(), getHeight()+5);
	}

	public void setBackground(Color color) {
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void acceptClick(Point p) {
		listeners.get(0).clickPerformed(p);
	}

	public void addClickListener(ByoGUIViewer.ButtonChangeClickListener buttonChangeClickListener) {
		// TODO Auto-generated method stub
		this.listeners.add(buttonChangeClickListener);
	}

}
