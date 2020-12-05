import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * This class renders the components in the GameWorld on the GUI. It contains a
 * keyboard listner to listen to user command on hero.
 * 
 * @author Jingwen Wu & Haoxuan Sun Created on 10/26/2019
 */

public class GameComponent extends JComponent{

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private GameWorld world;
	private BackgroundMusic backgroundMusic;
	private BackgroundMusic backgroundMusic2;
	private int gameLevel;
//	private int maxLevel;
	private final static int START_LEVEL = 6;
	private final static int MAX_LEVEL = 3;
	private int totalscores;
	private int blood;
	private int y = 0;
	private int ticks = 0;
	
	private BufferedImage endPage;
	private BufferedImage winPage;
	private BufferedImage startPage;
	private BufferedImage snow;
	private BufferedImage button2;
	private BufferedImage up;
	private BufferedImage down;
	private BufferedImage left;
	private BufferedImage right;
	private BufferedImage restart;
	private BufferedImage shoot;
	private BufferedImage goBack;
	private BufferedImage start;
	private BufferedImage start3;
	private BufferedImage combine;
	private BufferedImage joust;
	private BufferedImage backCom;
	
	boolean isSwitching;

	
	private long initial_time;
	static long duration;
	private ArrayList<Long> playerScores;
	
	private Point button1Point;
	private Point button2Point;
	
	private String musicName  = null;
	/**
	 * Constructs a component for rendering the given ballControl on the GUI.
	 * 
	 *
	 */
	
	public GameComponent() {
		this.initial_time = System.currentTimeMillis();
//		this.duration = 0;
		this.gameLevel = START_LEVEL;
		this.world = new GameWorld(this.gameLevel);
		this.backgroundMusic = new BackgroundMusic(this.musicName);
		this.backgroundMusic2 = new BackgroundMusic(this.musicName);
		this.totalscores = 0;
		this.blood = 3;
		this.playerScores = new ArrayList<Long>();
		this.isSwitching = false;
		
//	    try {
//	    	AudioInputStream welcomeStream = AudioSystem.getAudioInputStream(new File("startMusic.wav"));
//	    	this.startMusic = AudioSystem.getClip();
//	    	this.startMusic.open(welcomeStream);
//	    }catch (Exception e) {
//	    	System.out.println("StartMusic not found");
//	    }
		try {
			this.endPage= ImageIO.read(new File("gameOver.png"));
			this.winPage = ImageIO.read(new File("winPage.png"));
			this.snow = ImageIO.read(new File("flake.png"));
			this.button2 = ImageIO.read(new File("ice button.png"));
			this.startPage =  ImageIO.read(new File("background2.png"));
			this.up =  ImageIO.read(new File("upArrow.png"));
			this.right =  ImageIO.read(new File("rightArrow.png"));
			this.left =  ImageIO.read(new File("leftArrow.png"));
			this.down =  ImageIO.read(new File("downArrow.png"));
			this.shoot =  ImageIO.read(new File("fire_right.png"));
			this.restart =  ImageIO.read(new File("restart.png"));
			this.goBack = ImageIO.read(new File("goBack.png"));
			this.start = ImageIO.read(new File("start.png"));
			this.start3 = ImageIO.read(new File("start3.png"));
			this.joust = ImageIO.read(new File("penguin.png"));
			this.backCom = ImageIO.read(new File("iceBerg.png"));
	
		} catch (IOException e) {
			throw new RuntimeException("Could not load image file ");
		}
		

		// add keyboard listener to this panel
		GameLevelHandler levelHandler = new GameLevelHandler();
		this.addKeyListener(levelHandler);
		// Set the listener on this panel.
		Handlerclass handler = new Handlerclass();
		this.addMouseListener(handler);
		this.setFocusable(true);

		// Create a timer to trigger the repaint of components.
		Timer repaintTimer = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (GameComponent.this.world.getIsPaused() == false) {
				repaint();
				if (GameComponent.this.blood == 0) {
					GameComponent.this.world.setIsPaused(true);
					GameComponent.this.world = new GameWorld(4);
					
				}
				if (GameComponent.this.world.getMonsters().size() == 0 && GameComponent.this.world.getScores() == 0) {
					int level = GameComponent.this.getWorld().getLevel();
					GameComponent.this.world.setIsPaused(true);
					if (level < MAX_LEVEL) {
					GameComponent.this.world = new GameWorld(level + 1);
					} else if (level == MAX_LEVEL) {
						GameComponent.this.world = new GameWorld(5);
						GameComponent.this.writeFile();
					}
					
				GameComponent.this.isSwitching = true;

				}
				}
			}

		});
		repaintTimer.start();
	}
	
	// write in files if the highest score appear;
	public void writeFile() {
		readFile();
		this.playerScores.add(duration);
		
		Collections.sort(this.playerScores);
//		for (Long l : this.playerScores) {
//			System.out.println(l);
//		}
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("scoreBoard.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		pw.println(Long.toString(playerScores.get(0)));
		pw.println(Long.toString(playerScores.get(1)));
		pw.println(Long.toString(playerScores.get(2)));
		pw.close();
	}
	
	// read files to see the highest scores
	
	public void readFile() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("scoreBoard.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Files not found");
			return;
		}
		this.playerScores.clear();

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			this.playerScores.add(Long.parseLong(line));
//			System.out.println(line);
		}
		
//		System.out.println("finish read");
		scanner.close();
		
	}

	public GameWorld getWorld() {
		return this.world;
	}
	
	public int getExp() {
		return this.totalscores;
	}

	public int getBlood() {
		return this.blood;
	}
	
	public void restart() {
		this.blood = 3;
		this.initial_time = System.currentTimeMillis();
		this.totalscores = 0;
	}
	
	public long getDuraction() {
		return duration;
	}

	/**
	 * Repaint the components onto simulation panel.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		duration = System.currentTimeMillis() - this.initial_time;
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawDrawable(g2, this.world);
		ArrayList<Drawable> drawableParts = this.world.getDrawableParts();
		for (Drawable d : drawableParts) {
			drawDrawable(g2, d);
		}

		this.totalscores += this.world.getScores();
		this.world.resetScore();

		this.blood -= this.world.getLose();
		this.world.resetLose();
		
		if (this.world.getLevel() == 1 && this.isSwitching == true) {
			this.backgroundMusic.stopMusic();
			this.backgroundMusic.playChillMusic();
			this.isSwitching = false;
		}
		if (this.world.getLevel() == 3 && this.isSwitching == true) {
			this.backgroundMusic.stopMusic();
			this.backgroundMusic.playBossMusic();
			this.isSwitching = false;
		}
		if (this.world.getLevel() == 4) {
			g.drawImage(this.endPage,0, 0,null);
		}
		if (this.world.getLevel() == 5) {
			g.drawImage(this.winPage,0, 0,null);
			
			// display the scores
			g2.setColor(Color.white);
			Font tr = new Font("Comic Sans MS", Font.BOLD, 32);
			g2.setFont(tr);
			g2.drawString("High Score", 400, 200);
			Long sec_1 = this.playerScores.get(0)/1000 % 60;
			Long min_1 = this.playerScores.get(0)/1000 / 60;
			Long sec_2 = this.playerScores.get(1)/1000 % 60;
			Long min_2 = this.playerScores.get(1)/1000 / 60;
			Long sec_3 = this.playerScores.get(2)/1000 % 60;
			Long min_3 = this.playerScores.get(2)/1000 / 60;
			g2.drawString("1st " + Long.toString(min_1) + ":" + Long.toString(sec_1),400,300);
			g2.drawString("2nd " + Long.toString(min_2) + ":" + Long.toString(sec_2),400,350);
			g2.drawString("3rd " + Long.toString(min_3) + ":" + Long.toString(sec_3),400,400);
			
			
		}
		
		
		if (this.world.getLevel() == 6) {
			
			Random random = new Random();
			int abc = random.nextInt(100);
//			this.backgroundMusic.stopMusic();
//			this.backgroundMusic.playMusic();
			
			g2.setColor(new Color(153,0,0));
			Font tr = new Font("Comic Sans MS", Font.CENTER_BASELINE, 20);
			g2.setFont(tr);
			g2.drawString(" JOUST COPY RIGHT 2019", 400, 220);
			g2.drawString(" DESIGNED BY: VALENTINE, RAY", 400, 240);
			g2.drawString("How to play?", 95, 550);
			g2.drawImage(this.start, 140, 220, null);
			g2.drawImage(this.button2, 85, 350, null);
			g2.drawImage(this.button2, 85, 500, null);
			g2.setColor(Color.BLUE);
			Font tr2 = new Font("Comic Sans MS", Font.ITALIC | Font.BOLD, 40);
			g2.setFont(tr2);
			g2.drawString(" Your Name:", 95, 400);
			g2.drawString("How to play?", 95, 550);
			Point2D.Double point = new Point2D.Double(abc*10, 0);
			GameComponent.this.world.addFlakes(point);
			GameComponent.this.combine = textEffect(this.backCom, makeTransparentImage(this.joust));
			g2.drawImage(this.combine, 400, 0, null);
//			this.backgroundMusic.stopMusic();
			this.backgroundMusic.playMusic();
		}
		if (this.world.getLevel() == 7) {
			this.backgroundMusic.stopMusic();
			this.backgroundMusic.playMusic();
			g2.drawImage(this.up, 200, 100, null);
			g2.drawImage(this.down, 200, 200, null);
			g2.drawImage(this.left, 100, 200, null);
			g2.drawImage(this.right, 300, 200, null);
			Font tr = new Font("Comic Sans MS", Font.ITALIC | Font.BOLD, 60);
			g2.setFont(tr);
			g2.drawString("I", 600, 180);
			g2.drawString("K", 600, 280);
			g2.drawString("J", 500, 280);
			g2.drawString("L", 700, 280);
			g2.drawImage(this.shoot, 200, 330,128,128, null);
			g2.drawImage(this.restart, 200, 420, null);			
			g2.drawString("A", 500, 410);
			g2.drawString("X", 500, 510);
			g2.drawImage(this.goBack, 50, 600, null);	
		}
	}

	/**
	 * This is a helper function for paintComponent function to draw every object.
	 * 
	 * @param g2
	 * @param d`
	 */
	private void drawDrawable(Graphics2D g2, Drawable d) {
		Color color = d.getColor();
		Shape shape = d.getShape();
		Image image = d.getImage();
		g2.setColor(color);
		g2.fill(shape);
		g2.drawImage(image,(int)shape.getBounds().getX(),(int)shape.getBounds().getY(),null);
	}
	
	public class Handlerclass implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
        	System.out.println("enterrrrr");

			Point startPoint = new Point(140,220);
            if (GameComponent.this.start != null && startPoint != null) {
                Point me = e.getPoint();
                Rectangle bounds = new Rectangle(startPoint, new Dimension(GameComponent.this.start.getWidth(), GameComponent.this.start.getHeight()));
                if (bounds.contains(me)) {
                	GameComponent.this.start = GameComponent.this.start3;
                	System.out.println("enterrrrr");
                }
            }
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
//			Point startPoint = new Point(140,220);
//            if (GameComponent.this.start != null && startPoint != null) {
//                Point me = e.getPoint();
//                Rectangle bounds = new Rectangle(startPoint, new Dimension(GameComponent.this.start.getWidth(), GameComponent.this.start.getHeight()));
//                if (bounds.contains(me)) {
//                	GameComponent.this.start = GameComponent.this.start3;
//                }
//            }
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			Point startPoint = new Point(140,220);
            if (GameComponent.this.start != null && startPoint != null) {
                Point me = e.getPoint();
                Rectangle bounds = new Rectangle(startPoint, new Dimension(GameComponent.this.start.getWidth(), GameComponent.this.start.getHeight()));
                if (bounds.contains(me)) {
                	GameComponent.this.world = new GameWorld(1);
                	GameComponent.this.isSwitching = true;
                }
            }
			Point button1Point = new Point(85,350);
            if (GameComponent.this.button2 != null && button1Point != null) {
                Point me = e.getPoint();
                Rectangle bounds = new Rectangle(button1Point, new Dimension(GameComponent.this.button2.getWidth(), GameComponent.this.button2.getHeight()));
                if (bounds.contains(me)) {
                	GameComponent.this.world = new GameWorld(1);
                	GameComponent.this.isSwitching = true;
                }
            }
            Point button2Point = new Point(85,500);
            if (GameComponent.this.button2 != null && button2Point != null) {
                Point me = e.getPoint();
                Rectangle bounds = new Rectangle(button2Point, new Dimension(GameComponent.this.button2.getWidth(), GameComponent.this.button2.getHeight()));
                if (bounds.contains(me)) {
                	GameComponent.this.world = new GameWorld(7);
                }
            }
            Point button3Point = new Point(50,600);
            if (GameComponent.this.goBack != null && button3Point != null) {
                Point me = e.getPoint();
                Rectangle bounds = new Rectangle(button3Point, new Dimension(GameComponent.this.goBack.getWidth(), GameComponent.this.goBack.getHeight()));
                if (bounds.contains(me)) {
                	GameComponent.this.world = new GameWorld(6);
                }
            }
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	/**
	 * This inner class listener switches the game level using 'u' and 'd' on the
	 * keyboard. The hero will move to left when 'j' is pressed. The hero will move
	 * to right when 'l' is pressed. The hero will fly when 'i' is pressed.
	 */
	public class GameLevelHandler implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == 'd') {
				GameComponent.this.world.setIsPaused(true);
				System.out.println("go to next level");
				if (GameComponent.this.getWorld().getLevel() < MAX_LEVEL) {
					GameComponent.this.world = new GameWorld(GameComponent.this.getWorld().getLevel() + 1);
					GameComponent.this.isSwitching = true;
				} else {
					System.out.println("there is no further level");
				}
			}
			if (e.getKeyChar() == 'u') {
				System.out.println("go to previous level");
				GameComponent.this.world.setIsPaused(true);
				if (GameComponent.this.getWorld().getLevel() > START_LEVEL) {
					GameComponent.this.world = new GameWorld(GameComponent.this.getWorld().getLevel() - 1);
					GameComponent.this.isSwitching = true;
				} else {
					System.out.println("there is no further level");
				}
			}
			if (e.getKeyChar() == 'x' && GameComponent.this.world.getLevel() >= 4 ) {
				System.out.println("restart");
				GameComponent.this.restart(); 
				GameComponent.this.world = new GameWorld(1);
			}
			if (e.getKeyChar() == 'j') {
				GameComponent.this.getWorld().lastMovement = 0;
				GameComponent.this.getWorld().isMovingLeft = true;
			}
			if (e.getKeyChar() == 'l') {
				GameComponent.this.getWorld().lastMovement = 1;
				GameComponent.this.getWorld().isMovingRight = true;
			}
			if (e.getKeyChar() == 'i') {
				GameComponent.this.getWorld().isFlying = true;
			}
			if (e.getKeyChar() == 'a' && GameComponent.this.world.heroIsDie() == false) {
				GameComponent.this.backgroundMusic2.stopMusic();
				GameComponent.this.backgroundMusic2.playShootMusic();
				System.out.println("shoot!!!");
				Point2D.Double heroLocation = GameComponent.this.getWorld().getHeroLocation();
				Point2D.Double shootPoint = new Point2D.Double(
						heroLocation.getX() - 5 + GameComponent.this.getWorld().lastMovement * 70,
						heroLocation.getY() + 15);
				GameComponent.this.getWorld().addBullets(shootPoint, GameComponent.this.getWorld().lastMovement,0);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			GameComponent.this.getWorld().isMovingLeft = false;

			GameComponent.this.getWorld().isMovingRight = false;

			GameComponent.this.getWorld().isFlying = false;

			GameComponent.this.getWorld().isFalling = true;

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}
	
	public  BufferedImage textEffect(BufferedImage image, BufferedImage text) {
	    if (image.getWidth() != text.getWidth() ||
	        image.getHeight() != text.getHeight())
	    {
	        throw new IllegalArgumentException("Dimensions are not the same!");
	    }
	    BufferedImage img = new BufferedImage(image.getWidth(),
	                                          image.getHeight(),
	                                          BufferedImage.TYPE_INT_ARGB_PRE);
	    if (GameComponent.this.ticks < 488) {
	    	GameComponent.this.ticks +=3;
	    } if (GameComponent.this.ticks >= 488) GameComponent.this.ticks = 0;
	    for (int y = 0; y < image.getHeight(); ++y) {
	        for (int x = 0; x < image.getWidth(); ++x) {
	           int textPixel = text.getRGB(x, y);
	           int textA = (textPixel & 0xFF000000);
	           int x2 = x + GameComponent.this.ticks;
	           if (x2 >= 488) x2 = x2-488;
	           int sourceRGB = image.getRGB(x2, y);
	           int newAlpha = (int) (((textA >> 24) * (sourceRGB >> 24)) / 255d);
	           int imgPixel = (newAlpha << 24) |  (sourceRGB & 0x00FFFFFF);
	           int rgb = imgPixel | textA;
	           img.setRGB(x, y, rgb);
	        }
	    }
	    return img;
	}
	 public BufferedImage makeTransparentImage(BufferedImage br) {
		    for (int i = 0; i < br.getHeight(); i++) {
		        for (int j = 0; j < br.getWidth(); j++) {
		            Color c = new Color(br.getRGB(j, i));
		            int r = c.getRed();
		            int b = c.getBlue();
		            int g = c.getGreen();
		            if ((r >=230 && b >=230 && g >=230)) {
		                br.setRGB(j, i, 0x00FF00);
		            }
		        }
		    }
		    return br;
		}

}
