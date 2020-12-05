import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ArcadeButton extends Element {
	private  GameWorld world;
//	private GameWorld world;
	private Point2D point;
	private int buttonWidth = 300;
	private int buttonHeight = 50;
	private Image image;
	private String imageName;
	private int imageHeight;
	private int imageWidth;
	private int startTime = 1;
	private Point mousePoint;
	
	
	public ArcadeButton(GameWorld gameWorld, Point2D drawPoint) {
		super(gameWorld,drawPoint);
		this.world = gameWorld;
		
		
//		PointerInfo a = MouseInfo.getPointerInfo();
//		this.mousePoint = a.getLocation();
//		int x = (int) this.mousePoint.getX();
//		int y = (int) this.mousePoint.getY();
//		System.out.println("y");
	}
	
	public void ArcadeImage(String filename) {
		try {
		    this.image = ImageIO.read(new File(filename));
		} catch (IOException e) {
			throw new RuntimeException("Could not load image file " + filename);
		}
		BufferedImage bimg = null;
		try {
			bimg = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.imageHeight = bimg.getHeight();
		this.imageWidth = bimg.getWidth();
	}
	
	@Override
	public Image getImage() {
		this.updateName();
		ArcadeImage(this.imageName);
		return this.image;
	}
	public void updateName() {
//		this.imageName = "play.png";
		this.startTime++;
		if(this.startTime <= 200) {
			this.imageName = "play.png";
		}
		if(this.startTime >200 && this.startTime <= 400) {
			this.imageName = "play2.png";
			if (this.startTime == 400) this.startTime = 0;
		}
//		System.out.println(this.isStart());
	}
	public boolean isStart() {
		this.getMouse();
		Rectangle2D.Double rect = new Rectangle2D.Double(450, 500, 132, 48);
		if (rect.contains(mousePoint)) {
			return true;
		}
		return false;
	}
	public void getMouse() {
		PointerInfo a = MouseInfo.getPointerInfo();
		this.mousePoint = a.getLocation();
		int x = (int) this.mousePoint.getX();
		int y = (int) this.mousePoint.getY();
	}
//	public Rectangle2D.Double getRect(){
//		return new Rectangle2D.Double(point.getX(), point.getY(), this.imageHeight, this.imageWidth);
//	}
	@Override
	public void updatePosition() {
//		double dx = this.getDrawPoint().getX() ;
//		double dy = this.getDrawPoint().getY() ;
//		Point2D point = new Point2D.Double(dx, dy);
//		this.setDrawPoint(point);
	}
//	private void DrawOn(Graphics2D g2, Drawable d) {
//		g2.setColor(color);
//		g2.fillRect((int)this.point.getX(), (int)this.point.getY(), getWidth(), getHeight());
//		g2.setColor(Color.black);
//		g2.drawRect(getX(), getY(), getWidth(), getHeight());
//		g2.fill(shape);
//		g2.drawImage(image, d.getX(), d.getY(), 40, 40, null);
//	}
	@Override
	public void timePassed() {
		updatePosition();
	}
	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void collideWith(Element other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideWithMonster(Monster monster) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideWithHero(Hero hero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void updateSize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateColor() {
		// TODO Auto-generated method stub
		
	}
	
	

}
