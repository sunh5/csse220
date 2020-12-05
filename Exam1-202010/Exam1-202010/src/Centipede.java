import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Centipede {

	private static final int SEGMENT_DIAMETER = 40;
	private static final int DEFAULT_HEAD_X = 30;
	private static final int DEFAULT_HEAD_Y = 30;
	private static final int EYE_WIDTH = 12;
	private static final int EYE_HEIGHT = 8;
	private static final int NOSE_DIAMETER = 14;
	
	private int x = 0;
	private int y = 0;
	private int numDown = 0;
	private int numRight = 0;
	private ArrayList<Color> colors;
	private Color newColor;
	private int indexOfC = 0;
	private double degrees;
	private HashMap<Integer,Color> colorAndIndex = new HashMap<Integer,Color>();
	// add whatever new fields, methods, constructors you need to
	public Centipede() {
		
	}
	public Centipede(int x, int y, int numDown, int numRight) {
		this.x = x;
		this.y = y;
		this.numDown = numDown;
		this.numRight = numRight;
		this.newColor = Color.green;
		this.indexOfC = 0;
		this.degrees = 0;
	}

	
	public void drawOn(Graphics2D g) {

		// modify this code however you need to
		
		int centerX = DEFAULT_HEAD_X;
		int centerY = DEFAULT_HEAD_Y;
		g.setColor(Color.green);

		g.fillOval(centerX - SEGMENT_DIAMETER / 2, centerY - SEGMENT_DIAMETER / 2, SEGMENT_DIAMETER, SEGMENT_DIAMETER);
		for (int i = 1; i < 4; i++) {
			g.fillOval(centerX - SEGMENT_DIAMETER / 2, centerY - SEGMENT_DIAMETER / 2+SEGMENT_DIAMETER*i, SEGMENT_DIAMETER, SEGMENT_DIAMETER);
		}
		for (int i = 1; i < 4; i++) {
			g.fillOval(centerX - SEGMENT_DIAMETER / 2+SEGMENT_DIAMETER*i, centerY - SEGMENT_DIAMETER / 2+SEGMENT_DIAMETER*3, SEGMENT_DIAMETER, SEGMENT_DIAMETER);
		}
		g.setColor(Color.red);
		g.fillOval(centerX - NOSE_DIAMETER / 2, centerY - 23, NOSE_DIAMETER, NOSE_DIAMETER);
		g.setColor(Color.black);
		g.fillOval(centerX - EYE_WIDTH / 2 - 8, centerY - 15, EYE_WIDTH, EYE_HEIGHT);
		g.fillOval(centerX - EYE_WIDTH / 2 + 8, centerY - 15, EYE_WIDTH, EYE_HEIGHT);
		
		//Part2

		g.setColor(Color.green);

//		g.fillOval(centerX - SEGMENT_DIAMETER / 2, centerY - SEGMENT_DIAMETER / 2, SEGMENT_DIAMETER, SEGMENT_DIAMETER);
		for (int i = 0; i < this.numDown; i++) {
			for (Integer key : colorAndIndex.keySet()) {
				if ((key-1) == i) {
					g.setColor(colorAndIndex.get(i+1));
					g.fillOval(this.x - SEGMENT_DIAMETER / 2, this.y - SEGMENT_DIAMETER / 2+SEGMENT_DIAMETER*i, SEGMENT_DIAMETER, SEGMENT_DIAMETER);
				}
			}
			g.fillOval(this.x - SEGMENT_DIAMETER / 2, this.y - SEGMENT_DIAMETER / 2+SEGMENT_DIAMETER*i, SEGMENT_DIAMETER, SEGMENT_DIAMETER);
		}
		g.translate(0,this.numDown*this.SEGMENT_DIAMETER);
		//g.setColor(Color.green);
		g.rotate(Math.toDegrees(this.degrees));
		
		for (int i = 0; i < this.numRight; i++) {
			
			for (Integer key : colorAndIndex.keySet()) {
				if ((key-this.numDown+1) == i) {
					g.setColor(colorAndIndex.get(i+this.numDown));					
					g.fillOval(this.x - SEGMENT_DIAMETER / 2+SEGMENT_DIAMETER*i, this.y - SEGMENT_DIAMETER / 2+SEGMENT_DIAMETER*(this.numDown-1), SEGMENT_DIAMETER, SEGMENT_DIAMETER);
				}
			}
			g.fillOval(this.x - SEGMENT_DIAMETER / 2+SEGMENT_DIAMETER*i, this.y - SEGMENT_DIAMETER / 2+SEGMENT_DIAMETER*(this.numDown-1), SEGMENT_DIAMETER, SEGMENT_DIAMETER);

		}

//		g.rotate(-Math.toDegrees(this.degrees));
		g.setColor(Color.red);
		g.fillOval(this.x - NOSE_DIAMETER / 2, this.y - 23, NOSE_DIAMETER, NOSE_DIAMETER);
		g.setColor(Color.black);
		g.fillOval(this.x - EYE_WIDTH / 2 - 8, this.y - 15, EYE_WIDTH, EYE_HEIGHT);
		g.fillOval(this.x - EYE_WIDTH / 2 + 8, this.y - 15, EYE_WIDTH, EYE_HEIGHT);
		
		
		//Part3
		
	}
	public void setColor(Color color, int i) {
		this.newColor = color;
		this.indexOfC = i;
		this.colorAndIndex.put(this.indexOfC, this.newColor);
	}
	public void setRotation(double drgrees) {
		this.degrees = degrees;
	}
}
