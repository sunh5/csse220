import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Fygars extends Monster{
	/*
	 * Construct fygars
	 */
	private int attacktick;
	private int spec;

	public Fygars(int startx, int starty, GameWorld world) {
		super(startx,starty, world);
		this.attacktick = 0;
		this.spec = new Random().nextInt(200)+100;
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
			if(this.world.currentLevel<4){
				this.monsterType = "Fygars";
			}
			if(this.world.currentLevel>=4){
				this.monsterType="Pika";
			}
			this.numtick++;
			super.updatePosition();
		}
		
	}



	private void shootFire() {
		/*
		 * Make fygars shootfire randomly,
		 * and face the fire correctly
		 */
		this.attacktick++;
		if(this.attacktick>20&&this.attacktick<=40){
			if(this.world.currentLevel<4){
				this.monsterType = "Fygarfire";
			}
			if(this.world.currentLevel>=4){
				this.monsterType="Pikalightning";
			}
			
			if(this.faceLeft){
				this.hitBox = new Rectangle2D.Double(this.x-80, this.y, 120, 40);
			}else{
				this.hitBox = new Rectangle2D.Double(this.x, this.y, 120, 40);
				}
		}else if(this.attacktick>40){
			this.numtick = 0;
			this.attacktick = 0;
			this.spec = new Random().nextInt(200)+100;
			if(this.world.currentLevel<4){
				this.monsterType = "Fygars";
			}
			if(this.world.currentLevel>=4){
				this.monsterType="Pika";
			}
		}
	}

}
