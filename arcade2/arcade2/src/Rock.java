import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rock extends GameObject{
	private int tick;
	protected Line2D line1;
	protected Line2D line2;
	protected Line2D line3;
	protected Line2D line4;
	protected boolean isFalling;

	public Rock(int startx, int starty, GameWorld world) {
			super(startx, starty, world);
			this.imageName="images/Rock.png";
			this.tick=1;
			this.isFalling=false;
			/*
			 * Creating hitbox in line form
			 */
			this.line1=new Line2D.Double(this.x-8, this.y+19, this.x-8, this.y+21);
			this.line2=new Line2D.Double(this.x+48, this.y+19, this.x+48, this.y+21);
			this.line3=new Line2D.Double(this.x+19, this.y-8, this.x+21, this.y-8);
			this.line4=new Line2D.Double(this.x+19, this.y+48, this.x+21, this.y+48);

	}

	@Override
	public void drawOn(Graphics2D g2) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(this.imageName));
			g2.drawImage(img, this.x, this.y, 40, 40, null);
	}catch(IOException e){
	}
	}

	@Override
	public void collideWith(GameObject other) {
		other.collideWithRock(this);
	}

	@Override
	public void collideWithMonster(Monster monster) {
		monster.collideWithRock(this);
		
	}
	@Override
	public void collideWithHero(Hero hero) {
		hero.collideWithRock(this);
		
	}
	@Override
	public void collideWithFruit(Fruit fruit) {
		// Ignored
		
	}

	@Override
	public void updatePosition() {
		/*
		 * make the rock able to fall properly
		 */
		if(this.world.getHero().dugs.contains(new Rectangle2D.Double(this.x, this.y+48, 40, 40))){
			this.tick+=1;
		}
		if(this.tick==20){
			this.isFalling=true;
			this.y+=48;
		}
		if(this.tick>=20 && this.tick<30){
			if(this.world.getHero().dugs.contains(new Rectangle2D.Double(this.x, this.y+40, 40, 40))){
				this.y+=40;
			}
			if(!this.world.getHero().dugs.contains(new Rectangle2D.Double(this.x, this.y+40, 40, 40))){
				this.tick+=1;
			}
			
		}
		if(this.tick==30){
			this.world.objectsToRemove.add(this);
			this.world.player.addPoints(100);
			this.isFalling=false;
		}
		updateHitBox();
	}
}
