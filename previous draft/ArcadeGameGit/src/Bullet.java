import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;

/**
 * This class defines how bullet moves from hero.
 * 
 * @author Jingwen Wu & Haoxuan Sun
 *
 */
public class Bullet extends Element {

	private Color color;
	private double dx;
	private final static double RANGE = 400.0;
	private int distance;
	private double width;
	private boolean outOfRange;
	private GameWorld world;
	private int direction; // 0 means left, 1 means right
	private int id; // 0 means hero's bullet, 1 means monster's bullet

	public Bullet(GameWorld world, Point2D drawPoint, int d, int i) {
		super(world, drawPoint);
		this.world = world;
		this.distance = 0;
		this.dx = 1;
		this.width = 20.0;
		this.color = Color.gray;
		this.outOfRange = false;
		this.direction = d;
		this.id = i;
	}

	public boolean isOutRange() {
		return this.outOfRange;
	}

	public int getId() {
		return this.id;
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
		if (this.distance <= RANGE) {
//			this.setDrawPoint(new Point2D.Double(this.getDrawPoint().getX() + this.dx, this.getDrawPoint().getY()));
			Point2D.Double checkPoint = null;
			if (this.direction == 1) {
				checkPoint = new Point2D.Double(this.getDrawPoint().getX() + this.dx,
						this.getDrawPoint().getY());
			}
			if (this.direction == 0) {
				checkPoint = new Point2D.Double(this.getDrawPoint().getX() - this.dx,
						this.getDrawPoint().getY());
			}
			if (this.withinWall(checkPoint) == false) {
				this.setDrawPoint(checkPoint);
			} else {
				this.outOfRange = true;
			}
			this.distance += this.dx;
		} else {
			this.outOfRange = true;
		}

	}
	
	public boolean withinWall(Point2D point) {
		Rectangle2D.Double rect = new Rectangle2D.Double(point.getX(), point.getY(), this.width, this.width);
		if (this.isIntersect(rect, this.world.getWalls()) == true ||this.isWithinBoundary(rect) == false)
			return true;
		return false;
	}
	
	@Override
	public void updateSize() {

	}

	@Override
	public void updateColor() {
		// TODO Auto-generated method stub

	}

}
