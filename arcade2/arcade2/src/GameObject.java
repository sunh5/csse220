import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public abstract class GameObject {
	protected int x;
	protected int y;
	protected String imageName;
	protected Rectangle2D.Double hitBox;
	protected GameWorld world;

	public GameObject(int startx, int starty, GameWorld world) {
		this.x = startx;
		this.y = starty;
		this.hitBox=new Rectangle2D.Double(this.x, this.y, 40, 40);
		this.world=world;
	}
	
	public String getType(){
		return "";
	}
	
	public abstract void drawOn(Graphics2D g2);

	public abstract void updatePosition();

	public void erase(){
		
	}

	public abstract void collideWith(GameObject other);
	
	public void collideWithRock(Rock rock){
		if(rock.isFalling){
		if(this.hitBox.intersects(rock.hitBox)){
			this.world.objectsToRemove.add(this);
			this.world.player.addPoints(500);
			this.world.player.monsternum-=1;
		}
		}
	}
	public abstract void collideWithMonster(Monster monster);
	public abstract void collideWithHero(Hero hero);
	public abstract void collideWithFruit(Fruit fruit);
	
	public void updateHitBox(){
		this.hitBox=new Rectangle2D.Double(this.x, this.y, 40, 40);
	}

}
