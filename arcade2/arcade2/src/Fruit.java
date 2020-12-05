
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Some cherries that give the player a boost in points
 */
public class Fruit extends GameObject{
	/*
	 * Fruit class
	 * draw fruit of two different types
	 */
	private int tick;
	public Fruit(int startx, int starty, GameWorld world, String type) {
		super(startx, starty, world);
		this.x=startx;
		this.y=starty;
		if(type.equals("fruit")){
			this.imageName="images/Fruit.png";
		}else{
			this.imageName="images/Ball.png";
		}
		this.tick=0;
	}

	/**
	 * draws the cherries on the screen
	 */
	@Override
	public void drawOn(Graphics2D g2) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(this.imageName));
			g2.drawImage(img, this.x, this.y, 40, 40, null);
		} catch (IOException e) {
			System.out.println("error drawing fruit");
		}
	}

	@Override
	public void updatePosition() {
		this.tick+=1;
		if(this.tick==200){
			this.world.objectsToRemove.add(this);
		}
	}

	@Override
	public void collideWith(GameObject other) {
		other.collideWithFruit(this);
		
	}

	@Override
	public void collideWithMonster(Monster monster) {
		// Ignored
		
	}

	@Override
	public void collideWithHero(Hero hero) {
		/*
		 * Add points after collide
		 */
		if(this.hitBox.intersects(hero.hitBox)){
			this.world.player.addPoints(500);
			this.world.objectsToRemove.add(this);
		}
	}

	@Override
	public void collideWithFruit(Fruit fruit) {
		// Ignored
		
	}

}
