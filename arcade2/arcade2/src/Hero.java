import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Hero extends GameObject{

	protected boolean isPumping;
	protected int lifeCount = 3;
	protected ArrayList<Rectangle2D> dugs;
	public char direction = 'y';
	public boolean up, down, left, right = false;
	protected Rectangle2D.Double pump;
	private int ox;
	private int oy;
	protected BufferedImage ball;
	private int hitRock;
	protected boolean hittingRock;

	public Hero(int startx, int starty, GameWorld world) {
		super(startx, starty, world);
		this.x = startx;
		this.y = starty;
		this.ox=this.x;
		this.oy=this.y;
		this.hitRock=0;
		this.isPumping = false;
		this.dugs=new ArrayList<>();
		this.imageName="images/hero-left.png";
		this.dugs.add(new Rectangle2D.Double(this.x, this.y, 40, 40));
		this.hittingRock=false;
	}

	@Override
	public void drawOn(Graphics2D g) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(this.imageName));
			g.drawImage(img, this.x, this.y, 40, 40, null);
		} catch (IOException e) {
		}
	}

	private void keepMoving() {
		// TODO Auto-generated method stub.
		if(this.direction == 'w'){
			this.y-=8;
			if(this.digThrough()!=null){
				this.dugs.add(this.digThrough());
			}
		}
		if(this.direction == 'a') 
		{this.x -=8;
		if(this.digThrough()!=null){
			this.dugs.add(this.digThrough());
		}
		}
		if(this.direction == 'd') {this.x += 8;
		if(this.digThrough()!=null){
			this.dugs.add(this.digThrough());
		}
		}
		if(this.direction == 's') {this.y += 8;
		if(this.digThrough()!=null){
			this.dugs.add(this.digThrough());
		}
		}
		}
	
	
	public void moveLeft() {
		if(this.left && this.hitRock!=2 && !this.isPumping){
			System.out.println("left");
		if (this.y%48!=736%48){
			keepMoving();
		}else{
		
		if (x > 24) {
			this.x -= 8;
		}
		if(this.digThrough()!=null){
			this.dugs.add(this.digThrough());
		}
		this.imageName="images/hero-left.png";
		this.direction = 'a';
		}}
	}

	

	public void moveRight() {
		if(this.right&& this.hitRock!=4 && !this.isPumping){
		if (this.y%48!=736%48){
			keepMoving();
		}else{
		if (this.x <= 736) {
			this.x += 8;

		}
		if(this.digThrough()!=null){
			this.dugs.add(this.digThrough());
		}
		this.imageName="images/hero-right.png";
		this.direction = 'd';
		}}
	}

	public void moveDown() {
		if(this.down && this.hitRock!=3 && !this.isPumping){
		if (this.x%48!=24){
			keepMoving();
		}else{
		if (this.y <= 728) {
			this.y += 8;
		}
		if(this.digThrough()!=null){
			this.dugs.add(this.digThrough());
		}
		if(this.imageName.equals("images/hero-left.png")||this.imageName.equals("images/hero-downfromleft.png")||this.imageName.equals("images/hero-upfromright.png")){
			this.imageName="images/hero-downfromleft.png";
		}else{
			this.imageName="images/hero-down.png";
		}
		this.direction = 's';
		}}
	}

	public void moveUp() {
		if(this.up && this.hitRock!=1 && !this.isPumping){
		if (this.x%48!=24){
			keepMoving();
		}else{
		if (this.y > 0) {
			this.y -= 8;
		}
		if(this.digThrough()!=null){
			this.dugs.add(this.digThrough());
			
		}
		if(this.imageName.equals("images/hero-right.png")||this.imageName.equals("images/hero-downfromleft.png")||this.imageName.equals("images/hero-upfromright.png")){
			this.imageName="images/hero-upfromright.png";
		}else{
			this.imageName="images/hero-up.png";
		}
		this.direction = 'w';
		}}
	}

	/**
	 * when the hero dies
	 */
	public void loseLife() {
		this.lifeCount-=1;
	}
	
	public void drawPump(Graphics2D g2){
		if(this.world.currentLevel<4){
			if(this.isPumping){
				if(this.pump==null){
					this.ox=this.x+20;
					this.oy=this.y+20;
				}
				this.pump=new Rectangle2D.Double(this.ox, this.oy, 16, 16);
				Line2D line=new Line2D.Double(this.x,this.y,this.ox,this.oy);
				g2.setColor(Color.RED);
				g2.fill(this.pump);
				g2.fill(line);
		}
		}
		if(this.world.currentLevel>=4){
			if(this.isPumping){
			try {
				if(this.ball==null){
					this.ox=this.x;
					this.oy=this.y;
				}
				this.ball = ImageIO.read(new File("images/Ball.png"));
				this.pump=new Rectangle2D.Double(this.ox, this.oy, 40, 40);
				g2.drawImage(this.ball, this.ox, this.oy, 40, 40, null);
			} catch (IOException e) {
			} 
			}
		}
	}
	
	public void updatePump() {
		if(this.isPumping){
			if(this.direction=='w'){
				this.oy-=8;
		}
		else if(this.direction=='s'){
			this.oy+=8;
		}
		else if(this.direction=='a'){
			this.ox-=8;
		}else if(this.direction=='d'){
			this.ox+=8;
		}
		if(this.ox>this.x+140 || this.ox<this.x-140 || this.oy>this.y+140 || this.oy<this.y-140){
			this.isPumping=false;
			this.pump=null;
			this.ball=null;
		}
		}
}

	public Rectangle2D digThrough(){
		Rectangle2D dug=new Rectangle2D.Double(this.x, this.y, 40, 40);
		if(!this.dugs.contains(dug)){
			this.world.player.addPoints(5);
			return dug;
		}
		return null;
	}

	@Override
	public void collideWith(GameObject other) {
		other.collideWithHero(this);
	}
	
	@Override
	public void collideWithRock(Rock rock){
		if(!rock.isFalling){
			if(this.hittingRock){
				return;
			}
			if(this.hitBox.intersectsLine(rock.line1)){
				this.hitRock=4;
				this.hittingRock=true;
			}
			else if(this.hitBox.intersectsLine(rock.line2)){
				this.hitRock=2;
				this.hittingRock=true;
				System.out.println(this.hitRock);
			}
			else if(this.hitBox.intersectsLine(rock.line3)){
				this.hitRock=3;
				this.hittingRock=true;
			}
			else if(this.hitBox.intersectsLine(rock.line4)){
				this.hitRock=1;
				this.hittingRock=true;
			}
//			else{
//				this.hitRock=0;
//			}
//			if(!this.hittingRock){
				if(!this.hitBox.intersects(rock.hitBox)){
					this.hitRock=0;
					System.out.println(this.hitRock);
			}
//			}
			
		}
		if(rock.isFalling && this.hitBox.intersects(rock.hitBox)){
			this.loseLife();
			this.world.die();
		}
	}

	@Override
	public void collideWithMonster(Monster monster) {
		if(this.isPumping){
			if(this.pump!=null){
			if(this.pump.intersects(monster.hitBox)){
				monster.pumpedTimes+=1;
				if(monster.pumpedTimes==3 || this.world.currentLevel>=4){
					this.world.objectsToRemove.add(monster);
					this.world.player.monsternum-=1;
					this.world.player.addPoints(500);
				}
				if(this.world.currentLevel<4){
					this.ox=this.x+20;
					this.oy=this.y+20;
				}
				if(this.world.currentLevel>=4){
					this.ox=this.x;
					this.oy=this.y;
				}
				this.isPumping=false;
				this.pump=null;
				this.ball=null;
			}
		}
		}
		
		
	}

	@Override
	public void collideWithHero(Hero hero) {
		// Ignored
		
	}
	@Override
	public void collideWithFruit(Fruit fruit) {
		// Ignored
		
	}

	@Override
	public void updatePosition() {
		moveUp();
		moveDown();
		moveRight();
		moveLeft();
		updateHitBox();
		updatePump();
	}
}
