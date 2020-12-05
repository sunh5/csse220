import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

public class Monster extends GameObject{
	protected String monsterType;
	protected boolean isGhost = false;
	protected int pumpedTimes = 0;
	private int direction;
	protected int numtick;
	/*
	 * 1 = up, 2 = right, 3 = left, 4 = down
	 */
	private int previousD;
	private int ghostCount;
	private int ox;
	private int oy;
	protected boolean faceLeft;
	
	public Monster(int x, int y, GameWorld world) {
		super(x, y, world);
		this.ox = x;
		this.oy = y;
		this.isGhost = false;
		this.monsterType=this.getClass().getName();
		double rand = Math.random();
		if(rand<.25){
			this.direction = 1;
		}else if(rand<.5){
			this.direction = 2;
		}else if(rand<.75){
			this.direction = 3;
		}else{
			this.direction = 4;
		}
		this.numtick = 0;
	}
	public void repos(){
		this.x = this.ox;
		this.y = this.oy;
		this.isGhost = false;
		double rand = Math.random();
		if(rand<.25){
			this.direction = 1;
		}else if(rand<.5){
			this.direction = 2;
		}else if(rand<.75){
			this.direction = 3;
		}else{
			this.direction = 4;
		}
		this.numtick = 0;
	}
	@Override
	public void drawOn(Graphics2D g2) {
		String fileName = null;
		if (this.isGhost) {
			fileName = "images/" + "Ghost.png";
		} 
	    else {
	    	if(this.faceLeft){
	    		fileName = "images/" + this.monsterType + ".png";
	    	}else{
	    		fileName = "images/" + this.monsterType +"-Flipped"+ ".png";
	    	}
	    	
		}

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileName));
			if(!this.monsterType.equals("Fygarfire") && !this.monsterType.equals("Pikalightning")){
				g2.drawImage(img, this.x, this.y, 40, 40, null);
			}else{
		    	if(this.faceLeft){
		    		g2.drawImage(img, this.x-80, this.y, 120, 40, null);
		    	}else{
		    		g2.drawImage(img, this.x, this.y, 120, 40, null);
		    	}
			}
		} catch (IOException e) {
			System.out.println("Error in Drawing Monster");
		}
		}
	  

	public void pumped() { 
		this.x = 0;
		this.y = 0;
		if (this.pumpedTimes != 3) {
			this.pumpedTimes += 1;
		} else if (this.pumpedTimes == 3) {
			if(this.getType().equals("Fygars")){
				this.world.player.addPoints(300);
			}
			else if(this.getType().equals("Pookas")){
				this.world.player.addPoints(200);
			}
		}
	}
	
	@Override
	public void collideWith(GameObject other) {
		other.collideWithMonster(this);
		
	}
	
	@Override
	public void collideWithMonster(Monster monster) {
		// Ignored
		
	}
	@Override
	public void collideWithHero(Hero hero) {
//		if(this.hitBox.intersects(hero.hitBox)){
//			hero.loseLife();
//			this.world.die();
//		}
		
	}
	@Override
	public void collideWithFruit(Fruit fruit) {
		// Ignored
		
	}
	@Override
	public void updatePosition() {
		if(this.pumpedTimes==0){
			this.updateHitBox();
		if(!this.isGhost){
			normalMove();
		}
		else{
			ghostMove();
		}
		}
	}
	@SuppressWarnings("boxing")
	protected void normalMove() {
		int upMovable = 0;
		int downMovable = 0;
		int rightMovable = 0;
		int leftMovable = 0;
		ArrayList<Integer> movable = new ArrayList<>(
				Arrays.asList(upMovable, rightMovable, leftMovable, downMovable));

		for (Rectangle2D box : this.world.getHero().dugs){
			if(box.getX() == this.x && box.getY()==this.y-8){
				upMovable++;
				movable.set(0, upMovable);

			}
			if(box.getX() == this.x && box.getY() == this.y+8){
				downMovable++;
				movable.set(3, downMovable);

			}
			if(box.getX() == this.x-8 && box.getY() == this.y){
				leftMovable++;
				movable.set(2, leftMovable);

			}
			if(box.getX() == this.x+8 && box.getY() == this.y){
				rightMovable++;
				movable.set(1, rightMovable);

			}
		}
		
		ArrayList<Integer> directions = new ArrayList<>();
		int i = 0;
		while(i<movable.size()){
			if(movable.get(i) != 0){
				directions.add(i+1);
			}
			i++;
		}
		if(directions.size()!=0){
			if(directions.size()>2&&this.forwardMovable(upMovable, rightMovable, leftMovable, downMovable)){
				Random random = new Random();
				this.previousD = this.direction;
				while(previousD==direction){
					this.direction = directions.get(random.nextInt(directions.size()));					
				}
				switch(this.direction){
				case 1:this.y-=8;
					break;
				case 2:this.x+=8; 				this.faceLeft = false;
					break;
				case 3:this.x-=8; 				this.faceLeft = true;
					break;
				case 4:this.y+=8;
					break;
				default:
					System.out.println("Invalid Direction3:" + this.direction);
				}
			}
			else if(!this.forwardMove(upMovable, rightMovable, leftMovable,downMovable)){
				if(directions.size()!=1){
					Random random = new Random();
					this.previousD = this.direction;
					while(previousD==direction||5-previousD==direction){

						this.direction = directions.get(random.nextInt(directions.size()));					
					}
				}else{
					this.direction = directions.get(0);
					this.ghostCount += 1;
					if(this.ghostCount == 3){
						this.ghostCount = 0;
						this.isGhost = true;
						this.previousD = 0;
						return;
					}
				}

				switch(this.direction){
				case 1:this.y-=8;
					break;
				case 2:this.x+=8; 				this.faceLeft = false;
					break;
				case 3:this.x-=8; 				this.faceLeft = true;
					break;
				case 4:this.y+=8;
					break;
				default:
					System.out.println("Invalid Direction2:" + this.direction);
				}
				
				
			}
		}
		
	}
	
	private boolean forwardMovable(int upMovable, int rightMovable, int leftMovable, int downMovable) {
		int tracker = 0;
		switch(this.direction){
		case 1:
			if (upMovable>0){
				tracker++;
			}
			break;
		case 2:
			if(rightMovable>0){
				tracker++;
			}
			break;
		case 3:
			if(leftMovable>0){
				tracker++;
			}
			break;
		case 4:
			if(downMovable>0){
				tracker++;
			}
			break;
		default:
			System.out.println("Invalid direction" + this.direction);
		}
		return tracker != 0;
	}
	private boolean forwardMove(int upMovable, int rightMovable, int leftMovable, int downMovable){
		int tracker = 0;
		switch(this.direction){
		case 1:
			if (upMovable>0){
				this.y-=8;
				tracker++;
			}
			break;
		case 2:
			if(rightMovable>0){
				this.x+=8;
				tracker++;
				this.faceLeft = false;
			}
			break;
		case 3:
			if(leftMovable>0){
				this.x-=8;
				tracker++;
				this.faceLeft = true;
			}
			break;
		case 4:
			if(downMovable>0){
				this.y+=8;
				tracker++;
			}
			break;
		default:
			System.out.println("Invalid direction" + this.direction);
		}
		return tracker != 0;
	}
	protected void ghostMove() {
		int HeroX = this.world.getHero().x;
		int HeroY = this.world.getHero().y;
		
		if(HeroX<this.x){
			this.x-=4;
		}else if(HeroX>this.x){
			this.x+=4;
		}
		
		if(HeroY<this.y){
			this.y-=4;
		}else if(HeroY>this.y){
			this.y+=4;
		}
		
		for (Rectangle2D box : this.world.getHero().dugs){
			if(box.getX() == this.x&& box.getY() == this.y){
				this.isGhost = false;
			}
		}
	}

}
