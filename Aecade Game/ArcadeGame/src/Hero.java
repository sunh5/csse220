import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Hero extends Element {
	
	private Color color;
	private double width;
	private GameWorld world;
	private double dx;
	private double dy;
	private Point2D point;
	
	public Hero(GameWorld world, Point2D drawPoint) {
		super(world, drawPoint);
		this.world = world;
		this.color = Color.blue;
		this.width = 50.0;
	}

	@Override
	public void timePassed() {
		updatePosition();
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub
		return false;
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
//	@Override
//	public double getWidth() {
//		// TODO Auto-generated method stub
//		return this.width;
//	}

	@Override
	public void updatePosition() {
		if(this.world.isMovingLeft == true) {
			this.moveLeft();
		}
		if(this.world.isMovingRight == true) {
			this.moveRight();
		}
		if(this.world.isFlying == true) {
			this.fly();
		}
		if(this.world.isFalling == true) {
			this.fall();
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
	
	public void moveLeft() {

		double dx=this.getDrawPoint().getX()-1;
		Point2D point  = new Point2D.Double(dx,this.getDrawPoint().getY());
		this.setDrawPoint(point);
	}
	
	public void moveRight() {

		double dx=this.getDrawPoint().getX()+1;
		Point2D point  = new Point2D.Double(dx,this.getDrawPoint().getY());
		this.setDrawPoint(point);
	}
	
	public void fly() {
		double dy=this.getDrawPoint().getY()-1;
		Point2D point  = new Point2D.Double(this.getDrawPoint().getX(),dy);
		this.setDrawPoint(point);
	}
	
	public void fall() {
		double dy=this.getDrawPoint().getY()+1;
		Point2D point  = new Point2D.Double(this.getDrawPoint().getX(),dy);
		this.setDrawPoint(point);
	}
}

