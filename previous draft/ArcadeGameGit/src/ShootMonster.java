import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ShootMonster extends Monster {
	private GameWorld world;
	private int recharge;
	private final static int RECHARGE_TIME = 300;
	private int freezeTime;
	
	public ShootMonster(GameWorld world, Point2D drawPoint) {
		super(world, drawPoint);
		this.world = world;
		this.recharge = 0;
		this.freezeTime = 0;
	}
	
	
	@Override
	public void timePassed() {
		updatePosition();
		ramdomShoot();
	}
	
	public void ramdomShoot() {
	Point2D.Double target = this.world.getHeroLocation();
	Rectangle2D.Double range = new Rectangle2D.Double(target.getX() - 200, target.getY() - 200, 400, 400);
	if (range.contains(this.getDrawPoint())) {
		if (this.freezeTime == 0) {
			if (this.recharge == 0) {
				if (target.getX() > this.getDrawPoint().getX()) {
					this.world.addBullets(
							new Point2D.Double(this.getDrawPoint().getX() +45 , this.getDrawPoint().getY() + 15),
							1,1);
					System.out.println("shoot right");
				} else {
					this.world.addBullets(
							new Point2D.Double(this.getDrawPoint().getX() - 10-40, this.getDrawPoint().getY() + 15),
							0,1);
					System.out.println("shoot left");
				}
				this.recharge = RECHARGE_TIME;
			}
			this.recharge -= 1;
		} else {
			this.recharge = RECHARGE_TIME;
		}
	}
}
}
