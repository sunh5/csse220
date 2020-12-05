import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;


public class Egg extends Element {
	private static final int MAX_TIME = 2000;
	private GameWorld world;
	private int time = 0;
	private int width;
	private Color color;
	private Boolean outOfTime;
	private Monster monster;
	
	
	public Egg(GameWorld world, Point2D drawPoint, Monster m) {
		super(world, drawPoint);
		this.world = world;
		this.width = 20;
		this.color = Color.pink;
		this.outOfTime = false;
		this.monster = m;
	}
	
	public Monster getMonster() {
		return this.monster;
	}
	
	public boolean isOutTime() {
		return this.outOfTime;
	}
	
	public void resetMonster() {
		double dy = this.getDrawPoint().getY()- this.monster.getWidth();
		double dx = this.getDrawPoint().getX();
		this.monster.setDrawPoint(new Point2D.Double(dx,dy));
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public void updatePosition() {
//		if (!this.isOutTime()) {
				double dy = this.getDrawPoint().getY()+1;
				Point2D point = new Point2D.Double(this.getDrawPoint().getX(), dy);
				this.moveAfterCheck(point);
				this.time ++;
//		}	
		if (this.time == MAX_TIME) {
			this.outOfTime =true;
		}

	}

	@Override
	public void updateSize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateColor() {
		// TODO Auto-generated method stub

	}
	
	public void moveAfterCheck(Point2D point) {
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.width, this.width);
		if (this.isIntersect(rect, this.world.getWalls()) == false && this.isWithinBoundary(rect) == true) {
			this.setDrawPoint(point);
		}
	}

}
