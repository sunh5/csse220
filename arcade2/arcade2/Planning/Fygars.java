import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Fygars extends Monster{
//	private int numtick;
	private int attacktick;
	private int spec;

	public Fygars(int startx, int starty, GameWorld world) {
		super(startx,starty, world);
//		this.numtick = 0;
		this.attacktick = 0;
		this.spec = new Random().nextInt(200)+100;
//		this.imageName="images/Fygars.png";
	}
	
	@Override
	public void updatePosition(){

		if(this.spec == this.numtick&!this.isGhost){
			shootFire();
		}else if(this.spec == this.numtick&this.isGhost){
			this.spec = new Random().nextInt(100);
			this.numtick = 0;
		}
		else{
			this.monsterType = "Fygars";
			this.numtick++;
			super.updatePosition();
		}
		
	}



	private void shootFire() {
		// TODO Auto-generated method stub.
		this.attacktick++;
		if(this.attacktick>20&&this.attacktick<=60){
			this.monsterType = "Fygarfire";
			if(this.faceLeft){
				this.hitBox = new Rectangle2D.Double(this.x-80, this.y, 120, 40);
			}else{
				this.hitBox = new Rectangle2D.Double(this.x, this.y, 120, 40);
				}
		}else if(this.attacktick>60){
			this.numtick = 0;
			this.attacktick = 0;
			this.spec = new Random().nextInt(200)+100;
			this.monsterType = "Fygars";
		}
	}

}
